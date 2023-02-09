package com.sysagro.controlador;

import com.sysagro.anotacao.PerfilValidado;
import com.sysagro.excecao.ValidacaoExcecao;
import com.sysagro.modelo.dao.PerfilDAO;
import com.sysagro.modelo.entidade.Perfil;
import com.sysagro.modelo.entidade.PerfilTela;
import com.sysagro.modelo.fabrica.PerfilFabrica;
import com.sysagro.modelo.servico.PerfilServico;
import static com.sysagro.util.LogUtil.exibirErro;
import static com.sysagro.util.TextoUtil.traduzir;
import static com.sysagro.util.WebUtil.addMensagemErroDetalhada;
import static com.sysagro.util.WebUtil.addMensagemInfo;
import static com.sysagro.util.WebUtil.executarJS;
import static com.sysagro.util.WebUtil.executarUpdateJSF;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Pedro
 */
@Named
@ViewScoped
public class PerfilControlador implements Serializable {

    private static final long serialVersionUID = 1278478124781287L;

    @Inject
    private PerfilDAO perfilDAO;
    @Inject
    private PerfilFabrica perfilFabrica;
    @Inject
    private PerfilServico perfilServico;

    // Variáveis
    @Inject
    @PerfilValidado
    private Perfil perfil;

    // Listas
    private List<Perfil> perfis;
    private DualListModel<PerfilTela> modeloDuploTelasPerfis;

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
        atualizarPerfis();
        limparModeloDuploTelasPerfis();
    }
    
    // Cadastro
    public void criar() {
        setPerfil(perfilFabrica.criar());
        atualizarModeloDuploTelasPerfis();
    }

    public void editar() {
        setPerfil(perfilDAO.buscarPorPerfil(perfil));
        atualizarModeloDuploTelasPerfis();
    }

    public void salvar() {
        try {
            perfilServico.salvar(
                perfil,
                modeloDuploTelasPerfis.getTarget(),
                modeloDuploTelasPerfis.getSource());
            atualizarPerfis();
            executarJS("handleListagemPerfis()");
            executarUpdateJSF("listagemPerfilForm");
            addMensagemInfo(traduzir("geral.mensagem.sucesso.salvar"));
        } catch (ValidacaoExcecao ex) {
            exibirErro(getClass(), ex);
            addMensagemErroDetalhada(ex.getTitulo(), ex.getDetalhe());
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            addMensagemErroDetalhada(traduzir("geral.mensagem.erro.salvar"), traduzir("geral.mensagem.erro.detalhe"));
        }
    }

    // Processamentos de listas
    public void atualizarPerfis() {
        setPerfis(perfilDAO.listarTodos());
    }

    private void limparPerfis() {
        setPerfis(new ArrayList<>());
    }
    
    private void atualizarModeloDuploTelasPerfis() {
        setModeloDuploTelasPerfis(perfilServico.listarModeloDuploTelasPerfisPorPerfil(perfil));
    }
    
    private void limparModeloDuploTelasPerfis() {
        setModeloDuploTelasPerfis(new DualListModel<>());
    }

    // Getters && Setters
    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    public DualListModel<PerfilTela> getModeloDuploTelasPerfis() {
        return modeloDuploTelasPerfis;
    }

    public void setModeloDuploTelasPerfis(DualListModel<PerfilTela> modeloDuploTelasPerfis) {
        this.modeloDuploTelasPerfis = modeloDuploTelasPerfis;
    }
}
