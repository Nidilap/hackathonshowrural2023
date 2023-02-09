/*
 */
package com.sysagro.modelo.servico;

import com.sysagro.enumeracao.ParametroEnum;
import com.sysagro.excecao.ValidacaoExcecao;
import com.sysagro.lambda.ParametroLambda;
import com.sysagro.modelo.dao.ParametroDAO;
import com.sysagro.modelo.entidade.Empresa;
import com.sysagro.modelo.entidade.Parametro;
import com.sysagro.util.ValidacaoUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author Pedro
 */
@Named
public class ParametroServico implements Serializable {
    
    private static final long serialVersionUID = 6512585371278517825L;

    @Inject
    private ParametroDAO parametroDAO;
    @Inject
    private ParametroLambda parametroLambda;

    // Salvamento
    public void salvarLista(List<Parametro> parametros) throws ValidacaoExcecao, Exception {
        if (!parametroDAO.salvarLista(parametros)) {
            throw new IllegalStateException();
        }
    }
    
    public void salvarListaNovos(List<Parametro> parametros) throws ValidacaoExcecao, Exception {
        List<Parametro> parametrosNovos = parametroLambda.filtrarNovos(parametros);
        if (!parametroDAO.salvarLista(parametrosNovos)) {
            throw new IllegalStateException();
        }
    }

    public void salvarRegistros(Parametro parametro) {
        atualizarValorPorRegistros(parametro);
        if (!parametroDAO.salvarComRegistros(parametro)) {
            throw new IllegalStateException();
        }
    }

    // Registros do parâmetro
    public void adicionarRegistroVazio(Parametro parametro) {
        ValidacaoUtil.validarParametroObrigatorio(parametro, "parametro");
        parametro.adicionarRegistro(new Parametro(
            true,
            null,
            null,
            parametro.getParametroEnum(),
            parametro.getEmpresa(),
            parametro
        ));
    }
    
    public void adicionarRegistroParaExcluir(int index, Parametro parametro) {
        ValidacaoUtil.validarParametroObrigatorio(parametro, "parametro");
        if (index >= 0) {
            parametro.adicionarRegistroParaExcluir(index);
        }
    }
    
    public void atualizarValorPorRegistros(Parametro parametro) {
        if (CollectionUtils.isNotEmpty(parametro.getRegistrosTR())) {
            parametro.setValor(parametroLambda.mapearParaString(parametro.getRegistrosTR(), "; "));
        } else {
            parametro.setValor(null);
        }
    }

    // Processamentos de listas
    public List<Parametro> listarParametrosPorEmpresaSemRegistros(Empresa empresa) {
        List<Parametro> parametros = parametroDAO.listarPorEmpresaSemRegistros(empresa);
        List<ParametroEnum> parametrosEnum = ParametroEnum.listar();
        mesclarComParametrosEnum(empresa, parametros, parametrosEnum);
        return parametros;
    }

    public void mesclarComParametrosEnum(Empresa empresa, List<Parametro> parametros, List<ParametroEnum> parametrosEnum) {
        for (Integer i = 0; i < parametrosEnum.size(); i++) {
            ParametroEnum parametroEnum = parametrosEnum.get(i);
            Parametro parametroFiltrado = parametroLambda.filtrarPorParametroEnum(parametroEnum, parametros);
            if (Objects.isNull(parametroFiltrado)) {
                parametros.add(new Parametro(parametroEnum, null, null, empresa));
            }
        }
    }
}
