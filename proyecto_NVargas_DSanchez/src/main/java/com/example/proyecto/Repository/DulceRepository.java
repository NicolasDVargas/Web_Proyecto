package com.example.proyecto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.proyecto.model.Dulce;

@Repository
public interface DulceRepository extends  JpaRepository<Dulce, Long> {
    
    @Query(value = "select o from Dulce o where o.nombre = :nombre")
	Dulce getByNombre(String nombre);

    @Query(value = "select o from Dulce o where o.id = :id")
	Dulce getById(Long id);

    @Query(value = "select o from Dulce o where o.tipo = :tipo")
	List<Dulce> getByTipo(String tipo);

}
