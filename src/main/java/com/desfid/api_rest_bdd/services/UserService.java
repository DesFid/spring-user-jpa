package com.desfid.api_rest_bdd.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desfid.api_rest_bdd.models.UserModel;
import com.desfid.api_rest_bdd.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository usuarioRepository;

    
    public List<UserModel> getAllUsers(){
        return usuarioRepository.findAll();
    }

    public UserModel saveUser(UserModel user){
        return usuarioRepository.save(user);
    }

    public Optional<UserModel> getById(Long id){
        return usuarioRepository.findById(id);
    }


    public ArrayList<UserModel>  getPorPrioridad(Integer prioridad) {
        return usuarioRepository.findByPrioridad(prioridad);
    }

    public boolean deleteUser(Long id) {
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch(Exception err) {
            return false;
        }
    }


    
}