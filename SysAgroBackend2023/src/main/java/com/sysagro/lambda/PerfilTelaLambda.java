/*
 */
package com.sysagro.lambda;

import com.sysagro.enumeracao.TelaEnum;
import com.sysagro.modelo.entidade.PerfilTela;
import com.sysagro.util.LambdaUtil;
import com.sysagro.util.RegistroUtil;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author Pedro
 */
public class PerfilTelaLambda implements Serializable {

    private static final long serialVersionUID = 7421874781248712874L;
    
    // Lambdas
    private final Predicate<PerfilTela> filtrarComIdMaiorQueZero = (up) -> !RegistroUtil.isNovo(up.getId());
    private final Function<PerfilTela, TelaEnum> mapearParaTelaEnum = PerfilTela::getTelaEnum;
    private final Function<PerfilTela, String> mapearParaNomeTela = (up) -> up.getTelaEnum().name();
    private final Function<PerfilTela, String> mapearParaNomeModuloTela = (up) -> up.getTelaEnum().getModuloTelaEnum().name();

    // Processamentos de listas
    public List<PerfilTela> filtrarComIdMaiorQueZero(List<PerfilTela> telasPerfis) {
        return new LambdaUtil<PerfilTela, PerfilTela>().processarLP(telasPerfis, filtrarComIdMaiorQueZero);
    }
    
    public List<TelaEnum> mapearParaTelasEnums(List<PerfilTela> telasPerfis) {
        return new LambdaUtil<PerfilTela, TelaEnum>().processarLF(telasPerfis, mapearParaTelaEnum);
    }
    
    public List<String> mapearParaNomesTelas(List<PerfilTela> telasPerfis) {
        return new LambdaUtil<PerfilTela, String>().processarLFDI(telasPerfis, mapearParaNomeTela);
    }
    
    public List<String> mapearParaNomesModulosTelas(List<PerfilTela> telasPerfis) {
        return new LambdaUtil<PerfilTela, String>().processarLFDI(telasPerfis, mapearParaNomeModuloTela);
    }
    
    // Lambdas com parâmetros
}
