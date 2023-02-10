/*
 */
package com.sysagro.modelo.servico.api;

import com.google.gson.JsonParseException;
import com.sysagro.enumeracao.StatusAPIEnum;
import com.sysagro.excecao.ValidacaoExcecao;
import com.sysagro.lambda.VisitaLambda;
import com.sysagro.lambda.json.VisitaLambdaJSON;
import com.sysagro.modelo.dao.VisitaDAO;
import com.sysagro.modelo.dto.json.ErroJSON;
import com.sysagro.modelo.dto.json.RetornoJSON;
import com.sysagro.modelo.dto.json.VisitaJSON;
import com.sysagro.modelo.entidade.Visita;
import com.sysagro.modelo.fabrica.VisitaFabrica;
import com.sysagro.modelo.fabrica.json.VisitaFabricaJSON;
import com.sysagro.modelo.servico.VisitaServico;
import static com.sysagro.util.LogUtil.exibirErro;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Pedro
 */
public class VisitaServicoAPI implements Serializable {

    private static final long serialVersionUID = 1249812489218949889L;
    
    @Inject
    private VisitaDAO visitaDAO;
    @Inject
    private VisitaLambda visitaLambda;
    @Inject
    private VisitaLambdaJSON visitaLambdaJSON;
    @Inject
    private VisitaServico visitaServico;
    @Inject
    private VisitaFabrica visitaFabrica;
    @Inject
    private VisitaFabricaJSON visitaFabricaJSON;

    // GET
    public Response listar() {
        try {
            List<Visita> visitas = visitaDAO.listarTodosAPI();
            return retornarJSON(visitas);
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            return new RetornoJSON(Status.INTERNAL_SERVER_ERROR)
                .adicionarErro(new ErroJSON().servidor())
                .retornar();
        }
    }
    
    public Response listarComFiltros(Long idFuncionario, Long idPessoa) {
        try {
            List<Visita> visitas = visitaDAO.listarComFiltrosAPI(idFuncionario, idPessoa);
            return retornarJSON(visitas);
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            return new RetornoJSON(Status.INTERNAL_SERVER_ERROR)
                .adicionarErro(new ErroJSON().servidor())
                .retornar();
        }
    }

    // POST
    public Response criar(String json) {
        try {
            VisitaJSON visitaJSON = visitaLambdaJSON.mapearJSONParaObjeto(json);
            Visita visita = visitaFabrica.criar(visitaJSON);
            visitaServico.salvarPOST(visita);
            return retornarJSON(visita);
        } catch (BadRequestException | JsonParseException ex) {
            exibirErro(getClass(), ex);
            return new RetornoJSON(Status.BAD_REQUEST)
                .adicionarErro(new ErroJSON().sintaxeJSON())
                .retornar();
        } catch (ValidacaoExcecao ex) {
            exibirErro(getClass(), ex);
            return new RetornoJSON(StatusAPIEnum.UNPROCESSABLE_ENTITY)
                .adicionarErro(new ErroJSON(ex.retornarDescricaoCompleta()))
                .retornar();
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            return new RetornoJSON(Status.INTERNAL_SERVER_ERROR)
                .adicionarErro(new ErroJSON().servidor())
                .retornar();
        }
    }
    
    // Retorno de JSON
    public Response retornarJSON(Visita visita) {
        VisitaJSON json = visitaFabricaJSON.criar(visita);
        return new RetornoJSON<>(Status.OK, json).retornar();
    }
    
    public Response retornarJSON(List<Visita> visitas) {
        List<VisitaJSON> listaJSONS = visitaLambda.mapearParaVisitasJSON(visitas);
        return new RetornoJSON<>(Status.OK, listaJSONS).retornar();
    }
}