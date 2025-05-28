package com.ist.hdi.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ist.hdi.entities.Medico;
import com.ist.hdi.repository.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;
	
	public List<Medico> listarTodos(){
		return medicoRepository.findAll();
	}
	
	public Optional<Medico> buscarPorId(Long id) {
		return medicoRepository.findById(id);
			
	}
	
	public Medico salvar(Medico medico) {
		return medicoRepository.save(medico);
	}
	
	public void deletar(Long id) {
		medicoRepository.deleteById(id);
	}

	public List<Medico> listarPorEspecialidade(String especialidade) {
	    return medicoRepository.findByEspecialidadeContainingIgnoreCase(especialidade);
	}


}
