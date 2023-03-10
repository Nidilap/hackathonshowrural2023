/*
 */
package com.sysagro.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import static java.util.Objects.isNull;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 *
 * @author Pedro
 */
public final class NumeroUtil {

    // Construtor
    private NumeroUtil() {}

    // Geral
    public static boolean isMaiorQueZero(Long numero) {
        return Objects.nonNull(numero) && numero > 0L;
    }
    
    public static BigDecimal criarBigDecimal(String valor) {
        try {
            return new BigDecimal(valor);
        } catch (Exception ex) {
            return BigDecimal.ZERO;
        }
    }
    
    public static BigDecimal criarBigDecimal(Double valor) {
        return new BigDecimal(validarDouble(valor));
    }
    
    public static BigDecimal criarBigDecimal(Integer valor) {
        return new BigDecimal(validarInteger(valor));
    }
    
    public static BigDecimal criarBigDecimal(Double valor, Integer escala) {
        return new BigDecimal(validarDouble(valor)).setScale(escala, RoundingMode.HALF_EVEN);
    }
    
    public static BigDecimal validarBigDecimal(BigDecimal valor) {
        return isNull(valor) ? BigDecimal.ZERO : valor;
    }
    
    public static Double validarDouble(Double valor) {
        return isNull(valor) ? 0d : valor;
    }
    
    public static Integer validarInteger(Integer valor) {
        return isNull(valor) ? 0 : valor;
    }
    
    public static ScriptEngine retornarEngineCalculo() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        return scriptEngineManager.getEngineByName("JavaScript");
    }
}