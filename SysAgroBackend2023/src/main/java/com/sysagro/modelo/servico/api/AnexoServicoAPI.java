/*
 */
package com.sysagro.modelo.servico.api;

import com.sysagro.modelo.dao.AnexoDAO;
import com.sysagro.modelo.dto.json.AnexoJSON;
import com.sysagro.modelo.entidade.Anexo;
import com.sysagro.modelo.fabrica.AnexoFabrica;
import com.sysagro.util.RegistroUtil;
import java.io.Serializable;
import static java.util.Objects.isNull;
import javax.inject.Inject;

/**
 *
 * @author Pedro
 */
public class AnexoServicoAPI implements Serializable {

    private static final long serialVersionUID = 1257217521675287L;

    @Inject
    private AnexoDAO anexoDAO;
    @Inject
    private AnexoFabrica anexoFabrica;

    // Objeto JSON para objeto
    public Anexo buscarValidandoExistencia(AnexoJSON anexoJSON) {
        if (isNull(anexoJSON)) {
            return null;
        } else {
            if (RegistroUtil.isNovo(anexoJSON.getIdAnexo())) {
                return anexoFabrica.criar(anexoJSON);
            } else {
                return anexoDAO.buscarPorId(anexoJSON.getIdAnexo());
            }
        }
    }
}