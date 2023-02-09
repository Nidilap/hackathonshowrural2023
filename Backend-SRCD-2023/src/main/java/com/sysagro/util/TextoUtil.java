/*
 */
package com.sysagro.util;

import javax.faces.context.FacesContext;
import java.util.Locale;
import static java.util.Objects.isNull;
import java.util.ResourceBundle;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Pedro
 */
public final class TextoUtil {

    // Construtor
    private TextoUtil() {}

    // Geral
    public static String extrairSomenteNumeros(String string) {
        if (isNull(string)) {
            return "";
        }
        return string.replaceAll("\\D", "");
    }
    
    public static String converterMaiusculo(String string) {
        return StringUtils.isBlank(string) ? null : string.toUpperCase().trim();
    }
    
    // I18n
    public static String traduzir(String label) {
        try {
            Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            return ResourceBundle.getBundle("i18n.labels", locale).getString(label);
        } catch (Exception ex) {
            return traduzirIngles(label);
        }
    }
    
    public static String traduzirIngles(String label) {
        try {
            return ResourceBundle.getBundle("i18n.labels", Locale.ENGLISH).getString(label);
        } catch (Exception ex) {
            return "N/D";
        }
    }
}