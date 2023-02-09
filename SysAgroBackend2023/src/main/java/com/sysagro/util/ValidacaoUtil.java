/*
 */
package com.sysagro.util;

import static com.sysagro.util.TextoUtil.traduzirIngles;
import java.util.Objects;

/**
 *
 * @author Pedro
 */
public final class ValidacaoUtil {

    // Construtor
    private ValidacaoUtil() {}
    
    // Geral
    public static void validarParametroObrigatorio(Object parametro, String nome) {
        Objects.requireNonNull(parametro, (String.format(traduzirIngles("validacao.mensagem.erro.parametroObrigatorio"), nome)));
    }
}