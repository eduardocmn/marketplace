package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entidade.Produto;

@Repository
public interface Repositorio extends JpaRepository<Produto, Long>{
	
	
	
}
