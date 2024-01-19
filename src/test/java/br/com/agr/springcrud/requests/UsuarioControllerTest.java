//package br.com.agr.springcrud.requests;
//
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import br.com.agr.springcrud.controllers.UsuarioController;
//import br.com.agr.springcrud.model.Usuario;
//
//@SpringBootTest
//public class UsuarioControllerTest {
//
//    private final UsuarioController usuarioController;
//
//    public UsuarioControllerTest(UsuarioController usuarioController) {
//        this.usuarioController = usuarioController;
//    }
//
//    
//   @Test
//    public void greetJava() throws Exception {
//        Usuario usuario = new Usuario();
//        usuario.setCpf("028028028028");
//        usuario.setNome("teste1");
//        usuario.setIdade(251);
//
//        usuario = usuarioController.salvarUsuarios(usuario).getBody();
//
//        assertEquals(true, usuario.getId() > 0);
//    }
//
//   
// 
//    
//}
