/*
 */
package com.sysagro.modelo.servico;

import com.google.gson.JsonObject;
import com.sysagro.enumeracao.TipoPessoaEnum;
import com.sysagro.modelo.dao.CidadeDAO;
import com.sysagro.modelo.dao.EnderecoDAO;
import com.sysagro.modelo.dao.PessoaDAO;
import com.sysagro.modelo.entidade.Endereco;
import com.sysagro.modelo.entidade.Pessoa;
import com.sysagro.modelo.entidade.PessoaEndereco;
import com.sysagro.util.ArquivoUtil;
import static com.sysagro.util.ArquivoUtil.converterParaConteudo;
import static com.sysagro.util.ArquivoUtil.retornarInputStreamSource;
import static com.sysagro.util.ConfiguracaoUtil.CAMINHO_CADASTRO_AMBIENTAL_RURAL;
import static com.sysagro.util.JsonUtil.transformarEmArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author Pedro
 */
@Named
public class PessoaServico implements Serializable {
    
    private static final long serialVersionUID = 98348923894892389L;

    @Inject
    private PessoaDAO pessoaDAO;
    @Inject
    private EnderecoDAO enderecoDAO;
    @Inject
    private EnderecoServico enderecoServico;
    
    // Geral
    public void atualizarCadastroComCAR() throws Exception {
        // Dados do Cadastro Ambiental Rural (CAR)
        String jsonCAR = converterParaConteudo(retornarInputStreamSource(ArquivoUtil.class, CAMINHO_CADASTRO_AMBIENTAL_RURAL));
        List<JsonObject> jsonOBJS = transformarEmArray(jsonCAR);
        if (CollectionUtils.isNotEmpty(jsonOBJS)) {
            List<Pessoa> pessoas = new ArrayList<>();
            List<Endereco> enderecos = new ArrayList<>();
            List<PessoaEndereco> pessoasEnderecos = new ArrayList<>();
            for (JsonObject jsonOBJ : jsonOBJS) {
                String car = jsonOBJ.get("car").getAsString();
                Endereco enderecoBD = enderecoDAO.buscarPorCAR(car);
                // Propriedade com CAR já existente no banco
                if (Objects.nonNull(enderecoBD)) {
                    continue;
                }
                // Criação
                Pessoa pessoa = criarProdutorCAR(jsonOBJ);
                Endereco endereco = enderecoServico.criarPropriedadeProdutorCAR(jsonOBJ);
                PessoaEndereco pessoaEndereco = new PessoaEndereco(endereco, pessoa);
                pessoas.add(pessoa);
                enderecos.add(endereco);
                pessoasEnderecos.add(pessoaEndereco);
            }
            // Salvando
            if (!pessoaDAO.salvarCompleto(pessoas, enderecos, pessoasEnderecos)) {
                throw new IllegalStateException();
            }
        }
    }
    
    // Criação
    private Pessoa criarProdutorCAR(JsonObject jsonOBJ) {
        Pessoa pessoa = new Pessoa();
        pessoa.setRazaoSocial("PESSOA DESCONHECIDA");
        pessoa.setIsProdutor(true);
        pessoa.setTipoPessoaEnum(TipoPessoaEnum.IND);
        return pessoa;
    }
}
