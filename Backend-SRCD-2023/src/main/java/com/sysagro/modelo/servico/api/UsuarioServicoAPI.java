/*
 */
package com.sysagro.modelo.servico.api;

import com.google.gson.JsonParseException;
import com.sysagro.enumeracao.StatusAPIEnum;
import com.sysagro.excecao.ValidacaoExcecao;
import com.sysagro.lambda.UsuarioLambda;
import com.sysagro.lambda.json.UsuarioLambdaJSON;
import com.sysagro.modelo.dao.UsuarioDAO;
import com.sysagro.modelo.dto.json.ErroJSON;
import com.sysagro.modelo.dto.json.RetornoJSON;
import com.sysagro.modelo.dto.json.UsuarioJSON;
import com.sysagro.modelo.entidade.Usuario;
import com.sysagro.modelo.fabrica.json.UsuarioFabricaJSON;
import com.sysagro.modelo.servico.UsuarioServico;
import static com.sysagro.util.ArquivoUtil.retornarChaveSecretaJWT;
import com.sysagro.util.ConfiguracaoUtil;
import com.sysagro.util.LocalDateUtil;
import static com.sysagro.util.LogUtil.exibirErro;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Pedro
 */
public class UsuarioServicoAPI implements Serializable {

    private static final long serialVersionUID = 4395892359823952329L;

    @Inject
    private UsuarioDAO usuarioDAO;
    @Inject
    private UsuarioServico usuarioServico;
    @Inject
    private UsuarioFabricaJSON usuarioFabricaJSON;
    @Inject
    private UsuarioLambda usuarioLambda;
    @Inject
    private UsuarioLambdaJSON usuarioLambdaJSON;

    // GET
    public Response listar() {
        try {
            List<Usuario> usuarios = usuarioDAO.listarTodosAPI();
            usuarioServico.atualizarPerfis(usuarios);
            return retornarJSON(usuarios);
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            return new RetornoJSON(Status.INTERNAL_SERVER_ERROR)
                .adicionarErro(new ErroJSON().servidor())
                .retornar();
        }
    }
    
    public Response buscarComFiltros(Long idUsuario) {
        try {
            Usuario usuario = usuarioDAO.buscarComFiltrosAPI(idUsuario);
            usuarioServico.atualizarPerfis(usuario);
            return retornarJSON(usuario);
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            return new RetornoJSON(Status.INTERNAL_SERVER_ERROR)
                .adicionarErro(new ErroJSON().servidor())
                .retornar();
        }
    }

    // POST
    public Response autenticar(String json) {
        try {
            UsuarioJSON usuarioJSON = usuarioLambdaJSON.mapearJSONParaObjeto(json);
            Usuario usuario = usuarioServico.retornarUsuarioAutenticadoAPI(usuarioJSON);
            usuarioServico.atualizarPerfis(usuario);
            String tokenJWT = retornarTokenJWT(usuario);
            return retornarTokenJWTJSON(usuario, tokenJWT);
        } catch (BadRequestException | JsonParseException ex) {
            exibirErro(getClass(), ex);
            return new RetornoJSON<>(Status.BAD_REQUEST)
                .adicionarErro(new ErroJSON().sintaxeJSON())
                .retornar();
        } catch (ValidacaoExcecao ex) {
            exibirErro(getClass(), ex);
            return new RetornoJSON(StatusAPIEnum.UNPROCESSABLE_ENTITY)
                .adicionarErro(new ErroJSON(ex.retornarDescricaoCompleta()))
                .retornar();
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            return new RetornoJSON<>(Status.INTERNAL_SERVER_ERROR)
                .adicionarErro(new ErroJSON().servidor())
                .retornar();
        }
    }

    // Criação do Token JWT
    private String retornarTokenJWT(Usuario usuario) {
        SecretKey chaveSecretaJWT = retornarChaveSecretaJWT();
        return Jwts.builder()
            .setSubject(usuario.getNome())
            .setIssuer(ConfiguracaoUtil.URL_SERVIDOR)
            .setIssuedAt(new Date())
            .setExpiration(LocalDateUtil.converterParaDate(LocalDate.now().plusDays(1)))
            .signWith(chaveSecretaJWT, SignatureAlgorithm.HS512)
            .compact();
    }

    // Retorno de JSON
    public Response retornarTokenJWTJSON(Usuario usuario, String tokenJWT) {
        UsuarioJSON json = usuarioFabricaJSON.criarComPerfis(usuario, usuario.getPerfisUsuario());
        return new RetornoJSON<>(Status.OK, json, tokenJWT).retornar();
    }
    
    public Response retornarJSON(Usuario usuario) {
        UsuarioJSON json = usuarioFabricaJSON.criarComPerfis(usuario, usuario.getPerfisUsuario());
        return new RetornoJSON<>(Status.OK, json).retornar();
    }
    
    public Response retornarJSON(List<Usuario> usuarios) {
        List<UsuarioJSON> listaJSONs = usuarioLambda.mapearParaUsuariosJSON(usuarios);
        return new RetornoJSON<>(Status.OK, listaJSONs).retornar();
    }
}