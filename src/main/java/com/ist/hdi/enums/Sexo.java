package com.ist.hdi.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sexo {
    M("MASCULINO"),
    F("FEMININO");

    private final String label;

    Sexo(String label) {
        this.label = label;
    }

    @JsonValue // Indica qual valor será usado na serialização
    public String getLabel() {
        return label;
    }

    @JsonCreator // Permite desserializar a partir do label
    public static Sexo fromLabel(String label) {
        return Arrays.stream(Sexo.values())
            .filter(e -> e.label.equalsIgnoreCase(label))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Valor inválido: " + label));
    }
}
