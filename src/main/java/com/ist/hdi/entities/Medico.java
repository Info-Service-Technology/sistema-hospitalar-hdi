package com.ist.hdi.entities;

import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.ist.hdi.enums.Especialidade;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_medico")
public class Medico extends RepresentationModel<Medico> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=3, max = 100)
    private String nome;

    @NotNull
    private String crm;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @NotNull
    private String telefone;

    @NotNull
    @Email
    private String email;
    
    public Medico() {
    	
    }

	

	public Especialidade getEspecialidade() {
		return especialidade;
	}



	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	


	public Medico(Long id, @NotNull @Size(min = 3, max = 30) String nome, @NotNull String crm,
			@NotNull Especialidade especialidade, @NotNull String telefone, @NotNull @Email String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.crm = crm;
		this.especialidade = especialidade;
		this.telefone = telefone;
		this.email = email;
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

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		Medico other = (Medico) obj;
		return Objects.equals(id, other.id);
	}   

    
	
}
