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
@Table(name = "usuario_perfil")
public class UsuarioPerfil extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 38943489392859023L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario_perfil")
    @SequenceGenerator(name = "seq_usuario_perfil", sequenceName = "seq_usuario_perfil", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;
    
    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;
    
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false,
        foreignKey = @ForeignKey(name = "fk_usuario_perfil_usuario"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
    
    @JoinColumn(name = "id_perfil", referencedColumnName = "id", nullable = false,
        foreignKey = @ForeignKey(name = "fk_usuario_perfil_perfil"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Perfil perfil;
    
    // Construtor
    public UsuarioPerfil() {}

    public UsuarioPerfil(Usuario usuario, Perfil perfil) {
        this.usuario = usuario;
        this.perfil = perfil;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    // Equals && Hashcode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.usuario);
        hash = 59 * hash + Objects.hashCode(this.perfil);
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
        final UsuarioPerfil other = (UsuarioPerfil) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.perfil, other.perfil)) {
            return false;
        }
        return true;
    }
}