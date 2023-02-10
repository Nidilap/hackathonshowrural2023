/*
 */
package com.sysagro.modelo.servico;

import com.sysagro.excecao.ValidacaoExcecao;
import com.sysagro.lambda.PerfilLambda;
import com.sysagro.lambda.PerfilTelaLambda;
import com.sysagro.lambda.TelaLambda;
import com.sysagro.modelo.dao.PerfilDAO;
import com.sysagro.modelo.dao.PerfilTelaDAO;
import com.sysagro.modelo.entidade.Perfil;
import com.sysagro.modelo.entidade.PerfilTela;
import com.sysagro.modelo.entidade.Usuario;
import com.sysagro.util.LambdaUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.nonNull;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Pedro
 */
@Named
public class PerfilServico implements Serializable {
    
    private static final long serialVersionUID = 19042190582158912L;

    @Inject
    private PerfilDAO perfilDAO;
    @Inject
    private PerfilTelaDAO perfilTelaDAO;
    @Inject
    private TelaLambda telaLambda;
    @Inject
    private TelaServico telaServico;
    @Inject
    private PerfilLambda perfilLambda;
    @Inject
    private PerfilTelaLambda perfilTelaLambda;
    
    // Salvamento
    public void salvar(Perfil perfil,
            List<PerfilTela> telasPerfisSalvar,
            List<PerfilTela> telasPerfisExcluir) throws ValidacaoExcecao, Exception {
        definirVinculoPerfilTela(perfil, telasPerfisSalvar);
        definirVinculoPerfilTela(perfil, telasPerfisExcluir);
        // Erro ao salvar no banco
        if (!perfilDAO.salvarComTelas(
                perfil,
                telasPerfisSalvar,
                perfilTelaLambda.filtrarComIdMaiorQueZero(telasPerfisExcluir))) {
            throw new IllegalStateException();
        }
    }

    // Definições
    private void definirVinculoPerfilTela(Perfil perfil, List<PerfilTela> telasPerfis) {
        if (nonNull(telasPerfis)) {
            telasPerfis.forEach(pt -> pt.setPerfil(perfil));
        }
    }
    
    // Processamentos de listas
    public DualListModel<PerfilTela> listarModeloDuploTelasPerfisPorPerfil(Perfil perfil) {
        List<PerfilTela> telasPerfis = perfilTelaDAO.listarPorPerfil(perfil);
        List<PerfilTela> telas = telaLambda.mapearParaPerfisTelas(perfil, telaServico.listarSemVinculoComPerfil(perfil, telasPerfis));
        return new DualListModel<>(telas, telasPerfis);
    }
    
    public List<String> listarPerfisStringPorUsuario(Usuario usuario) {
        try {
            List<Perfil> perfisTemp = perfilDAO.listarPorUsuario(usuario);
            List<String> perfisStringTemp = perfilLambda.mapearParaNomesPerfisEnum(perfisTemp);
            return perfisStringTemp;
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }
    
    public List<Perfil> filtrarNaoSalvosBD(Usuario usuario, List<Perfil> perfis) {
        // Retorno apenas os perfis do usuário que ainda não foram salvos no banco
        List<Perfil> perfisBD = perfilDAO.listarPorUsuario(usuario);
        return new LambdaUtil<Perfil, Perfil>().processarLP(perfis, perfilLambda.filtrarSemExistenciaBD(perfisBD));
    }
}
