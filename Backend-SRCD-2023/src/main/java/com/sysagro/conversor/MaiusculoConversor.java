/* @Pedro
   -----
   Esse Converter já se encarrega de fazer um TRIM no valor também
*/
package com.sysagro.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import static java.util.Objects.isNull;

/**
 *
 * @author Pedro
 */
@FacesConverter("upperCaseConverter")
public class MaiusculoConversor implements Converter {

    // Geral
    @Override
    public Object getAsObject(FacesContext contexto, UIComponent componente, String valorSubmetido) {
        return (isNull(valorSubmetido)) ? null : valorSubmetido.toUpperCase().trim();
    }

    @Override
    public String getAsString(FacesContext contexto, UIComponent componente, Object objeto) {
        return (isNull(objeto)) ? "" : objeto.toString();
    }
}
