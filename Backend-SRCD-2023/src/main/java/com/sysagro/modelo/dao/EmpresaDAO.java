/*
 */
package com.sysagro.modelo.dao;

import com.sysagro.modelo.entidade.Empresa;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author Pedro
 */
@Named
public class EmpresaDAO extends AbstratoDAO<Empresa> implements Serializable {

    private static final long serialVersionUID = 34930949029042039L;

    // Geral
    public Empresa buscarPorId(Long idEmpresa) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT emp \n")
            .append("  FROM Empresa emp \n")
            .append(" WHERE emp.id = (:idEmpresa) \n");
        try {
            Query query = em.createQuery(sql.toString());
            query.setParameter("idEmpresa", idEmpresa);
            return (Empresa) query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<Empresa> listarTodos() {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT emp \n")
            .append("  FROM Empresa emp \n")
            .append(" ORDER BY emp.nome \n");
        return em.createQuery(sql.toString()).getResultList();
    }

    public List<Empresa> listarAtivos() {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT emp \n")
            .append("  FROM Empresa emp \n")
            .append(" WHERE emp.isAtivo = TRUE \n")
            .append(" ORDER BY emp.nome \n");
        return em.createQuery(sql.toString()).getResultList();
    }
}
