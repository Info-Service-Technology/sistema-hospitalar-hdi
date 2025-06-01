package com.ist.hdi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ist.hdi.entities.Paciente;
import com.ist.hdi.repository.PacienteRepository;

@Service
public class PacienteService  {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	public List<Paciente> listarTodos(){
		return pacienteRepository.findAll();
	}
	
	public Optional<Paciente> buscarPorId(Long id){
		return pacienteRepository.findById(id);
	}
	
	public Paciente salvar(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}
	
	public void deletar(Long id) {
		pacienteRepository.deleteById(id);
	}
}
