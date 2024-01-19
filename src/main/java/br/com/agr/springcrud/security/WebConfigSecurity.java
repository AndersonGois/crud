package br.com.agr.springcrud.security;

import javax.servlet.http.HttpSessionActivationListener;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionActivationListener {
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.GET, "/usuario").antMatchers(HttpMethod.GET, "/usuarioNome/{nome}").antMatchers(HttpMethod.GET, "/usuariob").antMatchers(HttpMethod.GET, "/usuarioh").antMatchers(HttpMethod.GET, "/usuarioNome/{nome}").antMatchers(HttpMethod.GET, "/usuarios")
		   .antMatchers(HttpMethod.PUT, "/alterarUsuario").antMatchers(HttpMethod.GET, "/usuario/{id}").antMatchers(HttpMethod.DELETE, "/deleteUsuario/{id}").antMatchers(HttpMethod.POST, "/salvarUsuario");
		
	}
	
}
