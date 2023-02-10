/*
 */
package com.sysagro.modelo.servico.api;

import com.sysagro.lambda.PessoaLambda;
import com.sysagro.modelo.dao.PessoaDAO;
import com.sysagro.modelo.dto.json.ErroJSON;
import com.sysagro.modelo.dto.json.PessoaJSON;
import com.sysagro.modelo.dto.json.RetornoJSON;
import com.sysagro.modelo.entidade.Pessoa;
import com.sysagro.modelo.fabrica.json.PessoaFabricaJSON;
import static com.sysagro.util.LogUtil.exibirErro;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Pedro
 */
public class PessoaServicoAPI implements Serializable {

    private static final long serialVersionUID = 9832893289238923L;
    
    @Inject
    private PessoaDAO pessoaDAO;
    @Inject
    private PessoaLambda pessoaLambda;
    @Inject
    private PessoaFabricaJSON pessoaFabricaJSON;

    // GET
    public Response listar() {
        try {
            List<Pessoa> pessoas = pessoaDAO.listarTodosAPI();
            return retornarJSON(pessoas);
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            return new RetornoJSON(Status.INTERNAL_SERVER_ERROR)
                .adicionarErro(new ErroJSON().servidor())
                .retornar();
        }
    }

    // Retorno de JSON
    public Response retornarJSON(Pessoa pessoa) {
        PessoaJSON json = pessoaFabricaJSON.criar(pessoa);
        return new RetornoJSON<>(Status.OK, json).retornar();
    }
    
    public Response retornarJSON(List<Pessoa> pessoas) {
        List<PessoaJSON> listaJSONS = pessoaLambda.mapearParaPessoasJSON(pessoas);
        return new RetornoJSON<>(Status.OK, listaJSONS).retornar();
    }
}