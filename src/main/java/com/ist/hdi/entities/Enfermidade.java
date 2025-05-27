package com.ist.hdi.entities;


import java.util.Objects;

import com.ist.hdi.enums.TipoEnfermidade;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_enfermidade")
public class Enfermidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoEnfermidade tipo;

    @NotNull
    private String codigoCID;
    
    public Enfermidade() {
    	
    }    
    

    public Enfermidade(Long id, String nome, TipoEnfermidade tipo, String codigoCID) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.codigoCID = codigoCID;
	}



	// getters e setters
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

	public TipoEnfermidade getTipo() {
		return tipo;
	}

	public void setTipo(TipoEnfermidade tipo) {
		this.tipo = tipo;
	}

	public String getCodigoCID() {
		return codigoCID;
	}

	public void setCodigoCID(String codigoCID) {
		this.codigoCID = codigoCID;
	}


	@Override
	public int hashCode() {
		return Objects.hash(codigoCID, id, nome, tipo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enfermidade other = (Enfermidade) obj;
		return Objects.equals(codigoCID, other.codigoCID) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && tipo == other.tipo;
	}

    
    
}
