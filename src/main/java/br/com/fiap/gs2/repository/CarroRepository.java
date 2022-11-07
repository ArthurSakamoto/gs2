package br.com.fiap.gs2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.gs2.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{
    
}
