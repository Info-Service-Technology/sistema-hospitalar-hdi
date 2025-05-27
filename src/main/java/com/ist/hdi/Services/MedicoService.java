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
	private MedicoRepository medicoRepostory;
	
	public List<Medico> ListarTodos(){
		return medicoRepostory.findAll();
	}
	
	public Optional<Medico> buscarMedicoPorId(Long id) {
		return medicoRepostory.findById(id);
			
	}
	
	public Medico salvar(Medico medico) {
		return medicoRepostory.save(medico);
	}
	
	public void deletar(Long id) {
		medicoRepostory.deleteById(id);
	}
}
