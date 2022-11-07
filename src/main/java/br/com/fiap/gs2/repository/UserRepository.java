package br.com.fiap.gs2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.gs2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
