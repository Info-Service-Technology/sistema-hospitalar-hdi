package com.ist.hdi.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_medicamentos")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 30)
    private String nome;

    @NotNull
    private String dosagem;
    
    @NotNull
    private String efeitosColaterais;

    public Medicamento() {
    	
    }

	public Medicamento(Long id, String nome, String dosagem, String efeitosColaterais) {
		super();
		this.id = id;
		this.nome = nome;
		this.dosagem = dosagem;
		this.efeitosColaterais = efeitosColaterais;
	}

		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDosagem() {
		return dosagem;
	}

	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}

	public String getEfeitosColaterais() {
		return efeitosColaterais;
	}

	public void setEfeitosColaterais(String efeitosColaterais) {
		this.efeitosColaterais = efeitosColaterais;
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
		Medicamento other = (Medicamento) obj;
		return Objects.equals(id, other.id);
	}
    
    
}