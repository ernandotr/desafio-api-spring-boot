package br.com.rezende.security;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.springframework.util.Base64Utils;

public class CriptografiaRSA {

	public static final String ALGORITHM = "RSA";

	/**
	 * Gera a chave que contém um par de chave Privada e Pública usando 1025 bytes.
	 * Armazena o conjunto de chaves nos arquivos private.key e public.key
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static KeyPair geraChave() throws NoSuchAlgorithmException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
		keyGen.initialize(2048);
		KeyPair key = keyGen.generateKeyPair();
		return key;

	}

	/**
	 * Criptografa o texto puro usando chave pública.
	 */
	public static byte[] criptografa(String texto, PublicKey chave) {
		byte[] cipherText = null;

		try {
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			// Criptografa o texto puro usando a chave Púlica
			cipher.init(Cipher.ENCRYPT_MODE, chave);
			cipherText = cipher.doFinal(texto.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cipherText;
	}

	/**
	 * Decriptografa o texto puro usando chave privada.
	 */
	public static String decriptografa(byte[] texto, PrivateKey chave) {
		byte[] dectyptedText = null;

		try {
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			// Decriptografa o texto puro usando a chave Privada
			cipher.init(Cipher.DECRYPT_MODE, chave);
			dectyptedText = cipher.doFinal(texto);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new String(dectyptedText);
	}

	/**
	 * Converte uma chave para string codificada como Base64
	 * 
	 * @param chave
	 * @return Uma string que representa chave
	 */
	public static String getKeyAsString(Key key) {
		// obtem os bytes da chave
		byte[] keyBytes = key.getEncoded();
		return Base64Utils.encodeToString(keyBytes);
	}

	/**
	 * Gera Private Key de uma string codificada em Base64.
	 * 
	 * @param key string Base64 que representa a chave.
	 * @return a PrivateKey
	 * @throws java.lang.Exception
	 */
	public static PrivateKey getPrivateKeyFromString(String key) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(key));
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
		return privateKey;
	}

	/**
	 * Gera Public Key de uma string codificada em Base64.
	 * 
	 * @param key string Base64 que representa a chave.
	 * @return a PublicKey
	 * @throws java.lang.Exception
	 */
	public static PublicKey getPublicKeyFromString(String key) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64Utils.decodeFromString(key));
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
		return publicKey;
	}

}