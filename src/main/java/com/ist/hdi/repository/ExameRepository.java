package com.ist.hdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ist.hdi.entities.Exame;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {

}
