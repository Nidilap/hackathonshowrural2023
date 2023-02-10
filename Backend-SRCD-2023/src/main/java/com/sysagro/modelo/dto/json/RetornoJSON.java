/* @Pedro
   -----
   Usado para armazenar o JSON de retorno das APIs
*/
package com.sysagro.modelo.dto.json;

import com.sysagro.enumeracao.StatusAPIEnum;
import com.sysagro.util.JsonUtil;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.util.Objects.isNull;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author Pedro
 * @param <T>
 */
public final class RetornoJSON<T> implements Serializable {

    private static final long serialVersionUID = 352378573825783L;

    // Variáveis
    private Integer codigoStatus;
    private String status;
    private T dados;
    private String tokenJWT;
    private List<ErroJSON> erros;
    
    // Construtor
    private RetornoJSON() {}
    
    public RetornoJSON(StatusAPIEnum statusAPIEnum) {
        this.codigoStatus = statusAPIEnum.getCodigoStatus();
        this.status = statusAPIEnum.name();
    }

    public RetornoJSON(Status status, T dados, String tokenJWT) {
        this.dados = dados;
        this.tokenJWT = tokenJWT;
        definirStatus(status);
    }
    
    public RetornoJSON(Status status, T dados) {
        this.dados = dados;
        definirStatus(status);
    }

    public RetornoJSON(Integer codigoStatus, String status) {
        this.codigoStatus = codigoStatus;
        this.status = status;
    }
    
    public RetornoJSON(Status status) {
        definirStatus(status);
    }
    
    // Geral
    public Response retornar() {
        return Response.status(codigoStatus)
            .entity(JsonUtil.criarGsonParaUsoAPI().toJson(this))
            .build();
    }
    
    public Response retornarSemCorpo() {
        return Response.status(codigoStatus)
            .build();
    }
    
    public RetornoJSON adicionarErro(ErroJSON erroJSON) {
        validarErros();
        erros.add(erroJSON);
        return this;
    }

    private void definirStatus(Status status) {
        this.codigoStatus = status.getStatusCode();
        this.status = status.name();
    }
    
    // Processamentos de listas
    private void validarErros() {
        if (isNull(erros)) {
            limparErros();
        }
    }
    
    private void limparErros() {
        erros = new ArrayList<>();
    }
    
    // Getters && Setters
    public Integer getCodigoStatus() {
        return codigoStatus;
    }

    public String getStatus() {
        return status;
    }

    public T getDados() {
        return dados;
    }

    public String getTokenJWT() {
        return tokenJWT;
    }
    
    // Getters && Setters (imutáveis em outras classes)
    public List<ErroJSON> getErros() {
        return CollectionUtils.isEmpty(erros) ? null : Collections.unmodifiableList(erros);
    }
}