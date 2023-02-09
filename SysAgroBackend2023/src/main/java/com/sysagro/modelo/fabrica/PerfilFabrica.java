package com.sysagro.modelo.fabrica;

import com.sysagro.anotacao.PerfilValidado;
import com.sysagro.modelo.entidade.Perfil;
import java.io.Serializable;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
public class PerfilFabrica implements Serializable {

    private static final long serialVersionUID = 98124982189412894L;

    // Geral
    @Produces
    @PerfilValidado
    public Perfil criar() {
        try {
            Perfil perfil = new Perfil();
            return perfil;
        } catch (NullPointerException ex) {
            return null;
        }
    }
}
