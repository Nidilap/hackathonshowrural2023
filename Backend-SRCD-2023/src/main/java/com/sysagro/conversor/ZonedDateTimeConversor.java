package com.sysagro.conversor;

import com.sysagro.util.LocalDateTimeUtil;
import com.sysagro.util.DateTimeFormatterUtil;
import com.sysagro.util.TextoUtil;
import com.sysagro.util.WebUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.time.DateTimeException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import static java.util.Objects.isNull;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Pedro
 */
@FacesConverter(value = "zonedDateTimeConversor", forClass = ZonedDateTime.class)
public class ZonedDateTimeConversor implements Converter {

    // Geral
    @Override
    public String getAsString(FacesContext contexto, UIComponent componente, Object objeto) {
        String formato = "";
        try {
            formato = retornarFormatoDataHora(componente);
            if (isNull(objeto)) {
                return "";
            }
            if (objeto instanceof ZonedDateTime) {
                return DateTimeFormatterUtil.criar(formato).format((ZonedDateTime) objeto);
            } else {
                throw new DateTimeException("");
            }
        } catch (DateTimeException excecao) {
            throw new ConverterException(WebUtil.criarMensagem(
                String.format(TextoUtil.traduzirIngles("validacao.mensagem.erro.data.formatacaoInvalida"), objeto.toString(), formato)
            ));
        } catch (Exception excecao) {
            throw excecao;
        }
    }

    @Override
    public Object getAsObject(FacesContext contexto, UIComponent componente, String valorSubmetido) {
        String formato = "";
        try {
            formato = retornarFormatoDataHora(componente);
            if (StringUtils.isBlank(valorSubmetido)) {
                return null;
            }
            return LocalDateTimeUtil.converterParaZonedDateTime(LocalDateTimeUtil.criar(valorSubmetido, formato));
        } catch (DateTimeParseException excecao) {
            throw new ConverterException(WebUtil.criarMensagem(
                String.format(TextoUtil.traduzirIngles("validacao.mensagem.erro.data.parseInvalido"), valorSubmetido, formato)
            ));
        } catch (Exception excecao) {
            throw excecao;
        }
    }

    // Utilitários
    private String retornarFormatoDataHora(UIComponent componente) {
        String formatoDataHora = (String) componente.getAttributes().get("pattern");
        if (isNull(formatoDataHora)) {
            throw new IllegalArgumentException(TextoUtil.traduzirIngles(String.format("validacao.mensagem.erro.valorObrigatorio", "pattern")));
        }
        return formatoDataHora;
    }
}
