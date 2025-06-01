package com.ist.hdi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ist.hdi.entities.Consulta;
import com.ist.hdi.repository.ConsultaRepository;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	public List<Consulta> listarTodas(){
		return consultaRepository.findAll();
	}
	
	public Optional<Consulta> buscarPorId(Long id){
		return consultaRepository.findById(id);
	}
	
	public Consulta salvar(Consulta consulta) {
		return consultaRepository.save(consulta);
	}
	
	public void deletar(Long id) {
		consultaRepository.deleteById(id);
	}
}
