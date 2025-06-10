package com.ist.hdi.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Receita> receitas = new ArrayList<>();
	
	@OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private List<Exame> exames = new ArrayList<>();
	
	
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

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	
	
	public Consulta(Long id, @NotNull Paciente paciente, Medico medico, @NotNull LocalDateTime dataHora,
			@NotNull String diagnostico, List<Receita> receitas) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.dataHora = dataHora;
		Diagnostico = diagnostico;
		this.receitas = receitas;
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
