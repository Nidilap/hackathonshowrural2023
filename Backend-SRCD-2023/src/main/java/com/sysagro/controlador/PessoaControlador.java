package com.sysagro.controlador;

import com.sysagro.modelo.dao.PessoaDAO;
import com.sysagro.modelo.entidade.Pessoa;
import com.sysagro.modelo.servico.PessoaServico;
import static com.sysagro.util.LogUtil.exibirErro;
import static com.sysagro.util.WebUtil.addMensagemErro;
import static com.sysagro.util.WebUtil.addMensagemInfo;
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
public class PessoaControlador implements Serializable {
    
    private static final long serialVersionUID = 982189128921891898L;

    @Inject
    private PessoaDAO pessoaDAO;
    @Inject
    private PessoaServico pessoaServico;

    // Variáveis
    private Pessoa pessoa;

    // Listas
    private List<Pessoa> pessoas;

    // Construtor
    @PostConstruct
    public void postConstruct() {
        iniciarVariaveis();
        iniciarListas();
    }
    
    // Início
    private void iniciarVariaveis() {
    }

    private void iniciarListas() {
        atualizarPessoas();
    }

    // Geral
    public void atualizarCadastroComCAR() {
        try {
            pessoaServico.atualizarCadastroComCAR();
            atualizarPessoas();
            addMensagemInfo("Cadastro atualizado com sucesso");
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            addMensagemErro("Não foi possível atualizar o cadastro pelo CAR, recarregue a página e tente novamente");
        }
    }
    
    // Cadastro
    public void criar() {
    }
    
    public void editar() {
    }

    public void salvar() {
    }

    // Processamentos de listas
    public void atualizarPessoas() {
        setPessoas(pessoaDAO.listarTodos());
    }

    // Getters && Setters
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
}
