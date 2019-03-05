package com.shikha.project.login.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

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
public class AsymmetricDecryptionService implements InitializingBean {

	 	@Value("${application.security.privatekey}")
		private Resource privateStringPath;

		private volatile PrivateKey privateKey;

		public String decrypt(String content) throws EncryptionException {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
				return new String(cipher.doFinal(Base64.decodeBase64(content)), "UTF-8");
			} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException
					| IllegalBlockSizeException | BadPaddingException e) {
				throw new EncryptionException(e);
			}

		}

		@Override
		public void afterPropertiesSet() throws Exception {
			// TODO Auto-generated method stub
			byte[] keyBytes = Files.readAllBytes(privateStringPath.getFile().toPath());
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			privateKey = kf.generatePrivate(spec);
		}

}
