package com.ist.hdi.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="tb_exame")
public class Exame extends RepresentationModel<Exame> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull(message ="Data da emissão é obrigatória.")
	private LocalDateTime dataEmissao;

	@NotNull(message = "O nome é obrigatório")
	private String nome;
	
	@NotNull(message = "A descrição é obrigatória")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "consulta_id")
    @NotNull(message = "O ida consulta é obrigatório.")
    @JsonManagedReference
    private Consulta consulta;
    
    @Lob
    @NotNull
    private String resultado;  // pode ser texto extenso, laudo, valores, etc.

    public Exame() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDateTime dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exame other = (Exame) obj;
		return Objects.equals(id, other.id);
	}
    
    	
	

    
}
