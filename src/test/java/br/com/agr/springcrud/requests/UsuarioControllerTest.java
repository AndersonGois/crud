package br.com.agr.springcrud.requests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.agr.springcrud.controllers.UsuarioController;
import br.com.agr.springcrud.model.Usuario;
import br.com.agr.springcrud.service.UsuarioService;

@SpringBootTest
@RunWith(JUnit4.class)
public class UsuarioControllerTest {
	
	@Autowired
	private UsuarioController usuarioController;
	
	@Autowired
	private UsuarioService usuarioservise;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Test
	public void a_01greetJava() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setCpf("028028028028");
		usuario.setNome("teste1");
		usuario.setIdade(251);
		
		usuario = usuarioController.salvarUsuarios(usuario).getBody();
		
		assertEquals(true, usuario.getId() > 0);
	}
	
	@Test
	public void a_03greetJava() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
		MockMvc mockMvc = builder.build(); 
		
		
		Usuario usuario = new Usuario();
		usuario.setCpf("028028028028");
		usuario.setNome("teste1");
		usuario.setIdade(251);
		
		
		ObjectMapper objctMapper = new ObjectMapper(); 
		
		ResultActions resultActions = mockMvc
			.perform(MockMvcRequestBuilders.post("/salvarUsuario").content(objctMapper.writeValueAsString(usuario))
					.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		
		
		usuario = objctMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), Usuario.class);
		
		assertEquals(true, usuario.getId() > 0);
	}
	
	@Test
	public void a_02tearDown() {
		System.out.println("Método após a conclusão de todos os testes");
		usuarioservise.todosComCpf("028028028028").forEach(user -> {
			usuarioservise.deleteUsuario(user.getId());
		});
		

		assertEquals(true, 1 > 0);
		// Coloque qualquer lógica que você queira executar após todos os testes aqui
	}
}
