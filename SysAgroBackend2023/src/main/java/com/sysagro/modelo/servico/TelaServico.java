/*
 */
package com.sysagro.modelo.servico;

import com.sysagro.enumeracao.TelaEnum;
import com.sysagro.lambda.PerfilTelaLambda;
import com.sysagro.modelo.dao.PerfilTelaDAO;
import com.sysagro.modelo.entidade.Perfil;
import com.sysagro.modelo.entidade.PerfilTela;
import com.sysagro.modelo.entidade.Usuario;
import java.io.Serializable;
import java.util.List;
import static java.util.Objects.isNull;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.compress.utils.Lists;

/**
 *
 * @author Pedro
 */
@Named
public class TelaServico implements Serializable {
    
    private static final long serialVersionUID = 92398128931928241L;
    
    @Inject
    private PerfilTelaDAO perfilTelaDAO;
    @Inject
    private PerfilTelaLambda perfilTelaLambda;
    
    // Processamentos de listas
    public List<String> listarNomesPorUsuario(Usuario usuario) {
        if (isNull(usuario)) {
            return Lists.newArrayList();
        } else {
            List<PerfilTela> telasPerfis = perfilTelaDAO.listarPorUsuario(usuario);
            return perfilTelaLambda.mapearParaNomesTelas(telasPerfis);
        }
    }
    
    public List<String> listarNomesModulosTelasPorUsuario(Usuario usuario) {
        if (isNull(usuario)) {
            return Lists.newArrayList();
        } else {
            List<PerfilTela> telasPerfis = perfilTelaDAO.listarPorUsuario(usuario);
            return perfilTelaLambda.mapearParaNomesModulosTelas(telasPerfis);
        }
    }
    
    public List<TelaEnum> listarSemVinculoComPerfil(Perfil perfil, List<PerfilTela> telasPerfil) {
        List<TelaEnum> telasEnums = TelaEnum.listar();
        List<TelaEnum> telasEnumsPerfil = perfilTelaLambda.mapearParaTelasEnums(telasPerfil);
        telasEnums.removeAll(telasEnumsPerfil);
        return telasEnums;
    }
}
