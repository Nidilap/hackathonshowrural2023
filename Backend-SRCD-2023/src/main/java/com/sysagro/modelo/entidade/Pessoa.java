package com.sysagro.modelo.entidade;

import com.sysagro.enumeracao.EstadoCivilEnum;
import com.sysagro.enumeracao.TipoPessoaEnum;
import static com.sysagro.util.TextoUtil.extrairSomenteNumeros;
import java.io.Serializable;
import java.time.ZonedDateTime;
import static java.util.Objects.nonNull;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "pessoa")
public class Pessoa extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 48689303491294921L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;
    
    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;
    
    @Column(name = "is_ativo", nullable = false)
    private boolean isAtivo = true;
    
    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;
    
    @Column(name = "nome_fantasia")
    private String nomeFantasia;
    
    @Column(name = "nome_pai")
    private String nomePai;
    
    @Column(name = "nome_mae")
    private String nomeMae;
    
    @Column(name = "cpf_cnpj", length = 20, unique = true)
    private String cpfCnpj;
    
    @Column(name = "rg", length = 20, unique = true)
    private String rg;
    
    @Column(name = "telefone", length = 20) // Telefone principal
    private String telefone;
    
    @Column(name = "celular", length = 20) // Celular principal
    private String celular;
    
    @Column(name = "email") // Email principal
    private String email;
    
    @Column(name = "atividade")
    private String atividade;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;
    
    @Column(name = "data_hora_nascimento")
	private ZonedDateTime dataHoraNascimento;
    
    @Column(name = "estado_civil", length = 10)
    @Enumerated(EnumType.STRING)
    private EstadoCivilEnum estadoCivilEnum;
    
    @Column(name = "tipo_pessoa", length = 10)
    @Enumerated(EnumType.STRING)
    private TipoPessoaEnum tipoPessoaEnum;
    
    @JoinColumn(name = "id_foto", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_pessoa_foto"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Anexo foto;

    // Construtor
    public Pessoa() {
    }

    public Pessoa(boolean isAtivo, String razaoSocial, String nomeFantasia, String nomePai, String nomeMae, String cpfCnpj,
            String rg, String telefone, String celular, String email, String atividade,
            String observacao, ZonedDateTime dataHoraNascimento, EstadoCivilEnum estadoCivilEnum, TipoPessoaEnum tipoPessoaEnum, Anexo foto) {
        this.isAtivo = isAtivo;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.cpfCnpj = cpfCnpj;
        this.rg = rg;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
        this.atividade = atividade;
        this.observacao = observacao;
        this.dataHoraNascimento = dataHoraNascimento;
        this.estadoCivilEnum = estadoCivilEnum;
        this.tipoPessoaEnum = tipoPessoaEnum;
        this.foto = foto;
    }
    
    // Listeners
    @PrePersist
    public void prePersistEntidade() {
        validarCPFCNPJ();
    }
    
    @PreUpdate
    public void preUpdateEntidade() {
        validarCPFCNPJ();
    }

    // Geral
    private boolean isCPFValido() {
        return nonNull(cpfCnpj)
            && extrairSomenteNumeros(cpfCnpj).length() != 11;
    }

    private void validarCPFCNPJ() {
        if (isCPFValido()) {
            cpfCnpj = extrairSomenteNumeros(cpfCnpj);
        } else {
            cpfCnpj = null;
        }
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

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
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

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public ZonedDateTime getDataHoraNascimento() {
        return dataHoraNascimento;
    }

    public void setDataHoraNascimento(ZonedDateTime dataHoraNascimento) {
        this.dataHoraNascimento = dataHoraNascimento;
    }

    public EstadoCivilEnum getEstadoCivilEnum() {
        return estadoCivilEnum;
    }

    public void setEstadoCivilEnum(EstadoCivilEnum estadoCivilEnum) {
        this.estadoCivilEnum = estadoCivilEnum;
    }

    public TipoPessoaEnum getTipoPessoaEnum() {
        return tipoPessoaEnum;
    }

    public void setTipoPessoaEnum(TipoPessoaEnum tipoPessoaEnum) {
        this.tipoPessoaEnum = tipoPessoaEnum;
    }

    public Anexo getFoto() {
        return foto;
    }

    public void setFoto(Anexo foto) {
        this.foto = foto;
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
        final Pessoa other = (Pessoa) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
