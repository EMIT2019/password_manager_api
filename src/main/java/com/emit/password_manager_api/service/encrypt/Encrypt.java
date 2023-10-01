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

public interface Encrypt<T, E> {
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
}
