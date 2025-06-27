package com.desfid.api_rest_bdd.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import com.desfid.api_rest_bdd.models.UserModel;
import com.desfid.api_rest_bdd.services.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService usuarioService;

    @GetMapping()
    public List<UserModel> getAllUsers(){
        return usuarioService.getAllUsers();
    }

    @PostMapping()
    public UserModel saveUser(@RequestBody UserModel user){
        System.out.println(user);
        return this.usuarioService.saveUser(user);
    }

    @GetMapping( path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id) {
        return this.usuarioService.getById(id);
    }

    @DeleteMapping( path = "/{id}")
    public Boolean deleteById(@PathVariable("id") Long id){
        boolean response = this.usuarioService.deleteUser(id);
        return response;
    }

}