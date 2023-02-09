/*
 */
package com.sysagro.controlador;

import com.sysagro.anotacao.UsuarioValidado;
import com.sysagro.excecao.ValidacaoExcecao;
import com.sysagro.modelo.dao.UsuarioDAO;
import com.sysagro.modelo.entidade.Usuario;
import com.sysagro.modelo.entidade.UsuarioPerfil;
import com.sysagro.modelo.fabrica.UsuarioFabrica;
import com.sysagro.modelo.servico.UsuarioServico;
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
import java.util.List;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Pedro
 */
@Named
@ViewScoped
public class UsuarioControlador implements Serializable {

    private static final long serialVersionUID = 32476372846732L;

    @Inject
    private UsuarioDAO usuarioDAO;
    @Inject
    private UsuarioServico usuarioServico;
    @Inject
    private UsuarioFabrica usuarioFabrica;

    // Variáveis
    @Inject
    @UsuarioValidado
    private Usuario usuario;

    // Listas
    private List<Usuario> usuarios;
    private DualListModel<UsuarioPerfil> modeloDuploPerfisUsuario;

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
        atualizarUsuarios();
        limparModeloDuploPerfisUsuario();
    }

    // Cadastro
    public void criar() {
        setUsuario(usuarioFabrica.criar());
        atualizarModeloDuploPerfis();
    }

    public void editar() {
        setUsuario(usuarioDAO.buscarPorUsuario(usuario));
        atualizarModeloDuploPerfis();
    }

    public void salvar() {
        try {
            usuarioServico.salvar(usuario,
                modeloDuploPerfisUsuario.getTarget(),
                modeloDuploPerfisUsuario.getSource());
            atualizarUsuarios();
            executarJS("handleListagemUsuarios()");
            executarUpdateJSF("listagemUsuarioForm");
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
    public void atualizarUsuarios() {
        setUsuarios(usuarioDAO.listarTodos());
    }

    private void atualizarModeloDuploPerfis() {
        setModeloDuploPerfisUsuario(usuarioServico.listarModeloDuploPerfisUsuarioPorUsuario(usuario));
    }

    private void limparModeloDuploPerfisUsuario() {
        setModeloDuploPerfisUsuario(new DualListModel<>());
    }

    // Getters && Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public DualListModel<UsuarioPerfil> getModeloDuploPerfisUsuario() {
        return modeloDuploPerfisUsuario;
    }

    public void setModeloDuploPerfisUsuario(DualListModel<UsuarioPerfil> modeloDuploPerfisUsuario) {
        this.modeloDuploPerfisUsuario = modeloDuploPerfisUsuario;
    }
}