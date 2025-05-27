package com.ist.hdi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

import com.ist.hdi.enums.SituacaoEpidemia;

@Entity
@Table(name = "tb_epidemias")
public class Epidemia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "enfermidade_id")
    private Enfermidade enfermidade;

    @NotNull
    private String regiao;

    @NotNull
    private LocalDate inicio;

    @NotNull
    private LocalDate fim;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SituacaoEpidemia status; // Ex: Ativa, Controlada

    // getters e setters
    public Epidemia() {
    	
    }

	public Epidemia(Long id, @NotNull Enfermidade enfermidade, @NotNull String regiao, @NotNull LocalDate inicio,
			@NotNull LocalDate fim, @NotNull SituacaoEpidemia status) {
		super();
		this.id = id;
		this.enfermidade = enfermidade;
		this.regiao = regiao;
		this.inicio = inicio;
		this.fim = fim;
		this.status = status;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Epidemia other = (Epidemia) obj;
		return Objects.equals(id, other.id);
	}
    
    
}
