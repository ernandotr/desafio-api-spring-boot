package br.com.rezende.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import br.com.rezende.security.CriptografiaRSA;

public class CriptografiaRSATest {
	
	
	@Test
	public void testCriptografa() throws NoSuchAlgorithmException {
		KeyPair keys = CriptografiaRSA.geraChave();
		byte[] textoCriptografado = CriptografiaRSA.criptografa("Ernando", keys.getPublic());
		String textoDescriptografado = CriptografiaRSA.decriptografa(textoCriptografado, keys.getPrivate());
		assertNotNull(textoDescriptografado);
		assertEquals("Ernando", textoDescriptografado);
		

	}
	
	

}
