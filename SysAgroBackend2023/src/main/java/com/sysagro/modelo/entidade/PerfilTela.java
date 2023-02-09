package com.sysagro.modelo.entidade;

import com.sysagro.enumeracao.TelaEnum;
import com.sysagro.util.RegistroUtil;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "perfil_tela")
public class PerfilTela extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 8579847473858743578L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_perfil_tela")
    @SequenceGenerator(name = "seq_perfil_tela", sequenceName = "seq_perfil_tela", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;

    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;
    
    @Column(name = "tela", nullable = false)
    @Enumerated(EnumType.STRING)
    private TelaEnum telaEnum;

    @JoinColumn(name = "id_perfil", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_perfil_tela_perfil"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Perfil perfil;

    // Construtor
    public PerfilTela() {
    }

    public PerfilTela(TelaEnum telaEnum, Perfil perfil) {
        this.telaEnum = telaEnum;
        this.perfil = perfil;
    }
    
    // Sobrescritos
    @Override
    public boolean isNovo() {
        return RegistroUtil.isNovo(id);
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
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.telaEnum);
        hash = 89 * hash + Objects.hashCode(this.perfil);
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
        final PerfilTela other = (PerfilTela) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.telaEnum != other.telaEnum) {
            return false;
        }
        if (!Objects.equals(this.perfil, other.perfil)) {
            return false;
        }
        return true;
    }
}
