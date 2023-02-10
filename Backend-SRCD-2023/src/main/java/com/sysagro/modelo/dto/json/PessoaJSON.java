/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.modelo.dto.json;

import com.sysagro.enumeracao.EstadoCivilEnum;
import com.sysagro.enumeracao.TipoPessoaEnum;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class PessoaJSON implements Serializable {

    private static final long serialVersionUID = 98124899812489128L;

    // Variáveis
    private Long idPessoa;
    private boolean isAtivo;
    private boolean isProdutor;
    private boolean isFornecedor;
    private boolean isFuncionario;
    private String razaoSocial;
    private String nomeFantasia;
    private String nomePai;
    private String nomeMae;
    private String cpfCnpj;
    private String rg;
    private String telefone;
    private String celular;
    private String email;
    private String atividade;
    private String observacao;
    private ZonedDateTime dataHoraNascimento;
    private EstadoCivilEnum estadoCivilEnum;
    private TipoPessoaEnum tipoPessoaEnum;
    private AnexoJSON foto;
    private List<EnderecoJSON> enderecos;
    
    // Construtor
    public PessoaJSON() {
    }
    
    public PessoaJSON(Long idPessoa, boolean isAtivo, boolean isProdutor, boolean isFornecedor, boolean isFuncionario, String razaoSocial, String nomeFantasia,
            String nomePai, String nomeMae, String cpfCnpj, String rg, String telefone, String celular, String email, String atividade, String observacao,
            ZonedDateTime dataHoraNascimento, EstadoCivilEnum estadoCivilEnum, TipoPessoaEnum tipoPessoaEnum, AnexoJSON foto, List<EnderecoJSON> enderecos) {
        this.idPessoa = idPessoa;
        this.isAtivo = isAtivo;
        this.isProdutor = isProdutor;
        this.isFornecedor = isFornecedor;
        this.isFuncionario = isFuncionario;
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
        this.enderecos = enderecos;
    }
    
    // Getters && Setters
    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public boolean isIsAtivo() {
        return isAtivo;
    }

    public void setIsAtivo(boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    public boolean isIsProdutor() {
        return isProdutor;
    }

    public void setIsProdutor(boolean isProdutor) {
        this.isProdutor = isProdutor;
    }

    public boolean isIsFornecedor() {
        return isFornecedor;
    }

    public void setIsFornecedor(boolean isFornecedor) {
        this.isFornecedor = isFornecedor;
    }

    public boolean isIsFuncionario() {
        return isFuncionario;
    }

    public void setIsFuncionario(boolean isFuncionario) {
        this.isFuncionario = isFuncionario;
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

    public AnexoJSON getFoto() {
        return foto;
    }

    public void setFoto(AnexoJSON foto) {
        this.foto = foto;
    }

    public List<EnderecoJSON> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoJSON> enderecos) {
        this.enderecos = enderecos;
    }
}
