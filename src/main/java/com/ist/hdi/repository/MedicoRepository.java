package com.ist.hdi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ist.hdi.entities.Medico;
import com.ist.hdi.enums.Especialidade;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{

	List<Medico> findByEspecialidade(Especialidade especialidade);


}
