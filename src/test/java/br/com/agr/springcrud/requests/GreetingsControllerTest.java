package br.com.agr.springcrud.requests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.agr.springcrud.Application;
import br.com.agr.springcrud.controllers.UsuarioController;
import br.com.agr.springcrud.model.Usuario;


@Profile("test")
@SpringBootTest(classes = Application.class)
public class GreetingsControllerTest {
	
	@Autowired
	private UsuarioController usuarioController;
	
	
	
	@Test
	public void greetRodJohnson() throws Exception {
		br.com.agr.springcrud.model.Usuario usuario = new Usuario();
		usuario.setCpf("028028028028");
		usuario.setNome("teste3");
		usuario.setIdade(253);
		
		usuario = usuarioController.salvarUsuarios(usuario).getBody();
		
		assertEquals(true, usuario.getId() > 0);
		
	}
}
