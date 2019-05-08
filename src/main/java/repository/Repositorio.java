package repository;

import org.springframework.stereotype.Repository;

import entidade.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface Repositorio extends JpaRepository<Produto, Long>{
	
	
	
}
