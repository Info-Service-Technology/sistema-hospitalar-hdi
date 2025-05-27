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
import org.springframework.web.bind.annotation.RestController;

import com.ist.hdi.Services.PacienteService;
import com.ist.hdi.entities.Paciente;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/pacientes", produces = "application/json")
@CrossOrigin(origins = "*")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        List<Paciente> pacientes = pacienteService.listarTodos();

        // Adiciona links HATEOAS a cada paciente
        pacientes = pacientes.stream().peek(paciente -> {
            Link selfLink = linkTo(methodOn(PacienteController.class).buscarPorId(paciente.getId())).withSelfRel();
            paciente.add(selfLink);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(pacientes);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id)
                .map(paciente -> {
                    Link selfLink = linkTo(methodOn(PacienteController.class).buscarPorId(id)).withSelfRel();
                    paciente.add(selfLink);
                    return ResponseEntity.ok(paciente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Paciente> salvar(@Valid @RequestBody Paciente paciente) {
        Paciente salvo = pacienteService.salvar(paciente);
        Link selfLink = linkTo(methodOn(PacienteController.class).buscarPorId(salvo.getId())).withSelfRel();
        salvo.add(selfLink);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Paciente> atualizar(@PathVariable Long id, @Valid @RequestBody Paciente paciente) {
        if (!pacienteService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        paciente.setId(id);
        Paciente atualizado = pacienteService.salvar(paciente);
        atualizado.add(linkTo(methodOn(PacienteController.class).buscarPorId(id)).withSelfRel());
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!pacienteService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pacienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
