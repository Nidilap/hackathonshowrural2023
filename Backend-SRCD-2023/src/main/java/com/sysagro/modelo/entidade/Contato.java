package com.sysagro.modelo.entidade;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "contato")
public class Contato extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 8792105832598382L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_contato")
    @SequenceGenerator(name = "seq_contato", sequenceName = "seq_contato", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;
    
    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;

    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "telefone", length = 20) // Telefone
    private String telefone;
    
    @Column(name = "celular", length = 20) // Celular
    private String celular;
    
    @Column(name = "email") // Email
    private String email;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    // Construtor
    public Contato() {
    }

    public Contato(String nome, String telefone, String celular, String email, String observacao) {
        this.nome = nome;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
        this.observacao = observacao;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    // Equals && Hashcode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Contato other = (Contato) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
