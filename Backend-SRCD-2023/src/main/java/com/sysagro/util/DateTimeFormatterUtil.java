/*
 */
package com.sysagro.util;

import com.sysagro.enumeracao.FusoHorarioEnum;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.apache.commons.lang3.time.DurationFormatUtils;

/**
 *
 * @author Pedro
 */
public final class DateTimeFormatterUtil {

    // Construtor
    private DateTimeFormatterUtil() {}

    // Geral
    public static DateTimeFormatter criar(String formato) {
        return DateTimeFormatter
            .ofPattern(formato);
    }
    
    public static DateTimeFormatter criar(String formato, Locale localidade) {
        return DateTimeFormatter
            .ofPattern(formato, localidade);
    }
    
    public static DateTimeFormatter criarComUTC(String formato) {
        return DateTimeFormatter
            .ofPattern(formato)
            .withZone(FusoHorarioEnum.UTC.getZoneId());
    }
    
    // Formatações
    public static String formatarDuration(Duration duration, String formato) {
        try {
            return DurationFormatUtils.formatDuration(Math.abs(duration.toMillis()), formato);
        } catch (Exception excecao) {
            return null;
        }
    }
}