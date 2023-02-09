/* @Pedro
   -----
   Usado para armazenar o JSON de retorno das APIs
*/
package com.sysagro.modelo.dto.json;

import static com.sysagro.util.TextoUtil.traduzirIngles;
import java.io.Serializable;

/**
 *
 * @author Pedro
 */
public class ErroJSON implements Serializable {

    private static final long serialVersionUID = 82983823478327887L;

    // Variáveis
    private String mensagem;
    
    // Construtor
    public ErroJSON() {}

    public ErroJSON(String mensagem) {
        this.mensagem = mensagem;
    }

    // Geral
    public ErroJSON servidor() {
        setMensagem(traduzirIngles("api.mensagem.erro.servidor"));
        return this;
    }
    
    public ErroJSON sintaxeJSON() {
        setMensagem(traduzirIngles("api.mensagem.erro.jsonInvalido"));
        return this;
    }
    
    // Getters && Setters
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}