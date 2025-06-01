package com.ist.hdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ist.hdi.entities.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

}
