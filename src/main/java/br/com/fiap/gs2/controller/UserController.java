package br.com.fiap.gs2.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.gs2.dto.UserDto;
import br.com.fiap.gs2.model.User;
import br.com.fiap.gs2.repository.UserRepository;
import br.com.fiap.gs2.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public Page<User> index(Pageable pageable){
        return service.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.save(user)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> show(@PathVariable Long id){
        UserDto user = service.get(id);
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
       UserDto optional = service.get(id);
        if(optional == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        service.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid User newUser){
        UserDto optional = service.get(id);
        if(optional== null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        var user = optional.toUser();
        BeanUtils.copyProperties(newUser, user);
        user.setId(id);
        service.save(user);

        return ResponseEntity.ok(user);
    }

    
}