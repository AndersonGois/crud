package br.com.agr.springcrud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.agr.springcrud.model.Usuario;
import br.com.agr.springcrud.service.UsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioservice;

	@GetMapping(value = "/usuarios")
	public ResponseEntity<List<Usuario>> buscarUsuarios() {
		return Optional.ofNullable(usuarioservice.buscarTodos()).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping(value = "/usuario/{id}")
	public ResponseEntity<Usuario> buscarUsuariosPorID(@PathVariable Long id) {
		return Optional.ofNullable(usuarioservice.buscarTodosPorID(id)).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping(value = "/usuario")
	public ResponseEntity<Usuario> buscarUsuariosPorIdP(@RequestParam(value = "id") Long id) {
		return Optional.ofNullable(usuarioservice.buscarTodosPorID(id)).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/usuarioNome/{nome}")
	public ResponseEntity<List<Usuario>> buscarUsuariosPorNome(@PathVariable(value = "nome") String nome) {
		return Optional.ofNullable(usuarioservice.buscarPorNome(nome)).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/usuarioh")
	public ResponseEntity<Usuario> buscarUsuariosPorIdH(@RequestHeader(value = "id") Long id) {
		return Optional.ofNullable(usuarioservice.buscarTodosPorID(id)).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping(value = "/usuariob")
	public ResponseEntity<Usuario> buscarUsuariosPorIdHB(@RequestBody() Usuario usuario) {
		return Optional.ofNullable(usuarioservice.buscarTodosPorID(usuario.getId())).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping(value = "/salvarUsuario")
	public ResponseEntity<Usuario> salvarUsuarios(@RequestBody Usuario usuario) {
		try {
			return Optional.ofNullable(usuarioservice.salvar(usuario))
					.map(usu -> new ResponseEntity<Usuario>(usu, HttpStatus.OK))
					.orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
			
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/alterarUsuario")
	public ResponseEntity<Usuario> alterarUsuarios(@RequestBody Usuario usuario) {
		try {
			return Optional.ofNullable(usuarioservice.alterar(usuario))
					.map(user -> new ResponseEntity<Usuario>(user, HttpStatus.OK))
					.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/deleteUsuario/{id}")
	public ResponseEntity<String> alterarUsuarios(@PathVariable Long id) {
		try {
			String resultado = usuarioservice.deleteUsuario(id);
			HttpStatus status = "Usuario deletado com sucesso".equals(resultado) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
			return ResponseEntity.status(status).body(resultado);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao deletar o usuário: " + e.getMessage());
		}
	}
}
