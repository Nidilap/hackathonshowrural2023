package com.sysagro.modelo.entidade;

import java.io.Serializable;
import static java.util.Objects.isNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "cidade")
public class Cidade extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 82388213879218938L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cidade")
    @SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;

    @Column(name = "ibge")
    private Integer ibge;

    @Column(name = "codigo_municipio")
    private Integer codigoMunicipio;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "nome", nullable = false)
    private String nome;

    @JoinColumn(name = "id_estado", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_cidade_estado"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Estado estado;

    // Construtor
    public Cidade() {
    }

    public Cidade(String nome, Integer ibge, Integer codigoMunicipio, double latitude, double longitude, Estado estado) {
        this.nome = nome;
        this.ibge = ibge;
        this.codigoMunicipio = codigoMunicipio;
        this.latitude = latitude;
        this.longitude = longitude;
        this.estado = estado;
    }
    
    // Geral
    public String retornarComUF() {
        return new StringBuilder(nome)
            .append(" (")
            .append(isNull(estado) ? "" : estado.getUf())
            .append(")")
            .toString();
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

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
        final Cidade other = (Cidade) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
