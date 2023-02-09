/*
 */
package com.sysagro.lambda;

import com.sysagro.enumeracao.ParametroEnum;
import com.sysagro.enumeracao.TipoParametroEnum;
import com.sysagro.modelo.entidade.Parametro;
import com.sysagro.util.LambdaUtil;
import com.sysagro.util.RegistroUtil;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Pedro
 */
public class ParametroLambda implements Serializable {

    private static final long serialVersionUID = 98512895981298582L;
    
    // Lambdas
    private final Predicate<Parametro> filtrarNovo = (p) -> RegistroUtil.isNovo(p.getId());
    private final Predicate<Parametro> filtrarSemValorNulo = (p) -> StringUtils.isNotBlank(p.getValor());
    private final Function<Parametro, String> mapearParaValor = Parametro::getValor;
    
    // Processamentos de listas
    public String mapearParaString(List<Parametro> parametros, String delimitador) {
        return new LambdaUtil<Parametro, String>().unificarLPFDE(parametros, filtrarSemValorNulo, mapearParaValor, delimitador);
    }
    
    public Parametro filtrarPorParametroEnum(ParametroEnum parametroEnum, List<Parametro> parametros) {
        return new LambdaUtil<Parametro, Parametro>().processarLPOE(parametros, filtrarPorParametroEnum(parametroEnum), null);
    }
    
    public List<Parametro> filtrarNovos(List<Parametro> parametros) {
        return new LambdaUtil<Parametro, Parametro>().processarLP(parametros, filtrarNovo);
    }
    
    public List<Parametro> filtrarPorTipoParametroEnum(TipoParametroEnum tipoParametroEnum, List<Parametro> parametros) {
        return new LambdaUtil<Parametro, Parametro>().processarLP(parametros, filtrarPorTipoParametroEnum(tipoParametroEnum));
    }
    
    // Lambdas com parâmetros
    public Predicate<Parametro> filtrarPorParametroEnum(ParametroEnum parametroEnum) {
        return (parametro) -> parametro.getParametroEnum().equals(parametroEnum);
    }
    
    public Predicate<Parametro> filtrarPorTipoParametroEnum(TipoParametroEnum tipoParametroEnum) {
        return (parametro) -> parametro.getParametroEnum().getTipoParametroEnum().equals(tipoParametroEnum);
    }
}
