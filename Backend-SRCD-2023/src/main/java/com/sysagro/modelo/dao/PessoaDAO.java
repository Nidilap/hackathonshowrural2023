/*
 */
package com.sysagro.modelo.dao;

import com.sysagro.modelo.entidade.Endereco;
import com.sysagro.modelo.entidade.Pessoa;
import com.sysagro.modelo.entidade.PessoaEndereco;
import static com.sysagro.util.LogUtil.exibirErro;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 *
 * @author Pedro
 */
@Named
public class PessoaDAO extends AbstratoDAO<Pessoa> implements Serializable {

    private static final long serialVersionUID = 8127487128472187421L;
    
    @Inject
    private EnderecoDAO enderecoDAO;
    @Inject
    private PessoaEnderecoDAO pessoaEnderecoDAO;

    // Geral
    public List<Pessoa> listarTodos() {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT pess \n")
            .append("  FROM Pessoa pess \n")
            .append("     LEFT JOIN FETCH pess.vinculosEnderecos vincEnder \n")
            .append("    INNER JOIN FETCH vincEnder.endereco ender \n")
            .append("     LEFT JOIN FETCH ender.cidade cid \n")
            .append("     LEFT JOIN FETCH ender.localizacao local \n")
            .append(" ORDER BY pess.razaoSocial \n");
        return em.createQuery(sql.toString()).getResultList();
    }
    
    // API
    public List<Pessoa> listarTodosAPI() {
        StringBuilder sql = new StringBuilder("")
            .append("SELECT pess \n")
            .append("  FROM Pessoa pess \n")
            .append("     LEFT JOIN FETCH pess.vinculosEnderecos vincEnder \n")
            .append("    INNER JOIN FETCH vincEnder.endereco ender \n")
            .append("     LEFT JOIN FETCH ender.cidade cid \n")
            .append("     LEFT JOIN FETCH ender.localizacao local \n")
            .append(" ORDER BY pess.razaoSocial \n");
        return em.createQuery(sql.toString()).getResultList();
    }
    
    // Inserção, atualização e exclusão
    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = {Exception.class})
    public boolean salvarCompleto(List<Pessoa> pessoas, List<Endereco> enderecos, List<PessoaEndereco> pessoasEnderecos) {
        try {
            salvarListaSemTransacao(pessoas);
            enderecoDAO.salvarListaSemTransacao(enderecos);
            pessoaEnderecoDAO.salvarListaSemTransacao(pessoasEnderecos);
            return true;
        } catch (Exception ex) {
            exibirErro(getClass(), ex);
            return false;
        }
    }
}
