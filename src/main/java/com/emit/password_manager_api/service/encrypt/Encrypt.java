package com.emit.password_manager_api.service.encrypt;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.emit.password_manager_api.model.Keyword;
import com.emit.password_manager_api.model.ModelEntity;

public interface Encrypt<T, E, K extends ModelEntity> {
	SecretKey getKeyFromPassword(E password, E salt) throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	IvParameterSpec generateIv();
	
	String encrypt(E input, SecretKey key) throws 
	NoSuchPaddingException,
	NoSuchAlgorithmException,
	InvalidKeyException,
	BadPaddingException,
	IllegalBlockSizeException,
	InvalidAlgorithmParameterException;
	
	String decrypt(String cipherText, SecretKey key) throws
	NoSuchPaddingException,
	NoSuchAlgorithmException,
	InvalidKeyException,
	BadPaddingException,
	IllegalBlockSizeException,
	InvalidAlgorithmParameterException;
	

	K encryptKeyword(K keyword);
	
	K decryptKeyword(K keyword);
}
