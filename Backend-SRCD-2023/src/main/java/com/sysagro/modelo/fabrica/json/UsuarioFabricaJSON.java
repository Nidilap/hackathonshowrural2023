package com.sysagro.modelo.fabrica.json;

import com.sysagro.lambda.UsuarioPerfilLambda;
import com.sysagro.modelo.dto.json.UsuarioJSON;
import com.sysagro.modelo.entidade.Perfil;
import com.sysagro.modelo.entidade.Usuario;
import com.sysagro.modelo.entidade.UsuarioPerfil;
import java.io.Serializable;
import java.util.List;
import static java.util.Objects.isNull;
import javax.inject.Inject;

/**
 *
 * @author Pedro
 */
public class UsuarioFabricaJSON implements Serializable {

    private static final long serialVersionUID = 4782174782147812L;

    @Inject
    private PerfilFabricaJSON perfilFabricaJSON;
    @Inject
    private UsuarioPerfilLambda usuarioPerfilLambda;

    // Geral
    public UsuarioJSON criar(Usuario usuario) {
        try {
            UsuarioJSON usuarioJSON = new UsuarioJSON(
                usuario.getId(),
                isNull(usuario.getFoto()) ? 0 : usuario.getFoto().getId(),
                usuario.isIsAtivo(),
                usuario.getLogin(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataHoraCriacao());
            return usuarioJSON;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public UsuarioJSON criar(Usuario usuario, List<Perfil> perfis) {
        try {
            UsuarioJSON usuarioJSON = criar(usuario);
            usuarioJSON.adicionarPerfis(perfilFabricaJSON.criarLista(perfis));
            return usuarioJSON;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public UsuarioJSON criarComPerfis(Usuario usuario, List<UsuarioPerfil> perfisUsuario) {
        try {
            UsuarioJSON usuarioJSON = criar(usuario);
            usuarioJSON.adicionarPerfis(usuarioPerfilLambda.mapearParaPerfisJSON(perfisUsuario));
            return usuarioJSON;
        } catch (NullPointerException ex) {
            return null;
        }
    }
}
