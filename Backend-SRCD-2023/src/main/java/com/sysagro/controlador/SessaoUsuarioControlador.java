package com.sysagro.controlador;

import com.github.adminfaces.template.session.AdminSession;
import com.sysagro.enumeracao.LocalidadeEnum;
import com.sysagro.enumeracao.ModuloTelaEnum;
import com.sysagro.enumeracao.PerfilEnum;
import com.sysagro.enumeracao.TelaEnum;
import com.sysagro.excecao.ValidacaoExcecao;
import com.sysagro.modelo.dao.EmpresaDAO;
import com.sysagro.modelo.entidade.Empresa;
import com.sysagro.modelo.entidade.Usuario;
import com.sysagro.modelo.servico.PerfilServico;
import com.sysagro.modelo.servico.TelaServico;
import com.sysagro.modelo.servico.UsuarioServico;
import static com.sysagro.util.LogUtil.exibirErro;
import com.sysagro.util.SessaoUtil;
import static com.sysagro.util.TextoUtil.traduzir;
import com.sysagro.util.WebUtil;
import static com.sysagro.util.WebUtil.addMensagemErroDetalhada;
import static com.sysagro.util.WebUtil.recarregarTelaJS;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Specializes;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Pedro
 */
@Named
@Specializes
@SessionScoped
public class SessaoUsuarioControlador extends AdminSession implements Serializable {

    private static final long serialVersionUID = 89138987387273873L;

    @Inject
    private TelaServico telaServico;
    @Inject
    private EmpresaDAO empresaDAO;
    @Inject
    private PerfilServico perfilServico;
    @Inject
    private UsuarioServico usuarioServico;

    // Variáveis
    private String login;
    private String senha;
    private Usuario usuario;
    private Empresa empresa;
    private Locale localidade;
    private LocalidadeEnum localidadeEnum;

    // Listas
    private List<String> telas;
    private List<String> modulosTelas;
    private List<String> perfis;

    // Construtor
    @PostConstruct
    public void postConstruct() {
        iniciarListas();
        iniciarLocalidadePadrao();
    }
    
    // Início
    private void iniciarListas() {
        limparTelas();
        limparModulosTelas();
        limparPerfis();
    }
    
    private void iniciarLocalidadePadrao() {
        setLocalidade(Locale.getDefault());
        definirLocalidadeSistema(LocalidadeEnum.retornarPorLocale(localidade));
    }
    
    // Geral
    @Override
    public boolean isLoggedIn() {
        return nonNull(usuario);
    }
    
    private void definirEmpresa() {
        setEmpresa(empresaDAO.buscarPorId(1L));
    }

    // Login
    public void logar() {
        try {
            setUsuario(usuarioServico.retornarUsuarioAutenticado(login, senha));
            WebUtil.adicionarAtributoSessao(SessaoUtil.USUARIO, usuario);
            atualizarTelas();
            atualizarModulosTelas();
            atualizarPerfis();
            definirEmpresa();
            recarregarTelaJS();
        } catch (ValidacaoExcecao ex) {
            setUsuario(null);
            exibirErro(getClass(), ex);
            addMensagemErroDetalhada(ex.getTitulo(), ex.getDetalhe());
        } catch (Exception ex) {
            setUsuario(null);
            exibirErro(getClass(), ex);
            addMensagemErroDetalhada(traduzir("usuario.mensagem.erro.login"), traduzir("geral.mensagem.erro.detalhe"));
        }
    }
    
    // Logoff
    public void deslogar() {
        setUsuario(null);
        recarregarTelaJS();
    }
    
    // Perfis
    public boolean isAdministrador() {
        try {
            return perfis.contains(PerfilEnum.ADMIN.name());
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public boolean isPresidente() {
        try {
            return perfis.contains(PerfilEnum.PRESI.name());
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public boolean isExtensionista() {
        try {
            return perfis.contains(PerfilEnum.EXTEN.name());
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public boolean isCooperado() {
        try {
            return perfis.contains(PerfilEnum.COOP.name());
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public boolean isMotorista() {
        try {
            return perfis.contains(PerfilEnum.MOTOR.name());
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public boolean isFuncionarioCooperado() {
        try {
            return perfis.contains(PerfilEnum.COOP_F.name());
        } catch (NullPointerException ex) {
            return false;
        }
    }
    
    // Telas
    public String buscarTelaCSS(TelaEnum telaEnum) {
        try {
            return (isAdministrador() || telas.contains(telaEnum.name())) ? "" : "ocultar";
        } catch (NullPointerException ex) {
            return "";
        }
    }
    
    public String buscarModuloTelaCSS(ModuloTelaEnum moduloTelaEnum) {
        try {
            return (isAdministrador() || modulosTelas.contains(moduloTelaEnum.name())) ? "" : "ocultar";
        } catch (NullPointerException ex) {
            return "";
        }
    }
    
    // Localidade
    public void alterarLocalidade(LocalidadeEnum localidadeEnum) {
        try {
            FacesContext.getCurrentInstance().getViewRoot().getLocale();
            setLocalidade(localidadeEnum.getLocalidade());
            definirLocalidadeSistema(localidadeEnum);
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
        } finally {
            recarregarTelaJS();
        }
    }
    
    private void definirLocalidadeSistema(LocalidadeEnum localidadeEnum) {
        setLocalidadeEnum(isNull(localidadeEnum) ? LocalidadeEnum.EN_US : localidadeEnum);
    }
    
    // Processamentos de listas
    private void atualizarTelas() {
        setTelas(telaServico.listarNomesPorUsuario(usuario));
    }
    
    private void atualizarModulosTelas() {
        setModulosTelas(telaServico.listarNomesModulosTelasPorUsuario(usuario));
    }

    private void atualizarPerfis() {
        setPerfis(perfilServico.listarPerfisStringPorUsuario(usuario));
    }
    
    private void limparTelas() {
        setTelas(new ArrayList<>());
    }
    
    private void limparModulosTelas() {
        setModulosTelas(new ArrayList<>());
    }
    
    private void limparPerfis() {
        setPerfis(new ArrayList<>());
    }
    
    // Getters && Setters (imutáveis em outras classes)
    public List<String> getTelas() {
        return isNull(telas) ? null : Collections.unmodifiableList(telas);
    }

    private void setTelas(List<String> telas) {
        this.telas = telas;
    }
    
    public List<String> getModulosTelas() {
        return isNull(modulosTelas) ? null : Collections.unmodifiableList(modulosTelas);
    }

    private void setModulosTelas(List<String> modulosTelas) {
        this.modulosTelas = modulosTelas;
    }

    public List<String> getPerfis() {
        return isNull(perfis) ? null : Collections.unmodifiableList(perfis);
    }

    private void setPerfis(List<String> perfis) {
        this.perfis = perfis;
    }

    public LocalidadeEnum getLocalidadeEnum() {
        return localidadeEnum;
    }

    public void setLocalidadeEnum(LocalidadeEnum localidadeEnum) {
        this.localidadeEnum = localidadeEnum;
    }
    
    // Getters && Setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Locale getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Locale localidade) {
        this.localidade = localidade;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
