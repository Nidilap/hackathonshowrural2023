package com.sysagro.modelo.entidade;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "pessoa_contato")
public class PessoaContato extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 35923095093259583L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa_contato")
    @SequenceGenerator(name = "seq_pessoa_contato", sequenceName = "seq_pessoa_contato", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;
    
    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;
    
    @JoinColumn(name = "id_contato", referencedColumnName = "id", nullable = false,
        foreignKey = @ForeignKey(name = "fk_pessoa_contato_contato"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Contato contato;
    
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false,
        foreignKey = @ForeignKey(name = "fk_pessoa_contato_pessoa"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Pessoa pessoa;
    
    // Construtor
    public PessoaContato() {}

    public PessoaContato(Contato contato, Pessoa pessoa) {
        this.contato = contato;
        this.pessoa = pessoa;
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

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    // Equals && Hashcode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final PessoaContato other = (PessoaContato) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}