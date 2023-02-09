package com.sysagro.modelo.entidade;

import com.sysagro.enumeracao.PerfilEnum;
import com.sysagro.util.RegistroUtil;
import static com.sysagro.util.TextoUtil.converterMaiusculo;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "perfil")
public class Perfil extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 1275812581285912L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_perfil")
    @SequenceGenerator(name = "seq_perfil", sequenceName = "seq_perfil", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;
    
    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;
    
    @Column(name = "sigla", length = 10)
    private String sigla;
    
    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;
    
    @Column(name = "perfil", length = 10)
    @Enumerated(EnumType.STRING)
    private PerfilEnum perfilEnum = PerfilEnum.GENER;

    // Construtor
    public Perfil() {
    }

    public Perfil(String nome, String sigla, String observacao, PerfilEnum perfilEnum) {
        this.nome = nome;
        this.sigla = sigla;
        this.observacao = observacao;
        this.perfilEnum = perfilEnum;
    }
    
    // Listeners
    @PrePersist
    public void prePersistEntidade() {
        converterAtributosMaiusculo();
    }

    @PreUpdate
    public void preUpdateEntidade() {
        converterAtributosMaiusculo();
    }
    
    // Geral
    public Perfil processarParaQuery() {
        return RegistroUtil.isNovo(id) ? null : this;
    }
    
    private void converterAtributosMaiusculo() {
        nome = converterMaiusculo(nome);
        sigla = converterMaiusculo(sigla);
        observacao = converterMaiusculo(observacao);
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public PerfilEnum getPerfilEnum() {
        return perfilEnum;
    }

    public void setPerfilEnum(PerfilEnum perfilEnum) {
        this.perfilEnum = perfilEnum;
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
        final Perfil other = (Perfil) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}