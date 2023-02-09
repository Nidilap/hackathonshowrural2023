package com.sysagro.modelo.fabrica;

import com.sysagro.anotacao.UsuarioValidado;
import com.sysagro.modelo.entidade.Usuario;
import java.io.Serializable;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
public class UsuarioFabrica implements Serializable {

    private static final long serialVersionUID = 32758723587238752L;

    // Geral
    @Produces
    @UsuarioValidado
    public Usuario criar() {
        try {
            Usuario usuario = new Usuario();
            return usuario;
        } catch (NullPointerException ex) {
            return null;
        }
    }
}
