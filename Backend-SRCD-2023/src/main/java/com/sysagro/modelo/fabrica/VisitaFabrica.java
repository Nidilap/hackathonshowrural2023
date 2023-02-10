package com.sysagro.modelo.fabrica;

import com.sysagro.modelo.dao.EnderecoDAO;
import com.sysagro.modelo.dao.PessoaDAO;
import com.sysagro.modelo.dao.UsuarioDAO;
import com.sysagro.modelo.dto.json.VisitaJSON;
import com.sysagro.modelo.entidade.Endereco;
import com.sysagro.modelo.entidade.Pessoa;
import com.sysagro.modelo.entidade.Usuario;
import com.sysagro.modelo.entidade.Visita;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
public class VisitaFabrica implements Serializable {

    private static final long serialVersionUID = 91298219821891289L;

    @Inject
    private PessoaDAO pessoaDAO;
    @Inject
    private UsuarioDAO usuarioDAO;
    @Inject
    private EnderecoDAO enderecoDAO;
    @Inject
    private LocalizacaoFabrica localizacaoFabrica;
    
    // Geral
    public Visita criar(VisitaJSON visitaJSON) {
        try {
            Visita visita = new Visita(
                visitaJSON.getDataAgendada(),
                visitaJSON.getObservacao(),
                localizacaoFabrica.criarComValidacaoExistencia(visitaJSON.getCheckIn()),
                localizacaoFabrica.criarComValidacaoExistencia(visitaJSON.getCheckOut()),
                usuarioDAO.buscarPorId(visitaJSON.getIdFuncionario(), Usuario.class),
                pessoaDAO.buscarPorId(visitaJSON.getIdPessoa(), Pessoa.class),
                enderecoDAO.buscarPorId(visitaJSON.getIdEndereco(), Endereco.class));
            return visita;
        } catch (NullPointerException ex) {
            return null;
        }
    }
}
