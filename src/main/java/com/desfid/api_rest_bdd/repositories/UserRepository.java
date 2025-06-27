package com.desfid.api_rest_bdd.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desfid.api_rest_bdd.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    public abstract ArrayList<UserModel> findByPrioridad(Integer prioridad);
}