package com.sysagro.modelo.fabrica.json;

import com.sysagro.modelo.dto.json.VisitaJSON;
import com.sysagro.modelo.entidade.Visita;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Pedro
 */
public class VisitaFabricaJSON implements Serializable {

    private static final long serialVersionUID = 912172878127812782L;
    
    @Inject
    private LocalizacaoFabricaJSON localizacaoFabricaJSON;

    // Geral
    public VisitaJSON criar(Visita visita) {
        try {
            return new VisitaJSON(
                visita.getId(),
                visita.getFuncionario().getId(),
                visita.getPessoa().getId(),
                visita.getEndereco().getId(),
                visita.getDataAgendada(),
                visita.getPessoa().getRazaoSocial(),
                visita.getObservacao(),
                localizacaoFabricaJSON.criar(visita.getCheckIn()),
                localizacaoFabricaJSON.criar(visita.getCheckOut()));
        } catch (Exception ex) {
            return null;
        }
    }
}
