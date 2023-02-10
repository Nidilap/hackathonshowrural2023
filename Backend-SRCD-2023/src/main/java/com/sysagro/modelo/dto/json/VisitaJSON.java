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
public class VisitaJSON implements Serializable {

    private static final long serialVersionUID = 92489489348938954L;

    // Variáveis
    private Long idVisita;
    private Long idFuncionario;
    private Long idPessoa;
    private Long idEndereco;
    private ZonedDateTime dataAgendada;
    private String nomePessoa;
    private String observacao;
    private LocalizacaoJSON checkIn;
    private LocalizacaoJSON checkOut;
    
    // Construtor
    public VisitaJSON() {
    }
    
    public VisitaJSON(Long idVisita, Long idFuncionario, Long idPessoa, Long idEndereco, ZonedDateTime dataAgendada,
            String nomePessoa, String observacao, LocalizacaoJSON checkIn, LocalizacaoJSON checkOut) {
        this.idVisita = idVisita;
        this.idFuncionario = idFuncionario;
        this.idPessoa = idPessoa;
        this.idEndereco = idEndereco;
        this.dataAgendada = dataAgendada;
        this.nomePessoa = nomePessoa;
        this.observacao = observacao;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
    
    // Getters && Setters
    public Long getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(Long idVisita) {
        this.idVisita = idVisita;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public ZonedDateTime getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(ZonedDateTime dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalizacaoJSON getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalizacaoJSON checkIn) {
        this.checkIn = checkIn;
    }

    public LocalizacaoJSON getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalizacaoJSON checkOut) {
        this.checkOut = checkOut;
    }
}
