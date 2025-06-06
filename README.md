# sistema-hospitalar-hdi

## Descrição

Esta aplicação é uma API RESTful desenvolvida em Java com Spring Boot para gerenciar informações de pacientes. Ela permite criar, consultar, atualizar e deletar registros de pacientes, com validações robustas e suporte a HATEOAS para navegação entre recursos.

---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate Validator (Bean Validation)
- HATEOAS (para hiperlinks nos recursos)
- Banco de dados relacional (ex: MySQL, PostgreSQL)
- Swagger (OpenAPI) para documentação da API
- Insomnia para testes das requisições REST

---

## Estrutura da Entidade Paciente

A entidade `Paciente` representa o paciente no sistema com os seguintes atributos:

| Campo          | Tipo           | Descrição                                   | Validações                         |
|----------------|----------------|---------------------------------------------|-----------------------------------|
| `id`           | Long           | Identificador único gerado automaticamente | Não enviado na criação (auto-gen) |
| `nome`         | String         | Nome completo do paciente                    | Obrigatório, 3 a 60 caracteres    |
| `sexo`         | Enum `Sexo`    | Sexo do paciente (M ou F)                    | Obrigatório                       |
| `etnia`        | Enum `Etnia`   | Etnia do paciente                            | Obrigatório                       |
| `email`        | String         | Email válido do paciente                      | Obrigatório, formato email        |
| `dataNascimento`| LocalDate     | Data de nascimento                           | Obrigatório, deve ser data passada|
| `telefone`     | String         | Telefone de contato                          | Obrigatório                      |
| `tipo`         | Enum `TipoPaciente` | Tipo do paciente (ex: PADRAO, VIP)         | Obrigatório                      |

---

## Enumerações

- **Sexo:**  
  Valores aceitos: `"M"` (Masculino), `"F"` (Feminino)

- **Etnia:**  
  Exemplos: `"BRANCA"`, `"PARDA"`, `"PRETA"`, `"AMARELA"`, `"INDIGENA"`

- **TipoPaciente:**  
  Exemplos: `"PADRAO"`, `"VIP"`

---

## Endpoints Principais

| Método | Endpoint           | Descrição                      | Corpo da Requisição               |
|--------|--------------------|--------------------------------|----------------------------------|
| GET    | `/api/v1/pacientes` | Lista todos os pacientes       | -                                |
| GET    | `/api/v1/pacientes/{id}` | Busca paciente por ID        | -                                |
| POST   | `/api/v1/pacientes` | Cria um novo paciente          | JSON com dados do paciente       |
| PUT    | `/api/v1/pacientes/{id}` | Atualiza paciente existente   | JSON com dados atualizados       |
| DELETE | `/api/v1/pacientes/{id}` | Remove paciente               | -                                |

---

## Exemplo de Requisição POST (Criar Paciente)

```
{
  "nome": "João da Silva",
  "sexo": "Masculino",
  "etnia": "PA",
  "email": "joao.silva@email.com",
  "dataNascimento": "1985-07-20",
  "telefone": "(11) 98765-4321",
  "tipo": "PADRAO",
  "endereco": {
    "rua": "Rua das Flores, 123",
    "bairro": "Jardim Primavera",
    "cidade": "São Paulo",
    "uf": "SP",
    "cep": "01234-567"
  }
}
```
## Exemplo de Requisição GET (Listar Todos Pacientes)
```
[
	{
		"id": 1,
		"nome": "João da Silva",
		"sexo": "MASCULINO",
		"etnia": "PA",
		"email": "joao.silva@email.com",
		"dataNascimento": "1985-07-20",
		"telefone": "(11) 98765-4321",
		"tipo": "PADRAO",
		"endereco": {
			"id": 1,
			"rua": "Rua das Flores, 123",
			"bairro": "Jardim Primavera",
			"cidade": "São Paulo",
			"uf": "SP",
			"cep": "01234-567",
			"links": []
		},
		"links": [
			{
				"rel": "self",
				"href": "http://localhost:8080/api/v1/pacientes/1"
			}
		]
	}
]
```
