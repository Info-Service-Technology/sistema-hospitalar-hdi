package com.ist.hdi.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_prescricoes")
public class Prescricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;

    @NotNull
    private String posologia;

	public Prescricao(Long id, @NotNull Receita receita, @NotNull Medicamento medicamento, String posologia) {
		super();
		this.id = id;
		this.receita = receita;
		this.medicamento = medicamento;
		this.posologia = posologia;
	}
	
	public Prescricao() {
		
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Receita getConsulta() {
		return receita;
	}

	public void setConsulta(Receita receita) {
		this.receita = receita;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(receita, id, medicamento, posologia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prescricao other = (Prescricao) obj;
		return Objects.equals(receita, other.receita) && Objects.equals(id, other.id)
				&& Objects.equals(medicamento, other.medicamento) && Objects.equals(posologia, other.posologia);
	}
	
	

    // getters e setters
    
}
