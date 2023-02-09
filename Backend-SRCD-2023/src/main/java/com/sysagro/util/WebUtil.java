/*
 */
package com.sysagro.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Pedro
 */
public final class WebUtil {

    // Construtor
    private WebUtil() {}

    // JSF
    public static void adicionarAtributoSessao(String chave, Object valor) {
        if (nonNull(FacesContext.getCurrentInstance())) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(chave, valor);
        }
    }
    
    public static Object converterParaObjeto(String chave) {
        if (isNull(FacesContext.getCurrentInstance())) {
            return null;
        } else {
            return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(chave);
        }
    }
    
    public static Long converterParaLongURL(String parametro) {
        try {
            HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String parametroURL = req.getParameter(parametro);
            return Long.parseLong(parametroURL);
        } catch (NumberFormatException | NullPointerException ex) {
            return null;
        }
    }
    
    public static void addMensagemInfo(String msg) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    public static void addMensagemInfoDetalhada(String msg, String detail) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, detail);
		FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    public static void addMensagemErro(String msg) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    public static void addMensagemErroDetalhada(String msg, String detail) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, detail);
		FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    public static FacesMessage criarMensagem(String titulo) {
        return new FacesMessage(titulo);
    }
    
    public static FacesMessage criarMensagem(String titulo, String detalhe) {
        return new FacesMessage(titulo, detalhe);
    }
    
    public static FacesMessage criarMensagem(String titulo, String detalhe, Severity tipo) {
        return new FacesMessage(tipo, titulo, detalhe);
    }
    
    // JS
    public static void executarJS(String codeJS) {
        PrimeFaces.current().executeScript(codeJS);
	}
    
    public static void pressionarBotaoJS(String idBotao) {
        executarJS(String.format("$('#%s').click()", idBotao));
	}
    
    public static void abrirDialogJS(String dialogWV) {
        executarJS(String.format("PF('%s').show()", dialogWV));
    }
    
    public static void fecharDialogJS(String dialogWV) {
        executarJS(String.format("PF('%s').hide()", dialogWV));
    }

	public static void executarUpdateJSF(String component) {
        PrimeFaces.current().ajax().update(component);
	}
    
    public static void recarregarTelaJS() {
        executarJS("location.reload()");
    }
}