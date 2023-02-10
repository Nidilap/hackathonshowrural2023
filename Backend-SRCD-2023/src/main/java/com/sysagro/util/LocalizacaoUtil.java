/*
 */
package com.sysagro.util;

import static com.sysagro.util.LogUtil.exibirErro;
import static com.sysagro.util.NumeroUtil.retornarEngineCalculo;
import javax.script.ScriptException;

/**
 *
 * @author Pedro
 */
public final class LocalizacaoUtil {

    // Construtor
    private LocalizacaoUtil() {}

    // Geral
    public static double transformarLatitudeEmGrauDecimal(String latitude) throws ScriptException {
        try {
            boolean isSinalPositivo = (latitude.contains("N"));
            latitude = latitude.replace(",", ".").replace("\" ", "/3600)\"").replaceAll("N|S|\"", "")
                .replace("°","+(")
                .replace("'", "/60)+(");
            double longitudeCalculada = (double) retornarEngineCalculo().eval(latitude);
            return isSinalPositivo ? Math.abs(longitudeCalculada) : -Math.abs(longitudeCalculada);
        } catch (ScriptException ex) {
            exibirErro(LocalizacaoUtil.class, ex);
            throw ex;
        }
    }

    public static double transformarLongitudeEmGrauDecimal(String longitude) throws ScriptException {
        try {
            boolean isSinalPositivo = (longitude.contains("L") || longitude.contains("E"));
            longitude = longitude.replace(",", ".").replace("\" ", "/3600)\"").replaceAll("O|L|W|E|\"", "")
                .replace("°","+(")
                .replace("'", "/60)+(");
            double longitudeCalculada = (double) retornarEngineCalculo().eval(longitude);
            return isSinalPositivo ? Math.abs(longitudeCalculada) : -Math.abs(longitudeCalculada);
        } catch (ScriptException ex) {
            exibirErro(LocalizacaoUtil.class, ex);
            throw ex;
        }
    }
}