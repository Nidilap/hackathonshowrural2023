/*
 */
package com.sysagro.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *
 * @author Pedro
 */
public final class LocalDateTimeUtil {

    // Construtor
    private LocalDateTimeUtil() {}
    
    // Geral
    public static String formatarDataHoraAtualPTBR() {
        return DateTimeFormatterUtil.criar("dd-MM-yyyy HH-mm").format(LocalDateTime.now());
    }
    
    public static LocalDateTime criar(String valor, String formato) {
        return LocalDateTime.parse(valor, DateTimeFormatterUtil.criar(formato));
    }
    
    public static ZonedDateTime converterParaZonedDateTime(LocalDateTime ldt) {
        return ldt.atZone(ZoneId.systemDefault());
    }
    
    public static ZonedDateTime converterParaZonedDateTime(LocalDateTime ldt, ZoneId fusoHorario) {
        return ldt.atZone(fusoHorario);
    }
}