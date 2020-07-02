package br.com.rezende.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rezende.model.EntradaUsuarioDTO;
import br.com.rezende.model.Usuario;
import br.com.rezende.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "CRUD de Usuário", description = "Endpoint que possibilita executar todas as operações de CRUD de Usuário.")
@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService 	service;
	
	@ApiOperation(value = "Retorna todos os usuários cadastrados.")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Usuario> findAll(){
		return service.listAllUsuarios();
	}		

	@ApiOperation(value = "Retorna o usuário que possui o ID enviado na requisição.")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Usuario findById(@PathVariable("id") Integer id) throws Exception {
		if(id == null) {
			throw new ResourceNotFoundException("É necessário informar um ID.");
		}
		return service.findUsuarioById(id);
	}
	
	@ApiOperation(value = "Cria um novo usuário apartir dos dados enviados na requisião e retorna o usário criado.")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Usuario create(@RequestBody EntradaUsuarioDTO entradaUsuario){
		Usuario usuario = new Usuario();
		usuario.setName(entradaUsuario.getName());
		usuario.setEmail(entradaUsuario.getEmail());
		
		return service.createUsuario(usuario);
	}
	
	@ApiOperation(value = "Operação para alterar um usuário já cadastrado.")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Usuario update(@RequestBody EntradaUsuarioDTO entradaUsuario){
		
		Usuario usuario = new Usuario();
		usuario.setId(entradaUsuario.getId());
		usuario.setName(entradaUsuario.getName());
		usuario.setEmail(entradaUsuario.getEmail());
		
		return service.updateUsuario(usuario);
	}
	
	@ApiOperation(value = "Exclui um usuário cadastrado com base no ID enviado na requisição.")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(name = "id") Integer id) {
		service.deleteUsuario(id);
	}
}
