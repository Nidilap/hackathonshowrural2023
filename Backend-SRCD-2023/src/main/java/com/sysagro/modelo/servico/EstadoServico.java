/*
 */
package com.sysagro.modelo.servico;

import com.sysagro.modelo.dao.EstadoDAO;
import com.sysagro.modelo.entidade.Estado;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
public class EstadoServico implements Serializable {
    
    private static final long serialVersionUID = 9021895128951285L;

    @Inject
    private EstadoDAO estadoDAO;
    
    // Geral
    public List<Estado> listarTodosSessao() {
        return estadoDAO.listarPorSiglaPais("BR");
    }
}
