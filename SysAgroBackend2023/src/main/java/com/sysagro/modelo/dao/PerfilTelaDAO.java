/*
 */
package com.sysagro.modelo.dao;

import com.sysagro.modelo.entidade.Perfil;
import com.sysagro.modelo.entidade.PerfilTela;
import com.sysagro.modelo.entidade.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
public class PerfilTelaDAO extends AbstratoDAO<PerfilTela> implements Serializable {

    private static final long serialVersionUID = 78412874781247821L;

    // Geral
    public List<PerfilTela> listarPorPerfil(Perfil perfil) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT pt \n")
            .append("  FROM PerfilTela pt \n")
            .append("    INNER JOIN FETCH pt.perfil perfil \n")
            .append(" WHERE perfil = (:perfil) \n")
            .append(" ORDER BY pt.telaEnum \n");
        return em.createQuery(sql.toString())
            .setParameter("perfil", perfil.processarParaQuery())
            .getResultList();
    }
    
    public List<PerfilTela> listarPorUsuario(Usuario usuario) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT pt \n")
            .append("  FROM PerfilTela pt \n")
            .append("    INNER JOIN FETCH pt.perfil perfil \n")
            .append(" WHERE perfil IN ( \n")
            .append("    SELECT up.perfil \n")
            .append("      FROM UsuarioPerfil up \n")
            .append("     WHERE up.usuario = (:usuario) \n")
            .append(" ) \n")
            .append(" ORDER BY pt.telaEnum \n");
        return em.createQuery(sql.toString())
            .setParameter("usuario", usuario.processarParaQuery())
            .getResultList();
    }
    
    // Inserção, atualização e exclusão
}
