package br.com.rezende.controller;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rezende.security.CriptografiaRSA;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/cripto")
@Api(value = "Gera Criografia", description = "Endpoint para obter a chave publica que deverá ser utilizada para criptografar os dados do usuário.")
public class Criptografia {
	
	@ApiOperation(value = "Retorna a chave publica para que o cliente possa criptografar os dados.")
	@RequestMapping(value = "/gera-chave", produces = "text/pain", method = RequestMethod.GET)
	public String getChavePublica() throws NoSuchAlgorithmException {
		KeyPair keyPair = CriptografiaRSA.geraChave();
		return CriptografiaRSA.getKeyAsString(keyPair.getPublic());
	}
	
	@ApiOperation(value = "Retorna o texto enviado, criptografado com a chave púbica fornecida.")
	@RequestMapping(value = "/encripta", produces = "text/pain", method = RequestMethod.POST)
	public String encripta(@RequestParam(value = "texto") String texto, @RequestParam(value = "chave") String chave) throws Exception {
		PublicKey publicKey = CriptografiaRSA.getPublicKeyFromString(chave);
		byte[] textoCriptografado = CriptografiaRSA.criptografa(texto, publicKey);
		return Base64Utils.encodeToString(textoCriptografado);
	}
	

}
