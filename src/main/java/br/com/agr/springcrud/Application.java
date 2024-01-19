package br.com.agr.springcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
    	System.out.println("Versão do Java: " + System.getProperty("java.version"));
        SpringApplication.run(Application.class, args);
    }
}
