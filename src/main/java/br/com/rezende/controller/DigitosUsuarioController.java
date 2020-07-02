package br.com.rezende.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rezende.model.DigitoUnicoCalculado;
import br.com.rezende.model.Usuario;
import br.com.rezende.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Digitos Usuario", description = "Endpoint que possibilita a busca de dígitos calculados por usuário.")
@RestController
@RequestMapping(value = "/api/digitos-usuario")
public class DigitosUsuarioController {

	
	@Autowired
	private UsuarioService service;

	@ApiOperation(value = "Retorna a lista de dígitos únicos caculos de um determinado  usuário.")
	@RequestMapping(value = "/{idUsuario}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DigitoUnicoCalculado>> digitosCalculadosUsuario(@PathVariable("idUsuario") Integer idUsuario) {
		Usuario usuario = service.findUsuarioById(idUsuario);
		
		return new ResponseEntity<List<DigitoUnicoCalculado>>(usuario.getDigitosUnicos(), HttpStatus.OK);
	}
}
