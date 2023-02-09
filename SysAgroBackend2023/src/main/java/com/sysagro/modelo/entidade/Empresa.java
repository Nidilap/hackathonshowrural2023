/* @Pedro
   -----
   Filial para Login
*/
package com.sysagro.modelo.entidade;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "empresa")
public class Empresa extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 1932821893891983L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_empresa")
    @SequenceGenerator(name = "seq_empresa", sequenceName = "seq_empresa", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;
    
    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;
    
    @Column(name = "is_ativo", nullable = false)
    private boolean isAtivo = true;
    
    @Column(name = "is_matriz", nullable = false)
    private boolean isMatriz;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "codigo", length = 20)
    private String codigo;
    
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", unique = true,
        foreignKey = @ForeignKey(name = "fk_empresa_pessoa"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Pessoa pessoa;

    // Construtor
    public Empresa() {
    }

    public Empresa(boolean isAtivo, boolean isMatriz, String nome, String codigo, Pessoa pessoa) {
        this.isAtivo = isAtivo;
        this.isMatriz = isMatriz;
        this.nome = nome;
        this.codigo = codigo;
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

    public boolean isIsAtivo() {
        return isAtivo;
    }

    public void setIsAtivo(boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    public boolean isIsMatriz() {
        return isMatriz;
    }

    public void setIsMatriz(boolean isMatriz) {
        this.isMatriz = isMatriz;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        int hash = 3;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Empresa other = (Empresa) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
