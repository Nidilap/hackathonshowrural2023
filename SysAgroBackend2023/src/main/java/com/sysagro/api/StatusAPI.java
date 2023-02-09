package com.sysagro.api;

import com.sysagro.modelo.servico.api.StatusServicoAPI;
import io.swagger.v3.oas.annotations.Operation;
import static com.sysagro.util.LogUtil.exibirInfo;
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
@Path("/status")
public class StatusAPI {

    @Inject
    private StatusServicoAPI statusServicoAPI;
    
    // GET
    @GET
    @Path("/online")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Indica se a API está online")
    public Response isOnline() {
        exibirInfo(getClass(), "[GET] /status/online");
        return statusServicoAPI.isOnline();
    }
}