package br.com.fiap.gs2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.gs2.model.User;
import br.com.fiap.gs2.repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = repository.findByEmail(username);
        return user;
    }
    
}
