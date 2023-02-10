/*
 */
package com.sysagro.lambda.json;

import com.google.gson.reflect.TypeToken;
import com.sysagro.modelo.dto.json.VisitaJSON;
import com.sysagro.util.JsonUtil;
import java.io.Serializable;
import java.lang.reflect.Type;

/**
 *
 * @author Pedro
 */
public class VisitaLambdaJSON implements Serializable {

    private static final long serialVersionUID = 91828912891289128L;

    // Lambdas
    private final Type typeVisitaJSON = new TypeToken<VisitaJSON>() {
    }.getType();

    // Processamentos de listas
    public VisitaJSON mapearJSONParaObjeto(String json) {
        return JsonUtil.criarGsonParaUsoAPI().fromJson(json, typeVisitaJSON);
    }
}
