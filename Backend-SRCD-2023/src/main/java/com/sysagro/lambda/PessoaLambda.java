/*
 */
package com.sysagro.lambda;

import com.sysagro.modelo.dto.json.PessoaJSON;
import com.sysagro.modelo.entidade.Pessoa;
import com.sysagro.modelo.fabrica.json.PessoaFabricaJSON;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author Pedro
 */
public class PessoaLambda implements Serializable {

    private static final long serialVersionUID = 127891982981892129L;
    
    @Inject
    private PessoaFabricaJSON pessoaFabricaJSON;
    
    // Processamentos de listas
    public List<PessoaJSON> mapearParaPessoasJSON(List<Pessoa> pessoas) {
        List<PessoaJSON> lista = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(pessoas)) {
            pessoas.forEach(pessoa -> lista.add(pessoaFabricaJSON.criar(pessoa)));
        }
        return lista;
    }
    
    // Lambdas com parâmetros
}
