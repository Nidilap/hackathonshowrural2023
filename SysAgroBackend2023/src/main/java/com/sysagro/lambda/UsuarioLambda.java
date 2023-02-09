/*
 */
package com.sysagro.lambda;

import com.sysagro.modelo.dto.json.UsuarioJSON;
import com.sysagro.modelo.entidade.Usuario;
import com.sysagro.modelo.fabrica.json.UsuarioFabricaJSON;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author Pedro
 */
public class UsuarioLambda implements Serializable {

    private static final long serialVersionUID = 721841284912842124L;

    @Inject
    private UsuarioFabricaJSON usuarioFabricaJSON;
    
    // Processamentos de listas
    public List<UsuarioJSON> mapearParaUsuariosJSON(List<Usuario> usuarios) {
        List<UsuarioJSON> lista = new ArrayList<>();
        for (Integer i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            lista.add(usuarioFabricaJSON.criarComPerfis(usuario, usuario.getPerfisUsuario()));
        }
        return lista;
    }
    
    // Lambdas com parâmetros
    public Predicate<Usuario> filtrarSemExistenciaBD(List<Usuario> usuariosBD) {
        return (Usuario) -> !usuariosBD.contains(Usuario);
    }
}
