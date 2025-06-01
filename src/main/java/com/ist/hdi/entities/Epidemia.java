package com.ist.hdi.entities;

import com.ist.hdi.enums.SituacaoEpidemia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_epidemias")
public class Epidemia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Enfermidade relacionada à epidemia.
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "enfermidade_id")
    private Enfermidade enfermidade;

    /**
     * Região afetada pela epidemia.
     */
    @NotNull
    private String regiao;

    /**
     * Data de início da epidemia.
     */
    @NotNull
    private LocalDate inicio;

    /**
     * Data prevista ou real de fim da epidemia.
     */
    @NotNull
    private LocalDate fim;

    /**
     * Status atual da epidemia.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private SituacaoEpidemia status;

    // Construtores
    public Epidemia() {
    }

    public Epidemia(Long id, @NotNull Enfermidade enfermidade, @NotNull String regiao, @NotNull LocalDate inicio,
                    @NotNull LocalDate fim, @NotNull SituacaoEpidemia status) {
        this.id = id;
        this.enfermidade = enfermidade;
        this.regiao = regiao;
        this.inicio = inicio;
        this.fim = fim;
        this.status = status;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enfermidade getEnfermidade() {
        return enfermidade;
    }

    public void setEnfermidade(Enfermidade enfermidade) {
        this.enfermidade = enfermidade;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public SituacaoEpidemia getStatus() {
        return status;
    }

    public void setStatus(SituacaoEpidemia status) {
        this.status = status;
    }

    // equals e hashCode baseados em id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Epidemia)) return false;
        Epidemia that = (Epidemia) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
