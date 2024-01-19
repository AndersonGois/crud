package br.com.agr.springcrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.agr.springcrud.model.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{

	@Query("select u from Usuario u where (:nome is null or upper(trim(u.nome)) like upper(concat('%', :nome,'%')))")
	List<Usuario>  buscarPorNome(@Param("nome") String nome);

	@Query("select u from Usuario u where u.cpf=:cpf")
	List<Usuario> todosComCpf(@Param("cpf") String cpf);

}
