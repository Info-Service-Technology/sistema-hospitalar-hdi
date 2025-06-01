package com.ist.hdi.enums;

/**
 * Representa a situação atual de uma epidemia.
 */
public enum SituacaoEpidemia {

    ATIVA,             // Casos em alta, epidemia em andamento
    CONTROLADA,        // Casos sob controle, mas ainda monitorados
    ERRADICADA,        // Nenhum caso ativo, considerada extinta
    EM_OBSERVACAO,     // Há indícios, mas não confirmada
    SURTO_LOCALIZADO,  // Casos concentrados em uma área específica
    PANDEMIA,          // Atingiu múltiplas regiões e países
    ENDEMICIA          // Casos persistentes e regulares em uma região
}
