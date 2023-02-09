/*
 */
package com.sysagro.lambda;

import com.sysagro.modelo.entidade.Perfil;
import com.sysagro.modelo.entidade.Usuario;
import com.sysagro.modelo.entidade.UsuarioPerfil;
import com.sysagro.util.LambdaUtil;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author Pedro
 */
public class PerfilLambda implements Serializable {

    private static final long serialVersionUID = 27481724812874212L;
    
    // Lambdas
    private final Function<Perfil, String> mapearParaNomePerfilEnum = (perfil) -> perfil.getPerfilEnum().name();

    // Processamentos de listas
    public List<String> mapearParaNomesPerfisEnum(List<Perfil> perfis) {
        return new LambdaUtil<Perfil, String>().processarLF(perfis, mapearParaNomePerfilEnum);
    }
    
    public List<UsuarioPerfil> mapearParaUsuariosPerfis(Usuario usuario, List<Perfil> perfis) {
        return new LambdaUtil<Perfil, UsuarioPerfil>().processarLF(perfis, mapearParaUsuarioPerfil(usuario));
    }
    
    // Lambdas com parâmetros
    public Predicate<Perfil> filtrarSemExistenciaBD(List<Perfil> perfisBD) {
        return (perfil) -> !perfisBD.contains(perfil);
    }
    
    public Function<Perfil, UsuarioPerfil> mapearParaUsuarioPerfil(Usuario usuario) {
        return (perfil) -> new UsuarioPerfil(usuario, perfil);
    }
}
