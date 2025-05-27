package com.ist.hdi.entities;

import java.util.Objects;

import com.ist.hdi.enums.StatusQuarto;
import com.ist.hdi.enums.TipoQuarto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="tb_quarto")
public class Quarto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String numero;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoQuarto tipo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
    private StatusQuarto status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoQuarto getTipo() {
		return tipo;
	}

	public void setTipo(TipoQuarto tipo) {
		this.tipo = tipo;
	}

	public StatusQuarto getStatus() {
		return status;
	}

	public void setStatus(StatusQuarto status) {
		this.status = status;
	}

	public Quarto(Long id, @NotNull String numero, TipoQuarto tipo, StatusQuarto status) {
		super();
		this.id = id;
		this.numero = numero;
		this.tipo = tipo;
		this.status = status;
	}
	
	public Quarto() {
		
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
		Quarto other = (Quarto) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
