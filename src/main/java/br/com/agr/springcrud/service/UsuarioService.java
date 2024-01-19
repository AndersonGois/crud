package br.com.agr.springcrud.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agr.springcrud.model.Usuario;
import br.com.agr.springcrud.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repository;

	public List<Usuario> buscarTodos() {
		return repository.findAll();
	}

	public Usuario salvar(Usuario usuario) {
		return Optional.ofNullable(usuario)
			    .filter(user -> Objects.nonNull(usuario.getCpf()) && !usuario.getCpf().isEmpty())
			    .filter(user -> Objects.nonNull(usuario.getNome()) && !usuario.getNome().isEmpty())
			    .map(user -> {
			        if (user.getId() == null || !repository.existsById(user.getId())) {
			            return repository.save(user);
			        } else {
			            return null; 
			        }
			    })
			    .orElse(null);
	}

	public Usuario alterar(Usuario usuario) {
		return Optional.ofNullable(usuario)
				.map(Usuario::getId)
				.flatMap(id -> repository.findById(id))
				.filter(user -> Objects.nonNull(usuario.getNome())).filter(user -> Objects.nonNull(usuario.getCpf()))
				.filter(user -> Objects.nonNull(usuario.getIdade()) && usuario.getIdade() > 0).map(usuarioExistente -> {
					repository.save(usuario);
					return usuario;
				}).orElse(null);

	}

	public String deleteUsuario(Long id) {
		return repository.findById(id).map(usu -> {
			repository.delete(usu);
			return "Usuario deletado com sucesso";
		}).orElse("Usuario inexistente");
	}

	public Usuario buscarTodosPorID(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<Usuario> buscarPorNome(String nome) {
		return repository.buscarPorNome(nome);
	}

	public List<Usuario> todosComCpf(String cpf) {
		return repository.todosComCpf(cpf);
	}
	
	
	


}
