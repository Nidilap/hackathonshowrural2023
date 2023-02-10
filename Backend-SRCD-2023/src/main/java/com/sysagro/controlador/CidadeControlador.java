package com.sysagro.controlador;

import com.sysagro.modelo.dao.CidadeDAO;
import com.sysagro.modelo.entidade.Cidade;
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
public class CidadeControlador implements Serializable {
    
    private static final long serialVersionUID = 8712812788128712L;

    @Inject
    private CidadeDAO cidadeDAO;

    // Variáveis
    private Cidade cidade;

    // Listas
    private List<Cidade> cidades;

    // Construtor
    @PostConstruct
    public void postConstruct() {
        iniciarVariaveis();
        iniciarListas();
    }
    
    // Início
    private void iniciarVariaveis() {
        setCidade(null);
    }

    private void iniciarListas() {
        atualizarCidades();
    }
    
    // Cadastro
    public void criar() {
    }
    
    public void editar() {
    }

    public void salvar() {
    }

    // Processamentos de listas
    public void atualizarCidades() {
        setCidades(cidadeDAO.listarTodos());
    }

    // Getters && Setters
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
}
