package com.example.proyecto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.example.proyecto.model.Cliente;

@Repository
public interface ClienteRepository extends  JpaRepository<Cliente, Long> {

    @Query(value = "select o from Cliente o where o.email = :email and o.contrasenna = :contrasenna")
	Optional<Cliente> findByEmailAndContrasenna(String email, String contrasenna);

	Optional<Cliente> findById(Long id);

}
