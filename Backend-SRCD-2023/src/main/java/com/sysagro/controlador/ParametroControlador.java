/*
 */
package com.sysagro.controlador;

import com.sysagro.enumeracao.TipoParametroEnum;
import com.sysagro.excecao.ValidacaoExcecao;
import com.sysagro.lambda.ParametroLambda;
import com.sysagro.modelo.dao.ParametroDAO;
import com.sysagro.modelo.entidade.Parametro;
import com.sysagro.modelo.servico.ParametroServico;
import static com.sysagro.util.LogUtil.exibirErro;
import static com.sysagro.util.TextoUtil.traduzir;
import com.sysagro.util.WebUtil;
import static com.sysagro.util.WebUtil.addMensagemErroDetalhada;
import static com.sysagro.util.WebUtil.addMensagemInfo;
import static com.sysagro.util.WebUtil.executarUpdateJSF;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Pedro
 */
@Named
@ViewScoped
public class ParametroControlador implements Serializable {

    private static final long serialVersionUID = 781247827814871784L;
    
    @Inject
    private ParametroDAO parametroDAO;
    @Inject
    private ParametroLambda parametroLambda;
    @Inject
    private ParametroServico parametroServico;
    @Inject
    private SessaoUsuarioControlador sessaoUsuarioControlador;

    // Variáveis
    private Parametro parametro;

    // Listas
    private List<Parametro> parametros;
    private List<TipoParametroEnum> tiposParametrosEnum;

    // Construtor
    @PostConstruct
    public void postConstruct() {
        iniciarVariaveis();
        iniciarListas();
        salvarAposInicializacaoTela();
    }

    // Início
    private void iniciarVariaveis() {
    }

    private void iniciarListas() {
        atualizarParametros();
        atualizarTiposParametrosEnum();
    }
    
    private void salvarAposInicializacaoTela() {
        try {
            parametroServico.salvarListaNovos(parametros);
        } catch (ValidacaoExcecao ex) {
            exibirErro(getClass(), ex);
            addMensagemErroDetalhada(ex.getTitulo(), ex.getDetalhe());
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            addMensagemErroDetalhada(traduzir("geral.mensagem.erro.salvar"), traduzir("geral.mensagem.erro.detalhe"));
        }
    }

    // Cadastro
    public void salvar() {
        try {
            parametroServico.salvarLista(parametros);
            atualizarParametros();
            executarUpdateJSF("listagemParametrosForm");
            addMensagemInfo(traduzir("geral.mensagem.sucesso.salvar"));
        } catch (ValidacaoExcecao ex) {
            exibirErro(getClass(), ex);
            addMensagemErroDetalhada(ex.getTitulo(), ex.getDetalhe());
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            addMensagemErroDetalhada(traduzir("geral.mensagem.erro.salvar"), traduzir("geral.mensagem.erro.detalhe"));
        }
    }
    
    // Edição de registros do parâmetro
    public void editarRegistros(Parametro parametro) {
        try {
            setParametro(parametro);
            parametro.setRegistrosTR(parametroDAO.listarRegistrosPorParametro(parametro, sessaoUsuarioControlador.getEmpresa()));
            WebUtil.abrirDialogJS("editarRegistrosParametroDialogWV");
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            addMensagemErroDetalhada(traduzir("geral.mensagem.erro.editar"), traduzir("geral.mensagem.erro.detalhe"));
        }
    }
    
    public void adicionarRegistroVazio() {
        try {
            parametroServico.adicionarRegistroVazio(parametro);
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            addMensagemErroDetalhada(traduzir("geral.mensagem.erro.editar"), traduzir("geral.mensagem.erro.detalhe"));
        }
    }
    
    public void excluirRegistro(int index) {
        try {
            parametroServico.adicionarRegistroParaExcluir(index, parametro);
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            addMensagemErroDetalhada(traduzir("geral.mensagem.erro.editar"), traduzir("geral.mensagem.erro.detalhe"));
        }
    }
    
    public void salvarRegistros() {
        try {
            parametroServico.salvarRegistros(parametro);
            atualizarParametros();
            addMensagemInfo(traduzir("geral.mensagem.sucesso.salvar"));
            WebUtil.fecharDialogJS("editarRegistrosParametroDialogWV");
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            addMensagemErroDetalhada(traduzir("geral.mensagem.erro.editar"), traduzir("geral.mensagem.erro.detalhe"));
        }
    }

    // Processamentos de listas
    public List<Parametro> filtrarPorTipoParametroEnum(TipoParametroEnum tipoParametroEnum) {
        return parametroLambda.filtrarPorTipoParametroEnum(tipoParametroEnum, parametros);
    }

    public void atualizarParametros() {
        setParametros(parametroServico.listarParametrosPorEmpresaSemRegistros(sessaoUsuarioControlador.getEmpresa()));
    }

    public void atualizarTiposParametrosEnum() {
        setTiposParametrosEnum(TipoParametroEnum.listar());
    }

    // Getters && Setters
    public Parametro getParametro() {
        return parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }

    public List<Parametro> getParametros() {
        return parametros;
    }

    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }

    public List<TipoParametroEnum> getTiposParametrosEnum() {
        return tiposParametrosEnum;
    }

    public void setTiposParametrosEnum(List<TipoParametroEnum> tiposParametrosEnum) {
        this.tiposParametrosEnum = tiposParametrosEnum;
    }
}