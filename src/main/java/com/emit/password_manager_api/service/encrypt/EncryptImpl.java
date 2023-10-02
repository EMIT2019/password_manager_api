package com.emit.password_manager_api.service.encrypt;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.emit.password_manager_api.model.Keyword;

@Component
public class EncryptImpl implements Encrypt<Integer, String, Keyword> {

	@Override
	public SecretKey getKeyFromPassword(String password, String salt)
			throws 
			NoSuchAlgorithmException, 
			InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
		SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
		return secret;
	}

	@Override
	public IvParameterSpec generateIv() {
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		return new IvParameterSpec(iv);
	}

	@Override
	public String encrypt(String input, SecretKey key) 
			throws 
			NoSuchPaddingException, 
			NoSuchAlgorithmException,
			InvalidKeyException, 
			BadPaddingException, 
			IllegalBlockSizeException, 
			InvalidAlgorithmParameterException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cipherText = cipher.doFinal(input.getBytes());
		return Base64.getEncoder().encodeToString(cipherText);
	}

	@Override
	public String decrypt(String cipherText, SecretKey key) 
			throws 
			NoSuchPaddingException, 
			NoSuchAlgorithmException,
			InvalidKeyException, 
			BadPaddingException, 
			IllegalBlockSizeException, 
			InvalidAlgorithmParameterException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(plainText);
	}
	
	@Override
	public Keyword encryptKeyword(Keyword keyword) {
		try {
			String salt = "1271872sabsad57XFtsv978SGFGV7";
			SecretKey key = this.getKeyFromPassword(keyword.getKeyword(), salt);
			keyword.setKey(Base64.getEncoder().encodeToString(key.getEncoded()));
			String encryptedKeyword = this.encrypt(keyword.getKeyword(), key);
			keyword.setKeyword(encryptedKeyword);
			return keyword;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		throw new RuntimeException("There was a problem with the saving operation");
	}

	@Override
	public Keyword decryptKeyword(Keyword keyword) {
		try {
			String encodedKey = keyword.getKey();
			byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
			SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
			String decryptedKeyword = this.decrypt(keyword.getKeyword(), key);
			keyword.setKeyword(decryptedKeyword);
			return keyword;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
