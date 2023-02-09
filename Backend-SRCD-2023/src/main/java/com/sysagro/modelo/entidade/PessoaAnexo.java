package com.sysagro.modelo.entidade;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "pessoa_anexo")
public class PessoaAnexo extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 21589125892189516L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;
    
    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;
    
    @JoinColumn(name = "id_anexo", referencedColumnName = "id", nullable = false,
        foreignKey = @ForeignKey(name = "fk_pessoa_anexo_anexo"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Anexo anexo;
    
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false,
        foreignKey = @ForeignKey(name = "fk_pessoa_anexo_pessoa"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Pessoa pessoa;
    
    // Construtor
    public PessoaAnexo() {}

    public PessoaAnexo(Anexo anexo, Pessoa pessoa) {
        this.anexo = anexo;
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

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
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
        final PessoaAnexo other = (PessoaAnexo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}