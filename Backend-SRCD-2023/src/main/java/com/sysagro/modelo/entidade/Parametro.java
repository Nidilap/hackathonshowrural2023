package com.sysagro.modelo.entidade;

import com.sysagro.enumeracao.ParametroEnum;
import static com.sysagro.util.TextoUtil.converterMaiusculo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 * 
 *  - A Tela de Parâmetros do Sistema será uma listagem baseada
 *    nos enums do "ParametroEnum"
 */
@Audited
@Entity
@Table(name = "parametro")
public class Parametro extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 854386943643694396L;

    // Deve ter o @Id
    // Mais pra frente, caso tiver que parametrizar por "Filial", aí a coluna
    // de parâmetro não poderá ser a "PRIMARY KEY"
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_parametro")
    @SequenceGenerator(name = "seq_parametro", sequenceName = "seq_parametro", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;
    
    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;

    @Column(name = "is_registro_lista")
	private boolean isRegistroLista;
    
    @Column(name = "valor") // Valor do parâmetro (booleano, numérico, texto)
    private String valor;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;
    
    @Column(name = "parametro", nullable = false)
    @Enumerated(EnumType.STRING)
    private ParametroEnum parametroEnum;
    
    @JoinColumn(name = "id_empresa", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_parametro_empresa")) // Filial
	@ManyToOne(fetch = FetchType.LAZY)
	private Empresa empresa;
    
    @JoinColumn(name = "id_parametro", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_parametro_parametro")) // Parâmetro
	@ManyToOne(fetch = FetchType.LAZY)
	private Parametro parametro;
    
    // Transient
    @Transient
    private String valorTR;
    
    @Transient
    private List<Parametro> registrosTR;
    
    @Transient
    private List<Parametro> registrosParaExcluirTR;

    // Construtor
    public Parametro() {
    }

    public Parametro(ParametroEnum parametroEnum, String valor, String observacao, Empresa empresa) {
        this.parametroEnum = parametroEnum;
        this.valor = valor;
        this.observacao = observacao;
        this.empresa = empresa;
    }

    public Parametro(boolean isRegistroLista, String valor, String observacao, ParametroEnum parametroEnum, Empresa empresa, Parametro parametro) {
        this.isRegistroLista = isRegistroLista;
        this.valor = valor;
        this.observacao = observacao;
        this.parametroEnum = parametroEnum;
        this.empresa = empresa;
        this.parametro = parametro;
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
    private void converterAtributosMaiusculo() {
        observacao = converterMaiusculo(observacao);
    }
    
    public void adicionarRegistro(Parametro registro) {
        validarRegistrosTR();
        if (Objects.nonNull(registro)) {
            registrosTR.add(registro);
        }
    }
    
    public void adicionarRegistroParaExcluir(int index) {
        validarRegistrosTR();
        validarRegistrosParaExcluirTR();
        registrosParaExcluirTR.add(registrosTR.get(index));
        registrosTR.remove(index);
    }
    
    // Processamentos de listas
    public void validarRegistrosTR() {
        if (CollectionUtils.isEmpty(registrosTR)) {
            registrosTR = new ArrayList<>();
        }
    }

    public void validarRegistrosParaExcluirTR() {
        if (CollectionUtils.isEmpty(registrosParaExcluirTR)) {
            registrosParaExcluirTR = new ArrayList<>();
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
    
    public boolean isIsRegistroLista() {
        return isRegistroLista;
    }

    public void setIsRegistroLista(boolean isRegistroLista) {
        this.isRegistroLista = isRegistroLista;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public ParametroEnum getParametroEnum() {
        return parametroEnum;
    }

    public void setParametroEnum(ParametroEnum parametroEnum) {
        this.parametroEnum = parametroEnum;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Parametro getParametro() {
        return parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }

    public String getValorTR() {
        return valorTR;
    }

    public void setValorTR(String valorTR) {
        this.valorTR = valorTR;
    }

    public List<Parametro> getRegistrosTR() {
        return registrosTR;
    }

    public void setRegistrosTR(List<Parametro> registrosTR) {
        this.registrosTR = registrosTR;
    }

    public List<Parametro> getRegistrosParaExcluirTR() {
        return registrosParaExcluirTR;
    }

    public void setRegistrosParaExcluirTR(List<Parametro> registrosParaExcluirTR) {
        this.registrosParaExcluirTR = registrosParaExcluirTR;
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
        final Parametro other = (Parametro) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
