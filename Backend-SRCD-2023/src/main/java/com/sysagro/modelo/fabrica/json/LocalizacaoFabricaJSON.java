package com.sysagro.modelo.fabrica.json;

import com.sysagro.modelo.dto.json.LocalizacaoJSON;
import com.sysagro.modelo.entidade.Localizacao;
import java.io.Serializable;

/**
 *
 * @author Pedro
 */
public class LocalizacaoFabricaJSON implements Serializable {

    private static final long serialVersionUID = 981289129812981298L;

    // Geral
    public LocalizacaoJSON criar(Localizacao localizacao) {
        try {
            return new LocalizacaoJSON(localizacao.getId(), localizacao.getDataHoraCriacao(), localizacao.getCoordenada());
        } catch (Exception ex) {
            return null;
        }
    }
}
