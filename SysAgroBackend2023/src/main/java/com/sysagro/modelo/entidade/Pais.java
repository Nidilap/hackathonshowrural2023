package com.sysagro.modelo.entidade;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "pais")
public class Pais extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 21187257891258L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pais")
    @SequenceGenerator(name = "seq_pais", sequenceName = "seq_pais", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;

    @Column(name = "bacen")
    private Integer bacen;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "nome_pt")
    private String nomePT;
    
    @Column(name = "sigla", length = 10)
    private String sigla;
    
    // Construtor
    public Pais() {
    }

    public Pais(String nome, String nomePT, String sigla, Integer bacen) {
        this.nome = nome;
        this.nomePT = nomePT;
        this.sigla = sigla;
        this.bacen = bacen;
    }
    
    // Getters && Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBacen() {
        return bacen;
    }

    public void setBacen(Integer bacen) {
        this.bacen = bacen;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomePT() {
        return nomePT;
    }

    public void setNomePT(String nomePT) {
        this.nomePT = nomePT;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    // Equals && Hashcode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Pais other = (Pais) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
