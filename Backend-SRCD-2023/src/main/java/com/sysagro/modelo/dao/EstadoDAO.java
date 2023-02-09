/*
 */
package com.sysagro.modelo.dao;

import com.sysagro.modelo.entidade.Estado;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
public class EstadoDAO extends AbstratoDAO<Estado> implements Serializable {

    private static final long serialVersionUID = 215125125125121256L;

    // Geral
    public List<Estado> listarTodos() {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT est \n")
            .append("  FROM Estado est \n")
            .append("    INNER JOIN FETCH est.pais pais \n")
            .append(" ORDER BY pais.nome, est.nome \n");
        return em.createQuery(sql.toString()).getResultList();
    }
    
    public List<Estado> listarPorSiglaPais(String siglaPais) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT est \n")
            .append("  FROM Estado est \n")
            .append("    INNER JOIN FETCH est.pais pais \n")
            .append(" WHERE pais.sigla = (:siglaPais) \n")
            .append(" ORDER BY est.nome \n");
        return em.createQuery(sql.toString())
            .setParameter("siglaPais", siglaPais)
            .getResultList();
    }
}
