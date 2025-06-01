package com.ist.hdi.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.ist.hdi.enums.SituacaoEpidemia;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_epidemiologia")
public class Epidemiologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String regiao;

    @NotNull
    private LocalDate dataRegistro;

    @NotNull
    private Integer casosConfirmados;

    private Integer casosSuspeitos;

    private Integer obitos;

    private Integer recuperados;

    @Enumerated(EnumType.STRING)
    private SituacaoEpidemia situacao;

    public Epidemiologia() {}

    public Epidemiologia(Long id, String regiao, LocalDate dataRegistro, Integer casosConfirmados, Integer casosSuspeitos, Integer obitos, Integer recuperados, SituacaoEpidemia situacao) {
        this.id = id;
        this.regiao = regiao;
        this.dataRegistro = dataRegistro;
        this.casosConfirmados = casosConfirmados;
        this.casosSuspeitos = casosSuspeitos;
        this.obitos = obitos;
        this.recuperados = recuperados;
        this.situacao = situacao;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Integer getCasosConfirmados() {
        return casosConfirmados;
    }

    public void setCasosConfirmados(Integer casosConfirmados) {
        this.casosConfirmados = casosConfirmados;
    }

    public Integer getCasosSuspeitos() {
        return casosSuspeitos;
    }

    public void setCasosSuspeitos(Integer casosSuspeitos) {
        this.casosSuspeitos = casosSuspeitos;
    }

    public Integer getObitos() {
        return obitos;
    }

    public void setObitos(Integer obitos) {
        this.obitos = obitos;
    }

    public Integer getRecuperados() {
        return recuperados;
    }

    public void setRecuperados(Integer recuperados) {
        this.recuperados = recuperados;
    }

    public SituacaoEpidemia getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEpidemia situacao) {
        this.situacao = situacao;
    }

    // hashCode e equals com base no ID

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Epidemiologia other = (Epidemiologia) obj;
        return Objects.equals(id, other.id);
    }
}
