/*
 */
package com.sysagro.lambda;

import com.sysagro.enumeracao.TelaEnum;
import com.sysagro.modelo.entidade.Perfil;
import com.sysagro.modelo.entidade.PerfilTela;
import com.sysagro.util.LambdaUtil;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author Pedro
 */
public class TelaLambda implements Serializable {

    private static final long serialVersionUID = 12747812784812487L;
    
    // Processamentos de listas
    public List<PerfilTela> mapearParaPerfisTelas(Perfil perfil, List<TelaEnum> telasEnums) {
        return new LambdaUtil<TelaEnum, PerfilTela>().processarLF(telasEnums, mapearParaPerfilTela(perfil));
    }

    // Lambdas com parâmetros
    public Function<TelaEnum, PerfilTela> mapearParaPerfilTela(Perfil perfil) {
        return (telaEnum) -> new PerfilTela(telaEnum, perfil);
    }
}
