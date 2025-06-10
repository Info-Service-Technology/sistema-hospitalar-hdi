package com.ist.hdi.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ist.hdi.entities.Exame;
import com.ist.hdi.services.ExameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/api/v1/exames")
@CrossOrigin(origins="*")
public class ExameController {

	@Autowired
	private ExameService exameService;
	
	@Operation(summary = "Listar exames", description = "Retorna todos os exames cadastrados")
	@ApiResponse(responseCode = "200", description = "Exames retornados com sucesso")
	@ApiResponse(responseCode = "400", description = "Requisição inválida")
	@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	@GetMapping
    public ResponseEntity<List<Exame>> listarTodos() {
        List<Exame> exames = exameService.listarTodos();

        // Adiciona links HATEOAS a cada Exame
        exames = exames.stream().peek(exame -> {
            Link selfLink = linkTo(methodOn(ExameController.class).buscarPorId(exame.getId())).withSelfRel();
            exame.add(selfLink);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(exames);
    }

	@Operation(summary = "Buscar exame por ID",description = "Retorna um exame com base no ID fornecido")
	@ApiResponse(responseCode = "200", description = "Exame encontrado")
	@ApiResponse(responseCode = "400", description = "ID inválido")
	@ApiResponse(responseCode = "404", description = "Exame não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	@GetMapping(path = "/{id}")
	public ResponseEntity<Exame> buscarPorId(@PathVariable Long id) {
	    return exameService.buscarPorId(id)
	            .map(exame -> {
	                Link selfLink = linkTo(methodOn(ExameController.class).buscarPorId(id)).withSelfRel();
	                exame.add(selfLink);
	                return ResponseEntity.ok(exame); // corrigido aqui
	            })
	            .orElse(ResponseEntity.notFound().build());
	}
			

	
	@Operation(summary = "Cadastrar exame", description = "Insere um novo exame na base de dados")
	@ApiResponses(value = {
	@ApiResponse(responseCode = "201", description = "Exame criado com sucesso"),
	@ApiResponse(responseCode = "400", description = "Requisição inválida"),
	@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@PostMapping
	public ResponseEntity<Exame> salvar(@Valid @RequestBody Exame exame) {
	    Exame salvarExame = exameService.salvar(exame);
	    Link selfLink = linkTo(methodOn(ExameController.class).buscarPorId(salvarExame.getId())).withSelfRel();
	    salvarExame.add(selfLink);

	    URI location = URI.create(selfLink.getHref());
	    return ResponseEntity.created(location).body(salvarExame);
	}

	
}
