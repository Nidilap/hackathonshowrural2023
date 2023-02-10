package com.sysagro.modelo.entidade;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "solicitacao_pessoa")
public class SolicitacaoPessoa extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 92148928914891289L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_solicitacao_pessoa")
    @SequenceGenerator(name = "seq_solicitacao_pessoa", sequenceName = "seq_solicitacao_pessoa", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;
    
    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_solicitacao_pessoa_pessoa"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Pessoa pessoa;
    
    @JoinColumn(name = "id_endereco", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_solicitacao_pessoa_endereco"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Endereco endereco;
    
    // Construtor
    public SolicitacaoPessoa() {
    }

    public SolicitacaoPessoa(String observacao, Pessoa pessoa, Endereco endereco) {
        this.observacao = observacao;
        this.pessoa = pessoa;
        this.endereco = endereco;
    }
    
    // Getters && Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersao() {
        return versao;
    }

    private void setVersao(Integer versao) {
        this.versao = versao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    // Equals && Hashcode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SolicitacaoPessoa other = (SolicitacaoPessoa) obj;
        return Objects.equals(this.id, other.id);
    }
}
