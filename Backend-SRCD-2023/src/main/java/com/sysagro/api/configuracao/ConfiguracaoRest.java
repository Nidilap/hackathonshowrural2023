package com.sysagro.api.configuracao;

import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 *
 * @author Pedro
 */
@ApplicationPath("/rs")
public class ConfiguracaoRest extends Application {

    // Geral
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> recursos = new java.util.HashSet<>();
        adicionarClassesRest(recursos);
        adicionarClassesSwagger(recursos);
        return recursos;
    }

    private void adicionarClassesRest(Set<Class<?>> recursos) {
        recursos.add(com.sysagro.anotacao.implementacao.AutorizarFiltro.class);
        recursos.add(com.sysagro.api.PessoaAPI.class);
        recursos.add(com.sysagro.api.SolicitacaoPessoaAPI.class);
        recursos.add(com.sysagro.api.StatusAPI.class);
        recursos.add(com.sysagro.api.UsuarioAPI.class);
        recursos.add(com.sysagro.api.VisitaAPI.class);
    }

    private void adicionarClassesSwagger(Set<Class<?>> recursos) {
        recursos.add(OpenApiResource.class);
        recursos.add(AcceptHeaderOpenApiResource.class);
    }
}
