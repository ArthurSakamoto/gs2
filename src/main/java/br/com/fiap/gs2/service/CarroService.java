package br.com.fiap.gs2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.gs2.model.Carro;
import br.com.fiap.gs2.repository.CarroRepository;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;
    
    public Page<Carro> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Carro save(Carro carro){
        return repository.save(carro);
    }

    public Optional<Carro> get(Long id) {
        return repository.findById(id);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public List<Carro> listAll() {
        return repository.findAll();
    }
}

