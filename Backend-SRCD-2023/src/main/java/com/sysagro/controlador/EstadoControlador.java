package com.sysagro.controlador;

import com.sysagro.modelo.dao.EstadoDAO;
import com.sysagro.modelo.entidade.Estado;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Pedro
 */
@Named
@ViewScoped
public class EstadoControlador implements Serializable {
    
    private static final long serialVersionUID = 921487218742178487L;

    @Inject
    private EstadoDAO estadoDAO;

    // Variáveis
    private Estado estado;

    // Listas
    private List<Estado> estados;

    // Construtor
    @PostConstruct
    public void postConstruct() {
        iniciarVariaveis();
        iniciarListas();
    }
    
    // Início
    private void iniciarVariaveis() {
        setEstado(null);
    }

    private void iniciarListas() {
        atualizarEstados();
    }
    
    // Cadastro
    public void criar() {
    }
    
    public void editar() {
    }

    public void salvar() {
    }

    // Processamentos de listas
    public void atualizarEstados() {
        setEstados(estadoDAO.listarTodos());
    }

    // Getters && Setters
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
}
