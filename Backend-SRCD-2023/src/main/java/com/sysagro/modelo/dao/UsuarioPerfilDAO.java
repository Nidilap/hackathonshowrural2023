/*
 */
package com.sysagro.modelo.dao;

import com.sysagro.modelo.entidade.Usuario;
import com.sysagro.modelo.entidade.UsuarioPerfil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
public class UsuarioPerfilDAO extends AbstratoDAO<UsuarioPerfil> implements Serializable {

    private static final long serialVersionUID = 21512985981258912L;

    // Geral
    public List<UsuarioPerfil> listarPorUsuario(Usuario usuario) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT usuperf \n")
            .append("  FROM UsuarioPerfil usuperf \n")
            .append("    INNER JOIN FETCH usuperf.usuario usuario \n")
            .append("    INNER JOIN FETCH usuperf.perfil perfil \n")
            .append(" WHERE usuario = (:usuario) \n")
            .append(" ORDER BY perfil.nome \n");
        return em.createQuery(sql.toString())
            .setParameter("usuario", usuario.processarParaQuery())
            .getResultList();
    }
    
    public List<UsuarioPerfil> listarPorUsuarios(List<Usuario> usuarios) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT usuperf \n")
            .append("  FROM UsuarioPerfil usuperf \n")
            .append("    INNER JOIN FETCH usuperf.usuario usuario \n")
            .append("    INNER JOIN FETCH usuperf.perfil perfil \n")
            .append(" WHERE usuario IN (:usuarios) \n")
            .append(" ORDER BY perfil.nome \n");
        return em.createQuery(sql.toString())
            .setParameter("usuarios", usuarios)
            .getResultList();
    }
}
