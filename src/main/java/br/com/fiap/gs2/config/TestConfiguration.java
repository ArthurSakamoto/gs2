package br.com.fiap.gs2.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.gs2.model.Carro;
import br.com.fiap.gs2.model.User;
import br.com.fiap.gs2.repository.CarroRepository;
import br.com.fiap.gs2.repository.UserRepository;

@Configuration
public class TestConfiguration implements CommandLineRunner{

    @Autowired
    CarroRepository repositoryA;

    @Autowired
    UserRepository repositoryB;

    @Override
    public void run(String... args) throws Exception {
        
        repositoryA.saveAll(List.of(
            new Carro("KWID E-TECH", "RENAULT", 5.0, 69.72),
            new Carro("ZOE E-TECH", "RENAULT", 4.2, 53.22),
            new Carro("LEAF 2022", "Nissan", 4.3, 44.93)
        ));

        repositoryB.saveAll(List.of(
            new User("Arthur", "arthur@fiap.com", "123"),
            new User("Admin", "admin@fiap.com", "123")
           
        ));
        
    }
    
}
