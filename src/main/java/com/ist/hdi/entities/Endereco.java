package com.ist.hdi.entities;

import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_endereco")
public class Endereco  extends RepresentationModel<Endereco>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String rua;

    @NotBlank
    @Size(max = 50)
    private String bairro;

    @NotBlank
    @Size(max = 50)
    private String cidade;

    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;

    @NotBlank
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
    private String cep;

    // Construtor default
    public Endereco() {
        super();
    }

    // Construtor com parâmetros e super()
    public Endereco(@NotBlank @Size(max = 100) String rua,
                    @NotBlank @Size(max = 50) String bairro,
                    @NotBlank @Size(max = 50) String cidade,
                    @NotBlank @Size(min = 2, max = 2) String uf,
                    @NotBlank @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido") String cep) {
        super();
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    // hashCode e equals baseados no id

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
        Endereco other = (Endereco) obj;
        return Objects.equals(id, other.id);
    }
}
