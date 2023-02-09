package com.sysagro.modelo.fabrica.json;

import com.sysagro.modelo.dto.json.PerfilJSON;
import com.sysagro.modelo.entidade.Perfil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.nonNull;

/**
 *
 * @author Pedro
 */
public class PerfilFabricaJSON implements Serializable {

    private static final long serialVersionUID = 5821412894982194L;

    // Geral
    public PerfilJSON criar(Perfil perfil) {
        try {
            PerfilJSON perfilJSON = new PerfilJSON(
                perfil.getId(),
                perfil.getNome(),
                perfil.getSigla(),
                perfil.getObservacao(),
                perfil.getPerfilEnum());
            return perfilJSON;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public List<PerfilJSON> criarLista(List<Perfil> perfis) {
        try {
            List<PerfilJSON> perfisJSON = new ArrayList<>();
            if (nonNull(perfis)) {
                perfisJSON.forEach(perfilJSON -> perfisJSON.add(perfilJSON));
            }
            return perfisJSON;
        } catch (NullPointerException ex) {
            return null;
        }
    }
}
