/*
 */
package com.sysagro.lambda.json;

import com.google.gson.reflect.TypeToken;
import com.sysagro.modelo.dto.json.UsuarioJSON;
import com.sysagro.util.JsonUtil;
import java.io.Serializable;
import java.lang.reflect.Type;

/**
 *
 * @author Pedro
 */
public class UsuarioLambdaJSON implements Serializable {

    private static final long serialVersionUID = 2150125821889214L;

    // Lambdas
    private final Type typeUsuarioJSON = new TypeToken<UsuarioJSON>() {
    }.getType();
    
    // Processamentos de listas
    public UsuarioJSON mapearJSONParaObjeto(String json) {
        return JsonUtil.criarGsonParaUsoAPI().fromJson(json, typeUsuarioJSON);
    }
    
    // Lambdas com parâmetros
}
