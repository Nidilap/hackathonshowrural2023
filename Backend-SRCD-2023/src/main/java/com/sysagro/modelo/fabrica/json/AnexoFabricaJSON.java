package com.sysagro.modelo.fabrica.json;

import com.sysagro.modelo.dto.json.AnexoJSON;
import com.sysagro.modelo.entidade.Anexo;
import java.io.Serializable;

/**
 *
 * @author Pedro
 */
public class AnexoFabricaJSON implements Serializable {
    
    private static final long serialVersionUID = 54793683476874378L;

    // Geral
    public AnexoJSON criar(Anexo anexo) {
        try {
            AnexoJSON anexoJSON = new AnexoJSON(
                anexo.getId(),
                anexo.getTamanho(),
                anexo.getNome(),
                anexo.getTipo(),
                anexo.retornarBase64(),
                anexo.getDataHoraCriacao());
            return anexoJSON;
        } catch (Exception ex) {
            return null;
        }
    }
}