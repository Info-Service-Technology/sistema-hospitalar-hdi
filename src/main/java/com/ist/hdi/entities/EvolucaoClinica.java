package com.ist.hdi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_evolucao_clinica")
public class EvolucaoClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "internacao_id")
    private Internacao internacao;

    @NotNull
    private LocalDateTime dataRegistro;

    @NotNull
    private String descricao;

    // getters e setters
    public EvolucaoClinica() {
    	
    }

	public EvolucaoClinica(Long id, @NotNull Internacao internacao, @NotNull LocalDateTime dataRegistro,
			@NotNull String descricao) {
		super();
		this.id = id;
		this.internacao = internacao;
		this.dataRegistro = dataRegistro;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Internacao getInternacao() {
		return internacao;
	}

	public void setInternacao(Internacao internacao) {
		this.internacao = internacao;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		EvolucaoClinica other = (EvolucaoClinica) obj;
		return Objects.equals(id, other.id);
	}
    
    
    
}
