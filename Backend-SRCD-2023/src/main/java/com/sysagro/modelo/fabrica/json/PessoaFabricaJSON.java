package com.sysagro.modelo.fabrica.json;

import com.sysagro.modelo.dto.json.PessoaJSON;
import com.sysagro.modelo.entidade.Pessoa;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Pedro
 */
public class PessoaFabricaJSON implements Serializable {

    private static final long serialVersionUID = 984873478347834L;
    
    @Inject
    private AnexoFabricaJSON anexoFabricaJSON;
    @Inject
    private EnderecoFabricaJSON enderecoFabricaJSON;

    // Geral
    public PessoaJSON criar(Pessoa pessoa) {
        try {
            return new PessoaJSON(
                pessoa.getId(),
                pessoa.isIsAtivo(),
                pessoa.isIsProdutor(),
                pessoa.isIsFornecedor(),
                pessoa.isIsFuncionario(),
                pessoa.getRazaoSocial(),
                pessoa.getNomeFantasia(),
                pessoa.getNomePai(),
                pessoa.getNomeMae(),
                pessoa.getCpfCnpj(),
                pessoa.getRg(),
                pessoa.getTelefone(),
                pessoa.getCelular(),
                pessoa.getEmail(),
                pessoa.getAtividade(),
                pessoa.getObservacao(),
                pessoa.getDataHoraNascimento(),
                pessoa.getEstadoCivilEnum(),
                pessoa.getTipoPessoaEnum(),
                anexoFabricaJSON.criar(pessoa.getFoto()),
                enderecoFabricaJSON.criarLista(pessoa.getVinculosEnderecos()));
        } catch (Exception ex) {
            return null;
        }
    }
}
