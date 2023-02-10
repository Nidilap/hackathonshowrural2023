package com.sysagro.modelo.fabrica.json;

import com.sysagro.modelo.dto.json.EnderecoJSON;
import com.sysagro.modelo.entidade.Endereco;
import com.sysagro.modelo.entidade.PessoaEndereco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author Pedro
 */
public class EnderecoFabricaJSON implements Serializable {

    private static final long serialVersionUID = 981289129812981298L;
    
    @Inject
    private LocalizacaoFabricaJSON localizacaoFabricaJSON;

    // Geral
    public EnderecoJSON criar(Endereco endereco) {
        try {
            return new EnderecoJSON(
                endereco.getId(),
                (Objects.isNull(endereco.getCidade()) ? null : endereco.getCidade().getId()),
                endereco.isIsAtivo(),
                endereco.isIsPrincipal(),
                endereco.getNumero(),
                endereco.getEndereco(),
                endereco.getBairro(),
                endereco.getComplemento(),
                endereco.getCep(),
                endereco.getInscricaoEstadual(),
                endereco.getInscricaoMunicipal(),
                endereco.getCar(),
                endereco.getObservacao(),
                endereco.getAreaHa(),
                localizacaoFabricaJSON.criar(endereco.getLocalizacao()));
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<EnderecoJSON> criarLista(List<PessoaEndereco> vinculosEnderecos) {
        List<EnderecoJSON> listaJSONS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(vinculosEnderecos)) {
            vinculosEnderecos.forEach(vinculo -> listaJSONS.add(criar(vinculo.getEndereco())));
        }
        return listaJSONS;
    }
}
