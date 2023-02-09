package com.sysagro.modelo.entidade;

import com.sysagro.util.ArquivoUtil;
import static com.sysagro.util.TextoUtil.converterMaiusculo;
import java.io.Serializable;
import java.math.BigDecimal;
import static java.util.Objects.isNull;
import javax.persistence.*;
import org.hibernate.envers.Audited;
import org.primefaces.model.StreamedContent;
import static com.sysagro.util.NumeroUtil.criarBigDecimal;
import com.sysagro.util.RegistroUtil;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "anexo")
public class Anexo extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 79129858912592815L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_anexo")
    @SequenceGenerator(name = "seq_anexo", sequenceName = "seq_anexo", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;

    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;

    @Column(name="tamanho")
    private Long tamanho;

    @Column(name="conteudo", nullable = false)
    private byte[] conteudo;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name="tipo", nullable = false)
    private String tipo;

    // Construtor
    public Anexo() {}

    public Anexo(Long tamanho, byte[] conteudo, String nome, String tipo) {
        this.tamanho = tamanho;
        this.conteudo = conteudo;
        this.nome = nome;
        this.tipo = tipo;
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
    public String retornarBase64() {
        return isNull(conteudo) ? null : ArquivoUtil.converterParaBase64(conteudo);
    }
    
    public StreamedContent retornarStreamedContent() {
        return ArquivoUtil.converterParaStreamedContent(nome, tipo, conteudo);
    }
    
    public BigDecimal calcularTamanhoMB() {
        try {
            BigDecimal tamanhoMb = criarBigDecimal((Double.valueOf(tamanho / 1024) / 1024), 2);
            return tamanhoMb;
        } catch (NullPointerException excecao) {
            return BigDecimal.ZERO;
        }
    }
    
    private void converterAtributosMaiusculo() {
        nome = converterMaiusculo(nome);
        tipo = converterMaiusculo(tipo);
    }
    
    // Processamentos
    public void processarId() {
        if (RegistroUtil.isNovo(id)) {
            id = 0L;
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

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        final Anexo other = (Anexo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}