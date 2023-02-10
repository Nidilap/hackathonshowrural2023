/*
 */
package com.sysagro.modelo.dao;

import com.sysagro.modelo.entidade.Cidade;
import com.sysagro.modelo.entidade.Estado;
import com.sysagro.util.ConfiguracaoUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
public class CidadeDAO extends AbstratoDAO<Cidade> implements Serializable {

    private static final long serialVersionUID = 938257819857128512L;

    // Geral
    public Cidade buscarPorFiltros(String nomeCidade, String uf) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT cid \n")
            .append("  FROM Cidade cid \n")
            .append("    INNER JOIN FETCH cid.estado estado \n")
            .append("    INNER JOIN FETCH estado.pais pais \n")
            .append(" WHERE TRIM(cid.nome) ILIKE (:nomeCidade) \n")
            .append("   AND estado.uf = (:uf) \n");
        try {
            return (Cidade) em.createQuery(sql.toString())
                .setParameter("nomeCidade", "'".concat(nomeCidade.trim()).concat("'"))
                .setParameter("uf", uf)
                .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<Cidade> listarTodos() {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT cid \n")
            .append("  FROM Cidade cid \n")
            .append("    INNER JOIN FETCH cid.estado estado \n")
            .append("    INNER JOIN FETCH estado.pais pais \n")
            .append(" ORDER BY pais.nome, estado.nome, cid.nome \n");
        return em.createQuery(sql.toString()).getResultList();
    }
    
    public List<Cidade> listarPorEstado(Estado estado) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT cid \n")
            .append("  FROM Cidade cid \n")
            .append("    INNER JOIN FETCH cid.estado estado \n")
            .append(" WHERE estado = (:estado) \n")
            .append(" ORDER BY cid.nome \n");
        return em.createQuery(sql.toString())
            .setParameter("estado", estado)
            .getResultList();
    }
    
    public List<Cidade> listarAutoComplete(String valor) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT cid \n")
            .append("  FROM Cidade cid \n")
            .append("    INNER JOIN FETCH cid.estado estado \n")
            .append(" WHERE TRIM(UPPER(cid.nome)) LIKE TRIM(UPPER(CONCAT('%', :valor, '%'))) \n")
            .append(" ORDER BY cid.nome \n");
        return em.createQuery(sql.toString())
            .setParameter("valor", valor)
            .setMaxResults(ConfiguracaoUtil.AUTOCOMPLETE_LIMITE_RESULTADOS)
            .getResultList();
    }
}
