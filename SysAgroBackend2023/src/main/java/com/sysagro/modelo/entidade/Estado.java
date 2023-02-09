package com.sysagro.modelo.entidade;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "estado")
public class Estado extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 21587825871287L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estado")
    @SequenceGenerator(name = "seq_estado", sequenceName = "seq_estado", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;

    @Column(name = "ibge")
    private Integer ibge;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "uf", length = 10)
    private String uf;
    
    @Column(name = "ddd")
    private String ddd;
    
    @JoinColumn(name = "id_pais", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_estado_pais"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Pais pais;

    // Construtor
    public Estado() {
    }

    public Estado(String nome, String uf, String ddd, Integer ibge, Pais pais) {
        this.nome = nome;
        this.uf = uf;
        this.ddd = ddd;
        this.ibge = ibge;
        this.pais = pais;
    }

    // Getters && Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIbge() {
        return ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
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
        final Estado other = (Estado) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
