package com.ist.hdi.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ist.hdi.entities.Endereco;
import com.ist.hdi.services.EnderecoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/enderecos")
public class EnderecoController {

    @Autowired
	private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> listarTodos() {
        List<Endereco> enderecos = enderecoService.listarTodos();

        // Adiciona links HATEOAS a cada endereco
        enderecos = enderecos.stream().peek(endereco -> {
            Link selfLink = linkTo(methodOn(EnderecoController.class).buscarPorId(endereco.getId())).withSelfRel();
            endereco.add(selfLink);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(enderecos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
        return enderecoService.buscarPorId(id)
                .map(endereco -> {
                    Link selfLink = linkTo(methodOn(EnderecoController.class).buscarPorId(id)).withSelfRel();
                    endereco.add(selfLink);
                    return ResponseEntity.ok(endereco);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Endereco> salvar(@Valid @RequestBody Endereco endereco) {
    	Endereco salvo = enderecoService.salvar(endereco);
        Link selfLink = linkTo(methodOn(EnderecoController.class).buscarPorId(salvo.getId())).withSelfRel();
        salvo.add(selfLink);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @Valid @RequestBody Endereco endereco) {
        if (!enderecoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        endereco.setId(id);
        Endereco atualizado = enderecoService.salvar(endereco);
        atualizado.add(linkTo(methodOn(EnderecoController.class).buscarPorId(id)).withSelfRel());
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!enderecoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        enderecoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
	
}
