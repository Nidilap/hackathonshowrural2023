/*
 */
package com.sysagro.modelo.dao;

import com.sysagro.modelo.entidade.Visita;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author Pedro
 */
@Named
public class VisitaDAO extends AbstratoDAO<Visita> implements Serializable {

    private static final long serialVersionUID = 8127878217818712L;

    // API
    public List<Visita> listarTodosAPI() {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT vis \n")
            .append("  FROM Visita vis \n")
            .append("    INNER JOIN FETCH vis.funcionario func \n")
            .append("    INNER JOIN FETCH vis.pessoa pess \n")
            .append("    INNER JOIN FETCH vis.endereco ender \n")
            .append("     LEFT JOIN FETCH vis.checkIn checkIn \n")
            .append("     LEFT JOIN FETCH vis.checkOut checkOut \n")
            .append(" ORDER BY vis.dataHoraCriacao \n");
        return em.createQuery(sql.toString()).getResultList();
    }
    
    public List<Visita> listarComFiltrosAPI(Long idFuncionario, Long idPessoa) {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT vis \n")
            .append("  FROM Visita vis \n")
            .append("    INNER JOIN FETCH vis.funcionario func \n")
            .append("    INNER JOIN FETCH vis.pessoa pess \n")
            .append("    INNER JOIN FETCH vis.endereco ender \n")
            .append("     LEFT JOIN FETCH vis.checkIn checkIn \n")
            .append("     LEFT JOIN FETCH vis.checkOut checkOut \n")
            .append(" WHERE 1 = 1 \n");
        if (Objects.nonNull(idFuncionario)) {
            sql.append("   AND func.id = (:idFuncionario) \n");
        }
        if (Objects.nonNull(idFuncionario)) {
            sql.append("   AND pess.id = (:idPessoa) \n");
        }
        sql.append(" ORDER BY pess.razaoSocial, vis.dataHoraCriacao \n");
        // Query
        Query query =  em.createQuery(sql.toString());
        if (Objects.nonNull(idFuncionario)) {
            query.setParameter("idFuncionario", idFuncionario);
        }
        if (Objects.nonNull(idFuncionario)) {
            query.setParameter("idPessoa", idPessoa);
        }
        return query.getResultList();
    }
    
    // Inserção, atualização e exclusão
}
