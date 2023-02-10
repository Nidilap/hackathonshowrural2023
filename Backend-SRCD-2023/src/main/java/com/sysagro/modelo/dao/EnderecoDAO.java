package com.sysagro.modelo.dao;

import com.sysagro.modelo.entidade.Endereco;
import java.io.Serializable;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
public class EnderecoDAO extends AbstratoDAO<Endereco> implements Serializable {

    private static final long serialVersionUID = 218918298912891254L;

    // Geral
    public Endereco buscarPorCAR(String car) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT ender \n")
            .append("  FROM Endereco ender \n")
            .append(" WHERE ender.car = (:car) \n");
        try {
            return (Endereco) em.createQuery(sql.toString()).setParameter("car", car).getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    // Inserção, atualização e exclusão
}
