/*
 */
package com.sysagro.modelo.dao;

import com.sysagro.enumeracao.ParametroEnum;
import com.sysagro.modelo.entidade.Empresa;
import com.sysagro.modelo.entidade.Parametro;
import static com.sysagro.util.LogUtil.exibirErro;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.transaction.Transactional;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author Pedro
 */
@Named
public class ParametroDAO extends AbstratoDAO<Parametro> implements Serializable {

    private static final long serialVersionUID = 892589192858912895L;

    // Geral
    public Parametro buscarPorEnum(ParametroEnum parametroEnum) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT param \n")
            .append("  FROM Parametro param \n")
            .append("    INNER JOIN param.empresa empresa \n")
            .append(" WHERE param.parametroEnum = (:parametroEnum) \n");
        try {
            return (Parametro) em.createQuery(sql.toString())
                .setParameter("parametroEnum", parametroEnum)
                .getSingleResult();
        } catch (Exception excecao) {
            return null;
        }
    }
    
    public boolean buscarBooleanPorEnum(ParametroEnum parametroEnum) {
        try {
            Parametro parametro = buscarPorEnum(parametroEnum);
            return Boolean.valueOf(parametro.getValor());
        } catch (Exception excecao) {
            return false;
        }
    }
    
    public Integer buscarIntegerPorEnum(ParametroEnum parametroEnum) {
        try {
            Parametro parametro = buscarPorEnum(parametroEnum);
            return Integer.valueOf(parametro.getValor());
        } catch (Exception excecao) {
            return null;
        }
    }
    
    public List<Parametro> listarRegistrosPorParametro(Parametro parametro, Empresa empresa) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT regis \n")
            .append("  FROM Parametro regis \n")
            .append("    INNER JOIN regis.empresa empresa \n")
            .append("    INNER JOIN regis.parametro parametro \n")
            .append(" WHERE empresa = (:empresa) \n")
            .append("   AND parametro = (:parametro) \n")
            .append("   AND regis.isRegistroLista = TRUE \n")
            .append(" ORDER BY regis.id \n");
        return em.createQuery(sql.toString())
            .setParameter("empresa", empresa)
            .setParameter("parametro", parametro)
            .getResultList();
    }
    
    public List<Parametro> listarRegistrosPorParametro(ParametroEnum parametroEnum, Empresa empresa) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT regis \n")
            .append("  FROM Parametro regis \n")
            .append("    INNER JOIN regis.empresa empresa \n")
            .append("    INNER JOIN regis.parametro parametro \n")
            .append(" WHERE empresa = (:empresa) \n")
            .append("   AND regis.parametroEnum = (:parametroEnum) \n")
            .append("   AND regis.isRegistroLista = TRUE \n")
            .append(" ORDER BY regis.id \n");
        return em.createQuery(sql.toString())
            .setParameter("empresa", empresa)
            .setParameter("parametroEnum", parametroEnum)
            .getResultList();
    }
    
    public List<Parametro> listarPorEmpresaSemRegistros(Empresa empresa) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT param \n")
            .append("  FROM Parametro param \n")
            .append("    INNER JOIN param.empresa empresa \n")
            .append(" WHERE empresa = (:empresa) \n")
            .append("   AND param.isRegistroLista = FALSE \n")
            .append(" ORDER BY param.parametroEnum \n");
        return em.createQuery(sql.toString())
            .setParameter("empresa", empresa)
            .getResultList();
    }
    
    // Inserção, atualização e exclusão
    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = {Exception.class})
    public boolean salvarComRegistros(Parametro parametro) {
        try {
            salvarSemTransacao(parametro);
            if (CollectionUtils.isNotEmpty(parametro.getRegistrosTR())) {
                parametro.getRegistrosTR().forEach(registro -> salvarSemTransacao(registro));
            }
            if (CollectionUtils.isNotEmpty(parametro.getRegistrosParaExcluirTR())) {
                parametro.getRegistrosParaExcluirTR().forEach(registroParaExcluir -> excluirSemTransacao(registroParaExcluir));
            }
            return true;
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            return false;
        }
    }
}
