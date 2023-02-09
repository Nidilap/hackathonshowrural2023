package com.sysagro.modelo.entidade;

import com.sysagro.enumeracao.TelaEnum;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "usuario_tela")
public class UsuarioTela extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 47325783278587235L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario_tela")
    @SequenceGenerator(name = "seq_usuario_tela", sequenceName = "seq_usuario_tela", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;

    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;
    
    @Column(name = "tela", nullable = false)
    @Enumerated(EnumType.STRING)
    private TelaEnum telaEnum;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_usuario_tela_usuario"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

    // Construtor
    public UsuarioTela() {
    }

    public UsuarioTela(TelaEnum telaEnum, Usuario usuario) {
        this.telaEnum = telaEnum;
        this.usuario = usuario;
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

    public TelaEnum getTelaEnum() {
        return telaEnum;
    }

    public void setTelaEnum(TelaEnum telaEnum) {
        this.telaEnum = telaEnum;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        final UsuarioTela other = (UsuarioTela) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
