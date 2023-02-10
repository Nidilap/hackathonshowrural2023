package com.sysagro.modelo.entidade;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "endereco")
public class Endereco extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 9251901290519259L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
    @SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;
    
    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;
    
    @Column(name = "is_ativo", nullable = false)
    private boolean isAtivo = true;
    
    @Column(name = "is_principal", nullable = false)
    private boolean isPrincipal;
    
    @Column(name = "numero")
    private Integer numero;
    
    @Column(name = "endereco", nullable = false) // Endereço, Rua
    private String endereco;
    
    @Column(name = "bairro")
    private String bairro;
    
    @Column(name = "complemento", length = 20)
    private String complemento;
    
    @Column(name = "cep", length = 20)
    private String cep;
    
    @Column(name = "inscricao_estadual", length = 20)
    private String inscricaoEstadual;
    
    @Column(name = "inscricao_municipal", length = 20)
    private String inscricaoMunicipal;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @JoinColumn(name = "id_cidade", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_endereco_cidade"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Cidade cidade;

    // Construtor
    public Endereco() {
    }

    public Endereco(boolean isAtivo, boolean isPrincipal, Integer numero, String endereco, String bairro, String complemento,
            String cep, String inscricaoEstadual, String inscricaoMunicipal, String observacao, Cidade cidade) {
        this.isAtivo = isAtivo;
        this.isPrincipal = isPrincipal;
        this.numero = numero;
        this.endereco = endereco;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cep = cep;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.observacao = observacao;
        this.cidade = cidade;
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

    public boolean isIsPrincipal() {
        return isPrincipal;
    }

    public void setIsPrincipal(boolean isPrincipal) {
        this.isPrincipal = isPrincipal;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
        final Endereco other = (Endereco) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
