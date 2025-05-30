package com.ist.hdi.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ist.hdi.entities.Medico;
import com.ist.hdi.enums.Especialidade;
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

	public List<Medico> listarPorEspecialidade(Especialidade especialidade) {
	    return medicoRepository.findByEspecialidade(especialidade);
	}
	public List<Medico> listar(String nome,  String crm, String especialidade, String telefone, String email) {
		return medicoRepository.findAll().stream()
		        .filter(m -> nome == null || m.getNome().toLowerCase().contains(nome.toLowerCase()))
		        .filter(m -> crm == null || m.getCrm().equalsIgnoreCase(crm))
		        .filter(m -> especialidade == null || m.getEspecialidade().name().equalsIgnoreCase(especialidade))
		        .filter(m -> telefone == null || m.getTelefone().equalsIgnoreCase(telefone))
		        .filter(m -> email == null || m.getEmail().equalsIgnoreCase(email))		        
		        .collect(Collectors.toList());
	}

	
}
