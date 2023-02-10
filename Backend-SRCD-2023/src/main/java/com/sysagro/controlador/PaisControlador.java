package com.sysagro.controlador;

import com.sysagro.modelo.dao.PaisDAO;
import com.sysagro.modelo.entidade.Pais;
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
public class PaisControlador implements Serializable {
    
    private static final long serialVersionUID = 87421874827148721L;

    @Inject
    private PaisDAO paisDAO;

    // Variáveis
    private Pais pais;

    // Listas
    private List<Pais> paises;

    // Construtor
    @PostConstruct
    public void postConstruct() {
        iniciarVariaveis();
        iniciarListas();
    }
    
    // Início
    private void iniciarVariaveis() {
        setPais(null);
    }

    private void iniciarListas() {
        atualizarPaises();
    }
    
    // Cadastro
    public void criar() {
    }
    
    public void editar() {
    }

    public void salvar() {
    }

    // Processamentos de listas
    public void atualizarPaises() {
        setPaises(paisDAO.listarTodos());
    }

    // Getters && Setters
    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }
}
