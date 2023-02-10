package com.sysagro.modelo.fabrica;

import com.sysagro.modelo.dao.LocalizacaoDAO;
import com.sysagro.modelo.dto.json.LocalizacaoJSON;
import com.sysagro.modelo.entidade.Localizacao;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
public class LocalizacaoFabrica implements Serializable {

    private static final long serialVersionUID = 981281289189128L;

    @Inject
    private LocalizacaoDAO localizacaoDAO;
    
    // Geral
    public Localizacao criar(LocalizacaoJSON localizacaoJSON) {
        try {
            return new Localizacao(localizacaoJSON.getDataHoraCriacao(), localizacaoJSON.getCoordenada());
        } catch (NullPointerException ex) {
            return null;
        }
    }
    
    public Localizacao criarComValidacaoExistencia(LocalizacaoJSON localizacaoJSON) {
        try {
            return localizacaoJSON.getIdLocalizacao() > 0L
                ? localizacaoDAO.buscarPorId(localizacaoJSON.getIdLocalizacao(), Localizacao.class)
                : criar(localizacaoJSON);
        } catch (NullPointerException ex) {
            return null;
        }
    }
}
