/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.modelo.dto.json;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 *
 * @author Pedro
 */
public class AnexoJSON implements Serializable {

    private static final long serialVersionUID = 71728578128757124L;

    // Variáveis
    private Long idAnexo;
    private Long tamanho;; // Em Bytes
    private boolean isSincronizado = true;
    private String nome;
    private String tipo;
    private String conteudoBase64;
    private ZonedDateTime dataHoraCriacao;
    
    // Construtor
    public AnexoJSON(Long idAnexo, Long tamanho, String nome, String tipo, String conteudoBase64, ZonedDateTime dataHoraCriacao) {
        this.idAnexo = idAnexo;
        this.tamanho = tamanho;
        this.nome = nome;
        this.tipo = tipo;
        this.conteudoBase64 = conteudoBase64;
        this.dataHoraCriacao = dataHoraCriacao;
    }

    // Getters && Setters
    public Long getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(Long idAnexo) {
        this.idAnexo = idAnexo;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isIsSincronizado() {
        return isSincronizado;
    }

    public void setIsSincronizado(boolean isSincronizado) {
        this.isSincronizado = isSincronizado;
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

    public String getConteudoBase64() {
        return conteudoBase64;
    }

    public void setConteudoBase64(String conteudoBase64) {
        this.conteudoBase64 = conteudoBase64;
    }

    public ZonedDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(ZonedDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }
}
