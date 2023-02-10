/*
 */
package com.sysagro.modelo.servico;

import com.google.gson.JsonObject;
import com.sysagro.modelo.dao.CidadeDAO;
import com.sysagro.modelo.entidade.Endereco;
import static com.sysagro.util.NumeroUtil.criarBigDecimal;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.script.ScriptException;

/**
 *
 * @author Pedro
 */
@Named
public class EnderecoServico implements Serializable {
    
    private static final long serialVersionUID = 271782781278217832L;
    
    @Inject
    private CidadeDAO cidadeDAO;
    @Inject
    private LocalizacaoServico localizacaoServico;
    
    // Criação
    public Endereco criarPropriedadeProdutorCAR(JsonObject jsonOBJ) throws ScriptException {
        // Variáveis
        JsonObject imovel = jsonOBJ.getAsJsonObject("imovel");
        // Criação
        Endereco endereco = new Endereco();
        endereco.setEndereco("NÃO IDENTIFICADO");
        endereco.setAreaHa(criarBigDecimal(imovel.get("area").getAsString()));
        endereco.setCar(jsonOBJ.get("car").getAsString());
        endereco.setLocalizacao(localizacaoServico.criarCAR(imovel.get("endereco_latitude").getAsString(), imovel.get("endereco_longitude").getAsString()));
        endereco.setCidade(cidadeDAO.buscarPorFiltros(imovel.get("endereco_municipio").getAsString(), imovel.get("endereco_uf").getAsString()));
        return endereco;
    }
}
