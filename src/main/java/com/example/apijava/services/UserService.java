package com.example.apijava.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.example.apijava.repositories.UserRepository;
import com.example.apijava.dto.UserDto;
import com.example.apijava.models.User;

@Service
public class UserService {
    private final UserRepository UserRepository;

    public UserService(UserRepository UserRepository){
        this.UserRepository = UserRepository;
    }

    public List<User> listUsers(){
        return UserRepository.findAll();
    }

    public User retrieveUser(UUID id) throws Exception{
        var user = UserRepository.findById(id).orElse(null);
        if(user == null) throw new Exception ("Usúario não encontrado");

        return user;
    }

    public User createUser(UserDto user){
        User newUser = new User();

        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setIsAdmin(user.getIsAdmin());

        return UserRepository.save(newUser);
    }
}
