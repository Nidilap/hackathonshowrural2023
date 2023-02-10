package com.sysagro.api;

import com.sysagro.modelo.servico.api.SolicitacaoPessoaServicoAPI;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 *
 * @author Pedro
 */
@Path("/solicitacoes-pessoas")
public class SolicitacaoPessoaAPI {
    
    @Inject
    private SolicitacaoPessoaServicoAPI solicitacaoPessoaServicoAPI;
    
    // GET
    // POST
}
