/*
 */
package com.sysagro.modelo.dao;

import com.sysagro.enumeracao.PerfilEnum;
import com.sysagro.modelo.entidade.Usuario;
import com.sysagro.modelo.entidade.UsuarioPerfil;
import static com.sysagro.util.LogUtil.exibirErro;
import com.sysagro.util.NumeroUtil;
import java.io.Serializable;
import java.util.List;
import static java.util.Objects.nonNull;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author Pedro
 */
@Named
public class UsuarioDAO extends AbstratoDAO<Usuario> implements Serializable {

    private static final long serialVersionUID = 6886348998234892L;

    @Inject
    private UsuarioPerfilDAO usuarioPerfilDAO;

    // Geral
    public Usuario buscarPorUsuario(Usuario usuario) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT usu \n")
            .append("  FROM Usuario usu \n")
            .append(" WHERE usu = (:usuario) \n");
        try {
            return (Usuario) em.createQuery(sql.toString())
                .setParameter("usuario", usuario)
                .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public Usuario buscarAtivoPorLogin(String login) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT usu \n")
            .append("  FROM Usuario usu \n")
            .append("    LEFT JOIN FETCH usu.foto foto \n")
            .append(" WHERE (usu.login = (:login) OR usu.email = (:login)) \n")
            .append("   AND usu.isAtivo = TRUE \n");
        try {
            return (Usuario) em.createQuery(sql.toString())
                .setParameter("login", login)
                .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Usuario> listarTodos() {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT usu \n")
            .append("  FROM Usuario usu \n")
            .append(" ORDER BY usu.nome \n");
        return em.createQuery(sql.toString()).getResultList();
    }

    public List<Usuario> listarPorPerfil(PerfilEnum perfilEnum) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT usu \n")
            .append("  FROM Usuario usu \n")
            .append("    INNER JOIN usu.perfisUsuario perfisUsuario \n")
            .append("    INNER JOIN perfisUsuario.perfil perfil \n")
            .append(" WHERE perfil.perfilEnum = (:perfilEnum) \n")
            .append(" ORDER BY usu.nome \n");
        return em.createQuery(sql.toString())
            .setParameter("perfilEnum", PerfilEnum.COOP)
            .getResultList();
    }

    // API
    public List<Usuario> listarTodosAPI() {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT usu \n")
            .append("  FROM Usuario usu \n")
            .append("    LEFT JOIN FETCH usu.foto foto \n")
            .append(" ORDER BY usu.nome \n");
        return em.createQuery(sql.toString()).getResultList();
    }
    
    public Usuario buscarComFiltrosAPI(Long idUsuario) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT usu \n")
            .append("  FROM Usuario usu \n")
            .append("    LEFT JOIN FETCH usu.foto foto \n")
            .append(" WHERE 1 = 1 \n");
        if (NumeroUtil.isMaiorQueZero(idUsuario)) {
            sql.append("   AND usu.id = (:idUsuario) \n");
        }
        try {
            Query query = em.createQuery(sql.toString());
            if (NumeroUtil.isMaiorQueZero(idUsuario)) {
                query.setParameter("idUsuario", idUsuario);
            }
            return (Usuario) query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    // Inserção, atualização e exclusão
    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = {Exception.class})
    public boolean salvarComPerfis(Usuario usuario,
            List<UsuarioPerfil> perfisUsuarioSalvar,
            List<UsuarioPerfil> perfisUsuarioExcluir) {
        try {
            salvarSemTransacao(usuario);
            if (nonNull(perfisUsuarioSalvar)) {
                perfisUsuarioSalvar.forEach(up -> usuarioPerfilDAO.salvarSemTransacao(up));
            }
            if (nonNull(perfisUsuarioExcluir)) {
                perfisUsuarioExcluir.forEach(up -> usuarioPerfilDAO.excluirSemTransacao(up));
            }
            return true;
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            return false;
        }
    }
}
