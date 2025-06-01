package com.ist.hdi.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="tb_consulta")
public class Consulta extends RepresentationModel<Consulta> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	
	@ManyToOne
	@JoinColumn(name= "medico_id")
	private Medico medico;
	
	@NotNull
	private LocalDateTime dataHora;
	
	@NotNull
	private String Diagnostico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getDiagnostico() {
		return Diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		Diagnostico = diagnostico;
	}

	public Consulta(Long id, Paciente paciente, Medico medico, LocalDateTime dataHora, String diagnostico) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.dataHora = dataHora;
		Diagnostico = diagnostico;
	}
	
	public Consulta() {
		
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
		Consulta other = (Consulta) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
