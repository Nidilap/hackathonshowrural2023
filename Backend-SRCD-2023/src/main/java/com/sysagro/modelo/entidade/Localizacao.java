package com.sysagro.modelo.entidade;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.envers.Audited;
import org.locationtech.jts.geom.Coordinate;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "localizacao")
public class Localizacao extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 621762671672671L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_localizacao")
    @SequenceGenerator(name = "seq_localizacao", sequenceName = "seq_localizacao", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;

    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;

    @Column(name = "coordenada") // Latitude (X), Longitude (Y) e Altitude (Z)
    private Coordinate coordenada;

    // Construtor
    public Localizacao() {
    }

    public Localizacao(ZonedDateTime dataHoraCriacao, Coordinate coordenada) {
        super.dataHoraCriacao = dataHoraCriacao;
        this.coordenada = coordenada;
    }
    
    // Geral
    public double retornarLatitude() {
        return Objects.isNull(coordenada) ? 0d : coordenada.getX();
    }

    public double retornarLongitude() {
        return Objects.isNull(coordenada) ? 0d : coordenada.getY();
    }

    public double retornarAltitude() {
        return Objects.isNull(coordenada) ? 0d : coordenada.getZ();
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

    public Coordinate getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordinate coordenada) {
        this.coordenada = coordenada;
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
        final Localizacao other = (Localizacao) obj;
        return Objects.equals(this.id, other.id);
    }
}
