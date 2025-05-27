package com.ist.hdi.entities;

import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_prescricoes")
public class Prescricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;

    @NotNull
    private String posologia;

	public Prescricao(Long id, @NotNull Consulta consulta, @NotNull Medicamento medicamento, String posologia) {
		super();
		this.id = id;
		this.consulta = consulta;
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

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
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
		return Objects.hash(consulta, id, medicamento, posologia);
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
		return Objects.equals(consulta, other.consulta) && Objects.equals(id, other.id)
				&& Objects.equals(medicamento, other.medicamento) && Objects.equals(posologia, other.posologia);
	}
	
	

    // getters e setters
    
}
