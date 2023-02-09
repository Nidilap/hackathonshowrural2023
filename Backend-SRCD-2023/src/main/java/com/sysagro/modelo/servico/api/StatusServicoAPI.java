/*
 */
package com.sysagro.modelo.servico.api;

import com.sysagro.modelo.dto.json.ErroJSON;
import com.sysagro.modelo.dto.json.RetornoJSON;
import static com.sysagro.util.LogUtil.exibirErro;
import java.io.Serializable;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Pedro
 */
public class StatusServicoAPI implements Serializable {

    private static final long serialVersionUID = 9128712878172871L;

    // GET
    public Response isOnline() {
        try {
            return new RetornoJSON<>(Status.OK).retornar();
        } catch (NullPointerException | ArithmeticException | UnsupportedOperationException ex) {
            exibirErro(getClass(), ex);
            return new RetornoJSON(Status.INTERNAL_SERVER_ERROR)
                .adicionarErro(new ErroJSON().servidor())
                .retornar();
        }
    }
}