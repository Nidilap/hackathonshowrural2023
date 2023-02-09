/*
 */
package com.sysagro.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 *
 * @author Pedro
 */
public final class LocalDateUtil {

    // Construtor
    private LocalDateUtil() {}

    // Conversores
    public static LocalDate converterParaPrimeiroDiaSemana(LocalDate ld) {
        return ld.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
    }
    
    public static Date converterParaDate(LocalDate ld) {
        return Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}