package com.ist.hdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ist.hdi.entities.Paciente;

@Repository
public interface  PacienteRepository extends JpaRepository<Paciente, Long>{

}
