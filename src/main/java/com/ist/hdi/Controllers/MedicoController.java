package com.ist.hdi.Controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ist.hdi.Services.MedicoService;
import com.ist.hdi.entities.Medico;
import com.ist.hdi.enums.Especialidade;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/api/v1/medicos", produces="application/json")
@CrossOrigin(origins = "*")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;
	
	@GetMapping
	public ResponseEntity<List<Medico>> listar(
	        @RequestParam(required = false) String nome,
	        @RequestParam(required = false) String crm,
	        @RequestParam(required = false) String especialidade,
			@RequestParam(required = false) String telefone,
			@RequestParam(required = false) String email){

	    List<Medico> medicos = medicoService.listar(nome, crm, especialidade, telefone, email);

	    // HATEOAS
	    medicos = medicos.stream().peek(medico -> {
	        Link selfLink = linkTo(methodOn(MedicoController.class).buscarPorId(medico.getId())).withSelfRel();
	        medico.add(selfLink);
	    }).collect(Collectors.toList());

	    return ResponseEntity.ok(medicos);
	}

	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Medico> buscarPorId(@PathVariable Long id){
		return medicoService.buscarPorId(id)
				.map(medico -> {
                    Link selfLink = linkTo(methodOn(MedicoController.class).buscarPorId(medico.getId())).withSelfRel();
                    medico.add(selfLink);
                    return ResponseEntity.ok(medico);
                })
                .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Medico> salvar(@Valid @RequestBody Medico medico){
		Medico salvarMedico = medicoService.salvar(medico);
		Link selfLink = linkTo(methodOn(MedicoController.class).buscarPorId(salvarMedico.getId())).withSelfRel();
		salvarMedico.add(selfLink);
		return ResponseEntity.ok(salvarMedico);
	}
	
	@PutMapping(path = "/{id}", consumes = "application/json")
	public ResponseEntity<Medico> atualizar(@PathVariable Long id,@Valid @RequestBody Medico medico) {
		if(!medicoService.buscarPorId(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		medico.setId(id);
		Medico medicoAtualizado = medicoService.salvar(medico);
		medicoAtualizado.add (linkTo(methodOn(MedicoController.class).buscarPorId(medicoAtualizado.getId())).withSelfRel());
		return ResponseEntity.ok(medicoAtualizado);	
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		if(!medicoService.buscarPorId(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/especialidade/{especialidade}")
	public ResponseEntity<List<Medico>> listarPorEspecialidade(
	        @PathVariable Especialidade especialidade) {
	    
	    List<Medico> medicos;

	    if (especialidade != null) {
	        medicos = medicoService.listarPorEspecialidade(especialidade);
	    } else {
	        medicos = medicoService.listarTodos();
	    }

	    return ResponseEntity.ok(medicos);
	}

	
}
