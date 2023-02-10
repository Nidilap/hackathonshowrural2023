package com.sysagro.api;

import com.sysagro.anotacao.Autorizar;
import com.sysagro.modelo.servico.api.PessoaServicoAPI;
import com.sysagro.modelo.servico.api.VisitaServicoAPI;
import static com.sysagro.util.LogUtil.exibirInfo;
import io.swagger.v3.oas.annotations.Operation;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    private VisitaServicoAPI visitaServicoAPI;
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
    
    @GET
    @Autorizar
    @Path("/{id-pessoa}/visitas")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lista as visitas de uma pessoa")
    public Response listarVisitas(@PathParam("id-pessoa") Long idPessoa) {
        exibirInfo(getClass(), String.format("[GET] /pessoas/%d/visitas", idPessoa));
        return visitaServicoAPI.listarComFiltros(null, idPessoa);
    }
    
    // POST
}
