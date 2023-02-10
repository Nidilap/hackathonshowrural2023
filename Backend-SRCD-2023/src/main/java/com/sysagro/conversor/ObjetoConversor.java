package com.sysagro.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.Map;
import static java.util.Objects.isNull;

/**
 *
 * @author Pedro
 */
@FacesConverter(value = "objetoConversor")
public class ObjetoConversor implements Converter {

    // Geral
    @Override
    public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
        return (isNull(valor)) ? null : retornarAtributo(componente).get(valor);
    }

    @Override
    public String getAsString(FacesContext contexto, UIComponent componente, Object objeto) {
        if (isNull(objeto) || objeto.equals("")) {
            return (String) objeto;
        } else {
            Object entity = objeto;
            adicionarAtributo(componente, entity);
            Integer codigo = entity.hashCode();
            return String.valueOf(codigo);
        }
    }

    // Adiciona um componente na lista da página
    protected void adicionarAtributo(UIComponent componente, Object objeto) {
        String key = objeto.hashCode() + "";
        retornarAtributo(componente).put(key, objeto);
    }

    // Retorna os valores do componente
    protected Map<String, Object> retornarAtributo(UIComponent componente) {
        return componente.getAttributes();
    }
}