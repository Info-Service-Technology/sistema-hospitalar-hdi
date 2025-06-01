package com.ist.hdi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ist.hdi.entities.Endereco;
import com.ist.hdi.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<Endereco> listarTodos(){
		return enderecoRepository.findAll();
	}
	
	public Optional<Endereco> buscarPorId(Long id){
		return enderecoRepository.findById(id);
	}
	
	public Endereco salvar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public void deletar(Long id) {
		enderecoRepository.deleteById(id);
	}
	
	
}
