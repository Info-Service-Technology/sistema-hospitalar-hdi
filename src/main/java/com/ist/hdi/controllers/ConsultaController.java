package com.ist.hdi.controllers;

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
import org.springframework.web.bind.annotation.RequestBody; // CORRETO
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.ist.hdi.entities.Consulta;
import com.ist.hdi.services.ConsultaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/api/v1/consultas")
@CrossOrigin(origins = "*")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;
    
    @GetMapping
    @Operation(summary = "Listar consultas", description = "Retorna todas as consultas cadastradas")
    @ApiResponse(responseCode = "200", description = "Consultas lsitadas com Sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<List<Consulta>> listarTodas() {
        List<Consulta> consultas = consultaService.listarTodas();

        // Adiciona links HATEOAS a cada consulta
        consultas = consultas.stream().peek(consulta -> {
            Link selfLink = linkTo(methodOn(ConsultaController.class).buscarPorId(consulta.getId())).withSelfRel();
            consulta.add(selfLink);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(consultas);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Buscar consulta por ID", description = "Retorna uma consulta específica")
    @ApiResponse(responseCode = "200", description = "Consulta encontrada")
    @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        return consultaService.buscarPorId(id)
                .map(consulta -> {
                    Link selfLink = linkTo(methodOn(ConsultaController.class).buscarPorId(id)).withSelfRel();
                    consulta.add(selfLink);
                    return ResponseEntity.ok(consulta);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar consulta", description = "Cadastra uma nova consulta")
    @ApiResponse(responseCode = "201", description = "Consulta criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição")
    @ApiResponse(responseCode = "404", description = "Paciente ou médico não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Consulta> salvar(@Valid @RequestBody Consulta consulta) {
        Consulta consultaSalva = consultaService.salvar(consulta);
        
        UriComponents uriComponents = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(consultaSalva.getId());

        return ResponseEntity
            .created(uriComponents.toUri())
            .body(consultaSalva);
    }
    
    @PutMapping(path = "/{id}")
    @Operation(summary = "Atualizar consulta", description = "Atualiza uma consulta existente")
    @ApiResponse(responseCode = "200", description = "Consulta atualizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição")
    @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, @Valid @RequestBody Consulta consulta) {
        if (!consultaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        consulta.setId(id);
        Consulta atualizada = consultaService.salvar(consulta);
        atualizada.add(linkTo(methodOn(ConsultaController.class).buscarPorId(id)).withSelfRel());
        return ResponseEntity.ok(atualizada);
    }
    
    @DeleteMapping(path ="/{id}")
    @Operation(summary = "Excluir consulta", description = "Remove uma consulta do sistema")
    @ApiResponse(responseCode = "204", description = "Consulta excluída com sucesso")
    @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(!consultaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        consultaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}