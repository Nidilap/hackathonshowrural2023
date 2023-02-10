/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.modelo.dto.json;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Pedro
 */
public class EnderecoJSON implements Serializable {

    private static final long serialVersionUID = 92489489348938954L;

    // Variáveis
    private Long idEndereco;
    private Long idCidade;
    private boolean isAtivo;
    private boolean isPrincipal;
    private Integer numero;
    private String endereco;
    private String bairro;
    private String complemento;
    private String cep;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String car;
    private String observacao;
    private BigDecimal areaHa;
    private LocalizacaoJSON localizacao;
    
    // Construtor
    public EnderecoJSON() {
    }
    
    public EnderecoJSON(Long idEndereco, Long idCidade, boolean isAtivo, boolean isPrincipal, Integer numero, String endereco, String bairro, String complemento,
            String cep, String inscricaoEstadual, String inscricaoMunicipal, String car, String observacao, BigDecimal areaHa, LocalizacaoJSON localizacao) {
        this.idEndereco = idEndereco;
        this.idCidade = idCidade;
        this.isAtivo = isAtivo;
        this.isPrincipal = isPrincipal;
        this.numero = numero;
        this.endereco = endereco;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cep = cep;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.car = car;
        this.observacao = observacao;
        this.areaHa = areaHa;
        this.localizacao = localizacao;
    }
    
    // Getters && Setters
    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
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

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getAreaHa() {
        return areaHa;
    }

    public void setAreaHa(BigDecimal areaHa) {
        this.areaHa = areaHa;
    }

    public LocalizacaoJSON getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(LocalizacaoJSON localizacao) {
        this.localizacao = localizacao;
    }
}
