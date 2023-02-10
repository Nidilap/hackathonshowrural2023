package com.sysagro.api;

import com.sysagro.anotacao.Autorizar;
import com.sysagro.modelo.servico.api.UsuarioServicoAPI;
import com.sysagro.modelo.servico.api.VisitaServicoAPI;
import io.swagger.v3.oas.annotations.Operation;
import static com.sysagro.util.LogUtil.exibirInfo;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Pedro
 */
@Path("/usuarios")
public class UsuarioAPI {

    @Inject
    private VisitaServicoAPI visitaServicoAPI;
    @Inject
    private UsuarioServicoAPI usuarioServicoAPI;
    
    // GET
    @GET
    @Autorizar
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lista os usuários")
    public Response listar() {
        exibirInfo(getClass(), "[GET] /usuarios");
        return usuarioServicoAPI.listar();
    }
    
    @GET
    @Autorizar
    @Path("/{id-usuario}/visitas")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lista as visitas do usuário")
    public Response listarVisitas(@PathParam("id-usuario") Long idUsuario) {
        exibirInfo(getClass(), String.format("[GET] /usuarios/%d/visitas", idUsuario));
        return visitaServicoAPI.listarComFiltros(idUsuario);
    }
    
    @GET
    @Autorizar
    @Path("/{id-usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Busca um usuário por ID")
    public Response buscar(@PathParam("id-usuario") Long idUsuario) {
        exibirInfo(getClass(), String.format("[GET] /usuarios/%d", idUsuario));
        return usuarioServicoAPI.buscarComFiltros(idUsuario);
    }

    // POST
    @POST
    @Path("/autenticar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Autentica o acesso do usuário")
    public Response autenticar(String json) {
        exibirInfo(getClass(), "[POST] /usuarios/autenticar");
        return usuarioServicoAPI.autenticar(json);
    }
}
