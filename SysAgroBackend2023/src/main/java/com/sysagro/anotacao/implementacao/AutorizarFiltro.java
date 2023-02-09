/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.anotacao.implementacao;

import com.sysagro.anotacao.Autorizar;
import com.sysagro.modelo.dto.json.ErroJSON;
import com.sysagro.modelo.dto.json.RetornoJSON;
import static com.sysagro.util.ArquivoUtil.retornarChaveSecretaJWT;
import static com.sysagro.util.TextoUtil.traduzirIngles;
import io.jsonwebtoken.Jwts;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import javax.crypto.SecretKey;

/**
 *
 * @author Pedro
 */
@Autorizar
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AutorizarFiltro implements ContainerRequestFilter {

    // Geral
    @Override
    public void filter(ContainerRequestContext requisicao) throws IOException {
        try {
            SecretKey chaveSecretaJWT = retornarChaveSecretaJWT();
            // Token que veio no Header
            String autorizacaoHeader = requisicao.getHeaderString(HttpHeaders.AUTHORIZATION);
            String tokenHeader = autorizacaoHeader.substring("Bearer".length()).trim();
            // Definição de atributos da sessão
            definirAtributosSessao(requisicao);
            // Validação
            Jwts.parserBuilder().setSigningKey(chaveSecretaJWT).build().parseClaimsJws(tokenHeader);
        } catch (Exception ex) {
            requisicao.abortWith(new RetornoJSON(Status.UNAUTHORIZED)
                .adicionarErro(new ErroJSON(traduzirIngles("api.mensagem.erro.acessoNaoAutorizado")))
                .retornar());
        }
    }
    
    private void definirAtributosSessao(ContainerRequestContext requisicao) {
    }
}