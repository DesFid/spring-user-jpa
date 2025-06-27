package com.desfid.api_rest_bdd.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Integer prioridad;

    public UserModel() {}

    public UserModel(String name, String email, Integer prior) {
        this.name = name;
        this.email = email;
        this.prioridad = prior;
    }

}