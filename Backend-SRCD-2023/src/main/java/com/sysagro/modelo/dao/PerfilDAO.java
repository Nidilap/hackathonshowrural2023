/*
 */
package com.sysagro.modelo.dao;

import com.sysagro.modelo.entidade.Perfil;
import com.sysagro.modelo.entidade.PerfilTela;
import com.sysagro.modelo.entidade.Usuario;
import static com.sysagro.util.LogUtil.exibirErro;
import java.io.Serializable;
import java.util.List;
import static java.util.Objects.nonNull;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 *
 * @author Pedro
 */
@Named
public class PerfilDAO extends AbstratoDAO<Perfil> implements Serializable {

    private static final long serialVersionUID = 92148859327528921L;
    
    @Inject
    private PerfilTelaDAO perfilTelaDAO;

    // Geral
    public Perfil buscarPorPerfil(Perfil perfil) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT perf \n")
            .append("  FROM Perfil perf \n")
            .append(" WHERE perf = (:perfil) \n");
        try {
            return (Perfil) em.createQuery(sql.toString())
                .setParameter("perfil", perfil)
                .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<Perfil> listarTodos() {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT perf \n")
            .append("  FROM Perfil perf \n")
            .append(" ORDER BY perf.nome \n");
        return em.createQuery(sql.toString())
            .getResultList();
    }
    
    public List<Perfil> listarPorUsuario(Usuario usuario) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT perf \n")
            .append("  FROM Perfil perf \n")
            .append(" WHERE 1 = 1 \n")
            // Subselect para considerar os perfis do "usuário"
            .append("   AND perf IN ( \n")
            .append("        SELECT DISTINCT perfil \n")
            .append("          FROM UsuarioPerfil usuperf \n")
            .append("            INNER JOIN usuperf.perfil perfil \n")
            .append("            INNER JOIN usuperf.usuario usuario \n")
            .append("         WHERE usuario = (:usuario) \n")
            .append("   ) \n")
            .append(" ORDER BY perf.nome \n");
        return em.createQuery(sql.toString())
            .setParameter("usuario", usuario.processarParaQuery()) // Evita erro de flushing
            .getResultList();
    }
    
    public List<Perfil> listarSemVinculoComUsuario(Usuario usuario) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT perf \n")
            .append("  FROM Perfil perf \n")
            .append(" WHERE 1 = 1 \n")
            // Subselect para não considerar os perfis que o "usuário" já tem
            .append("   AND perf NOT IN ( \n")
            .append("        SELECT DISTINCT perfil \n")
            .append("          FROM UsuarioPerfil usuperf \n")
            .append("            INNER JOIN usuperf.perfil perfil \n")
            .append("            INNER JOIN usuperf.usuario usuario \n")
            .append("         WHERE usuario = (:usuario) \n")
            .append("   ) \n")
            .append(" ORDER BY perf.nome \n");
        return em.createQuery(sql.toString())
            .setParameter("usuario", usuario.processarParaQuery()) // Evita erro de flushing
            .getResultList();
    }
    
    // Inserção, atualização e exclusão
    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = {Exception.class})
    public boolean salvarComTelas(Perfil perfil,
            List<PerfilTela> telasPerfisSalvar,
            List<PerfilTela> telasPerfisExcluir) {
        try {
            salvarSemTransacao(perfil);
            if (nonNull(telasPerfisSalvar)) {
                telasPerfisSalvar.forEach(pt -> perfilTelaDAO.salvarSemTransacao(pt));
            }
            if (nonNull(telasPerfisExcluir)) {
                telasPerfisExcluir.forEach(pt -> perfilTelaDAO.excluirSemTransacao(pt));
            }
            return true;
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            return false;
        }
    }
}
