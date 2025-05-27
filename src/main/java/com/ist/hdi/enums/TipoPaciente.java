package com.ist.hdi.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoPaciente {
    
    P("PADRAO"),
    V("VIP");

    private final String label;

    TipoPaciente(String label) {
        this.label = label;
    }

    @JsonValue // Indica qual valor será usado na serialização
    public String getLabel() {
        return label;
    }

    @JsonCreator // Permite desserializar a partir do label
    public static TipoPaciente fromLabel(String label) {
        return Arrays.stream(TipoPaciente.values())
            .filter(e -> e.label.equalsIgnoreCase(label))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Valor inválido: " + label));
    }
}
