package com.ist.hdi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ist.hdi.entities.Exame;
import com.ist.hdi.repository.ExameRepository;

@Service
public class ExameService {
	
	@Autowired
	private ExameRepository exameRepository;
	
	public List<Exame> listarTodos(){
		return exameRepository.findAll();
	}
	
	public Optional<Exame> buscarPorId(Long id){
		return exameRepository.findById(id);
	}
	
	public Exame salvar(Exame Exame) {
		return exameRepository.save(Exame);
	}
	
	public void deletar(Long id) {
		exameRepository.deleteById(id);
	}
}
