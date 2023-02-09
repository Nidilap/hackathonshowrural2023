/*
 */
package com.sysagro.util;

import java.util.Objects;

/**
 *
 * @author Pedro
 */
public final class RegistroUtil {

    // Construtor
    private RegistroUtil() {}
    
    // Geral
    public static boolean isNovo(Long id) {
        return Objects.isNull(id) || id <= 0L;
    }
}