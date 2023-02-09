/*
 */
package com.sysagro.util;

import java.time.Duration;
import java.time.temporal.Temporal;

/**
 *
 * @author Pedro
 */
public final class DurationUtil {

    // Construtor
    private DurationUtil() {}

    // Geral
    public static Duration calcularIntervalo(Temporal tempoInicial, Temporal tempoFinal) {
        try {
            return Duration.between(tempoInicial, tempoFinal);
        } catch (NullPointerException excecao) {
            return null;
        }
    }
}
