/*
 */
package com.sysagro.util;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 *
 * @author Pedro
 */
public final class ZonedDateTimeUtil {

    // Construtor
    private ZonedDateTimeUtil() {}
    
    // Conversores
    public static LocalDate converterParaLocalDate(ZonedDateTime zdt) {
        return zdt.toLocalDate();
    }
}