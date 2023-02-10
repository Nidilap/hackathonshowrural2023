/*
 */
package com.sysagro.modelo.servico;

import com.sysagro.excecao.ValidacaoExcecao;
import com.sysagro.modelo.dao.VisitaDAO;
import com.sysagro.modelo.entidade.Visita;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
public class VisitaServico implements Serializable {
    
    private static final long serialVersionUID = 981249812894918234L;

    @Inject
    private VisitaDAO visitaDAO;
   
    // Salvamento
    public void salvar(Visita visita) throws ValidacaoExcecao, Exception {
        if (!visitaDAO.salvar(visita)) {
            throw new IllegalStateException();
        }
    }
    
    public void salvarPOST(Visita visita) throws Exception {
        salvar(visita);
    }
}
