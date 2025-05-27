package com.ist.hdi.enums;

public enum TipoEnfermidade {
	INFECCIOSA("Doença Infecciosa"),
    CRONICA("Doença Crônica"),
    TRANSMISSIVEL("Doença Transmissível"),
    GENETICA("Doença Genética"),
    AUTOIMUNE("Doença Autoimune"),
    NEOPLASIA("Neoplasia"),
    OUTROS("Outros");


    private final String descricao;

    TipoEnfermidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
