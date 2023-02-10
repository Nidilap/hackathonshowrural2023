package com.sysagro.api;

import com.sysagro.anotacao.Autorizar;
import com.sysagro.modelo.servico.api.PessoaServicoAPI;
import static com.sysagro.util.LogUtil.exibirInfo;
import io.swagger.v3.oas.annotations.Operation;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Pedro
 */
@Path("/pessoas")
public class PessoaAPI {
    
    @Inject
    private PessoaServicoAPI pessoaServicoAPI;
    
    // GET
    @GET
    @Autorizar
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lista as pessoas")
    public Response listar() {
        exibirInfo(getClass(), "[GET] /pessoas");
        return pessoaServicoAPI.listar();
    }
    
    // POST
}
