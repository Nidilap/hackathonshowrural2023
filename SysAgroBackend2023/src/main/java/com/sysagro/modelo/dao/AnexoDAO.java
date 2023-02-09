/*
 */
package com.sysagro.modelo.dao;

import com.sysagro.modelo.entidade.Anexo;
import java.io.Serializable;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
public class AnexoDAO extends AbstratoDAO<Anexo> implements Serializable {

    private static final long serialVersionUID = 9281481246517242L;

    // Geral
    public Anexo buscarPorId(Long idAnexo) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT ane \n")
            .append("  FROM Anexo ane \n")
            .append(" WHERE ane.id = (:idAnexo) \n");
        try {
            return (Anexo) em.createQuery(sql.toString())
                .setParameter("idAnexo", idAnexo)
                .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
}
