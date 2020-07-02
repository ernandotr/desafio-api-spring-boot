package br.com.rezende.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rezende.exception.ResourceNotfoundExceprion;
import br.com.rezende.model.Usuario;
import br.com.rezende.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario createUsuario(Usuario usuario) {
		return repository.save(usuario);
	}
	
	public Usuario updateUsuario(Usuario usuario) {
		Usuario entity = findUsuarioById(usuario.getId());
		entity.setName(usuario.getName());
		entity.setEmail(usuario.getEmail());
		
		return repository.save(entity);
	}
		
	public List<Usuario> listAllUsuarios(){
		return repository.findAll();
	}
	
	public Usuario findUsuarioById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotfoundExceprion("Nenhum usuário encontrado com este ID."));
	}
	
	public void deleteUsuario(Integer id) {
		Usuario entity = findUsuarioById(id);
		repository.delete(entity);
	}

}
