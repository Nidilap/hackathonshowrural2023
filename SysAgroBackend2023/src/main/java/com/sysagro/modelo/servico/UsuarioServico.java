/*
 */
package com.sysagro.modelo.servico;

import com.sysagro.excecao.ValidacaoExcecao;
import com.sysagro.lambda.PerfilLambda;
import com.sysagro.lambda.UsuarioLambda;
import com.sysagro.lambda.UsuarioPerfilLambda;
import com.sysagro.modelo.dao.PerfilDAO;
import com.sysagro.modelo.dao.UsuarioDAO;
import com.sysagro.modelo.dao.UsuarioPerfilDAO;
import com.sysagro.modelo.dto.json.UsuarioJSON;
import com.sysagro.modelo.entidade.Usuario;
import com.sysagro.modelo.entidade.UsuarioPerfil;
import static com.sysagro.util.TextoUtil.traduzir;
import static com.sysagro.util.TextoUtil.traduzirIngles;
import java.io.Serializable;
import java.util.List;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Pedro
 */
@Named
public class UsuarioServico implements Serializable {
    
    private static final long serialVersionUID = 46721521858127512L;

    @Inject
    private PerfilDAO perfilDAO;
    @Inject
    private UsuarioDAO usuarioDAO;
    @Inject
    private UsuarioPerfilDAO usuarioPerfilDAO;
    @Inject
    private PerfilLambda perfilLambda;
    @Inject
    private UsuarioPerfilLambda usuarioPerfilLambda;
    
    // Geral
    public void atualizarPerfis(Usuario usuario) {
        if (nonNull(usuario)) {
            List<UsuarioPerfil> perfisUsuario = usuarioPerfilDAO.listarPorUsuario(usuario);
            usuario.setPerfisUsuario(perfisUsuario);
        }
    }
    
    public void atualizarPerfis(List<Usuario> usuarios) {
        if (nonNull(usuarios)) {
            List<UsuarioPerfil> perfisUsuario = usuarioPerfilDAO.listarPorUsuarios(usuarios);
            usuarios.forEach(usuario -> usuario.setPerfisUsuario(usuarioPerfilLambda.filtrarPorUsuario(usuario, perfisUsuario)));
        }
    }
    
    public Usuario retornarUsuarioAutenticado(String login, String senha) throws ValidacaoExcecao, Exception {
        Usuario usuario = usuarioDAO.buscarAtivoPorLogin(login);
        validarExistenciaUsuarioLogin(usuario);
        validarSenhaUsuarioLogin(usuario, senha);
        return usuario;
    }
    
    public Usuario retornarUsuarioAutenticadoAPI(UsuarioJSON usuarioJSON) throws ValidacaoExcecao, Exception {
        validarUsuarioJSON(usuarioJSON);
        return retornarUsuarioAutenticado(usuarioJSON.getLogin(), usuarioJSON.getSenha());
    }

    // Salvamento
    public void salvar(Usuario usuario,
            List<UsuarioPerfil> perfisUsuarioSalvar,
            List<UsuarioPerfil> perfisUsuarioExcluir) throws ValidacaoExcecao, Exception {
        validarConfirmacaoSenha(usuario);
        definirVinculoUsuarioPerfil(usuario, perfisUsuarioSalvar);
        definirVinculoUsuarioPerfil(usuario, perfisUsuarioExcluir);
        // Erro ao salvar no banco
        if (!usuarioDAO.salvarComPerfis(
                usuario,
                perfisUsuarioSalvar,
                usuarioPerfilLambda.filtrarComIdMaiorQueZero(perfisUsuarioExcluir))) {
            throw new IllegalStateException();
        }
    }
    
    // Validações
    public void validarExistenciaUsuarioLogin(Usuario usuario) throws ValidacaoExcecao {
        if (isNull(usuario)) {
            throw new ValidacaoExcecao(traduzir("usuario.mensagem.erro.naoEncontrado"));
        }
    }
    
    public void validarSenhaUsuarioLogin(Usuario usuario, String senha) throws ValidacaoExcecao {
        if (!usuario.getSenha().equals(senha)) {
            throw new ValidacaoExcecao(traduzir("usuario.mensagem.erro.senhaIncorreta"));
        }
    }
    
    public void validarConfirmacaoSenha(Usuario usuario) throws ValidacaoExcecao {
        if (usuario.isSenhaNaoConfirmada()) {
            throw new ValidacaoExcecao(traduzir("usuario.mensagem.erro.confirmacaoSenha"));
        }
    }
    
    public void validarUsuarioJSON(UsuarioJSON usuarioJSON) throws ValidacaoExcecao {
        if (isNull(usuarioJSON)) {
            throw new ValidacaoExcecao(traduzirIngles("api.mensagem.erro.jsonInvalido"));
        }
    }
    
    // Definições
    private void definirVinculoUsuarioPerfil(Usuario usuario, List<UsuarioPerfil> perfisUsuario) {
        if (nonNull(perfisUsuario)) {
            perfisUsuario.forEach(up -> up.setUsuario(usuario));
        }
    }
    
    // Processamentos de listas
    public DualListModel<UsuarioPerfil> listarModeloDuploPerfisUsuarioPorUsuario(Usuario usuario) {
        List<UsuarioPerfil> perfis = perfilLambda.mapearParaUsuariosPerfis(usuario, perfilDAO.listarSemVinculoComUsuario(usuario));
        List<UsuarioPerfil> perfisUsuario = usuarioPerfilDAO.listarPorUsuario(usuario);
        return new DualListModel<>(perfis, perfisUsuario);
    }
}
