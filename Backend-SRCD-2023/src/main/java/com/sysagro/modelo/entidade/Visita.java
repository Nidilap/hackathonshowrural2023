package com.sysagro.modelo.entidade;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "visita")
public class Visita extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 9129821899182981L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_visita")
    @SequenceGenerator(name = "seq_visita", sequenceName = "seq_visita", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;
    
    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;
    
    @Column(name = "data_agendada")
    private ZonedDateTime dataAgendada;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;
    
    @JoinColumn(name = "id_check_in", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_visita_check_in"))
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Localizacao checkIn;
    
    @JoinColumn(name = "id_check_out", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_visita_check_out"))
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Localizacao checkOut;
    
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_visita_funcionario"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario funcionario;
    
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_visita_pessoa"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Pessoa pessoa;
    
    @JoinColumn(name = "id_endereco", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_visita_endereco"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Endereco endereco;
    
    // Construtor
    public Visita() {
    }

    public Visita(ZonedDateTime dataAgendada, String observacao, Localizacao checkIn, Localizacao checkOut, Usuario funcionario, Pessoa pessoa, Endereco endereco) {
        this.dataAgendada = dataAgendada;
        this.observacao = observacao;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.funcionario = funcionario;
        this.pessoa = pessoa;
        this.endereco = endereco;
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

    public ZonedDateTime getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(ZonedDateTime dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Localizacao getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Localizacao checkIn) {
        this.checkIn = checkIn;
    }

    public Localizacao getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Localizacao checkOut) {
        this.checkOut = checkOut;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Usuario funcionario) {
        this.funcionario = funcionario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    // Equals && Hashcode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Visita other = (Visita) obj;
        return Objects.equals(this.id, other.id);
    }
}
