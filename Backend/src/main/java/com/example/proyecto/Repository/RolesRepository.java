package com.example.proyecto.Repository;

import java.util.Optional;

import com.example.proyecto.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long>{
    
    Role findByName(String name);

}



