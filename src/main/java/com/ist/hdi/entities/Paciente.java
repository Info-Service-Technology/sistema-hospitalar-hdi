package com.ist.hdi.entities;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.ist.hdi.enums.Etnia;
import com.ist.hdi.enums.Sexo;
import com.ist.hdi.enums.TipoPaciente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="tb_paciente")
public class Paciente extends RepresentationModel<Paciente> {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 60)
    private String nome;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Etnia etnia;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    @NotNull
    private String telefone;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoPaciente tipo;
    
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    // Getters e Setters
    public TipoPaciente getTipo() {
		return tipo;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Etnia getEtnia() {
		return etnia;
	}

	public void setEtnia(Etnia etnia) {
		this.etnia = etnia;
	}

	public void setTipo(TipoPaciente tipo) {
		this.tipo = tipo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
   

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Paciente() {
		
	}

	// Construtor com parâmetros (exemplo simplificado)
    public Paciente(@NotNull Long id, @NotNull @Size(min = 3, max = 60) String nome,
                    @NotNull Sexo sexo, @NotNull Etnia etnia, @NotNull @Email String email,
                    @NotNull @Past LocalDate dataNascimento, @NotNull String telefone,
                    @NotNull TipoPaciente tipo, @Valid Endereco endereco) {
        super();
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.etnia = etnia;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.tipo = tipo;
        this.endereco = endereco;
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
		Paciente other = (Paciente) obj;
		return Objects.equals(id, other.id);
	}
    
    
}
