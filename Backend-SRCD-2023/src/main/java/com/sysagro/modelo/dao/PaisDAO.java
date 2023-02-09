/*
 */
package com.sysagro.modelo.dao;

import com.sysagro.modelo.entidade.Pais;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
public class PaisDAO extends AbstratoDAO<Pais> implements Serializable {    
        
    private static final long serialVersionUID = 815781278218689126L;
    
    // Geral
    public List<Pais> listarTodos() {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT pais \n")
            .append("  FROM Pais pais \n")
            .append(" ORDER BY pais.nome \n");
        return em.createQuery(sql.toString()).getResultList();
    }
    // Inserção, atualização e exclusão
}
