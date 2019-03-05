package com.shikha.project.login.service;

import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class AsymmetricCryptography implements InitializingBean{
	
     private PublicKey publicKey;
     
     @Value("${keyFilePath}")
     private Resource cert;
     
     public String encryptText(byte[] msg) throws NoSuchAlgorithmException, NoSuchPaddingException,
             UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
    	 Cipher cipher = Cipher.getInstance("RSA");
    	 cipher.init(Cipher.ENCRYPT_MODE, publicKey);
         return Base64.encodeBase64String(cipher.doFinal(msg));
     }

	@Override
	public void afterPropertiesSet() throws Exception {
		byte[] keyBytes = Files.readAllBytes(cert.getFile().toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");	
		this.publicKey = kf.generatePublic(spec);
	} 
}
