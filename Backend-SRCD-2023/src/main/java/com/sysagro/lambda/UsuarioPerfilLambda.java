/*
 */
package com.sysagro.lambda;

import com.sysagro.modelo.dto.json.PerfilJSON;
import com.sysagro.modelo.entidade.Perfil;
import com.sysagro.modelo.entidade.Usuario;
import com.sysagro.modelo.entidade.UsuarioPerfil;
import com.sysagro.modelo.fabrica.json.PerfilFabricaJSON;
import com.sysagro.util.LambdaUtil;
import com.sysagro.util.RegistroUtil;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author Pedro
 */
public class UsuarioPerfilLambda implements Serializable {

    private static final long serialVersionUID = 490214821784871247L;
    
    @Inject
    private PerfilFabricaJSON perfilFabricaJSON;
    
    // Lambdas
    private final Predicate<UsuarioPerfil> filtrarComIdMaiorQueZero = (up) -> !RegistroUtil.isNovo(up.getId());
    private final Function<UsuarioPerfil, Perfil> mapearParaPerfil = UsuarioPerfil::getPerfil;
    private final Function<UsuarioPerfil, PerfilJSON> mapearParaPerfilJSON = (up) -> perfilFabricaJSON.criar(up.getPerfil());

    // Processamentos de listas
    public List<UsuarioPerfil> filtrarComIdMaiorQueZero(List<UsuarioPerfil> perfisUsuario) {
        return new LambdaUtil<UsuarioPerfil, UsuarioPerfil>().processarLP(perfisUsuario, filtrarComIdMaiorQueZero);
    }
    
    public List<UsuarioPerfil> filtrarPorUsuario(Usuario usuario, List<UsuarioPerfil> perfisUsuario) {
        Predicate<UsuarioPerfil> filtrarComUsuario = (up) -> up.getUsuario().equals(usuario);
        return new LambdaUtil<UsuarioPerfil, UsuarioPerfil>().processarLP(perfisUsuario, filtrarComUsuario);
    }

    public List<Perfil> mapearParaPerfis(List<UsuarioPerfil> perfisUsuario) {
        return new LambdaUtil<UsuarioPerfil, Perfil>().processarLF(perfisUsuario, mapearParaPerfil);
    }

    public List<PerfilJSON> mapearParaPerfisJSON(List<UsuarioPerfil> perfisUsuario) {
        return new LambdaUtil<UsuarioPerfil, PerfilJSON>().processarLF(perfisUsuario, mapearParaPerfilJSON);
    }
}
