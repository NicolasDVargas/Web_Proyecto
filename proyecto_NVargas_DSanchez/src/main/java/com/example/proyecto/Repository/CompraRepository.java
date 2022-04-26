package com.example.proyecto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.proyecto.model.Compra;

@Repository
public interface CompraRepository extends  JpaRepository<Compra, Long> {

    @Query(value = "select o from Compra o where o.id = :id")
	Compra getById(Long id);
    
    @Query(value = "select o from Compra o where o.Propietario.id = :Propietario")
	List<Compra> getByIdPropietario(Long Propietario);

    List<Compra> findByFechaCompra(String fechaCompra);
    
}
