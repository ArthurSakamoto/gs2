package br.com.fiap.gs2.dto;

import br.com.fiap.gs2.model.User;

public record UserDto(Long id, String nome, String email) {

    public User toUser(){
        var user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setNome(nome);
        return user;
    }

    public boolean isPresent() {
        return false;
    }
    
}
