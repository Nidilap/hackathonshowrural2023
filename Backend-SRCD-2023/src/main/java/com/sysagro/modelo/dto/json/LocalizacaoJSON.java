/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.modelo.dto.json;

import java.io.Serializable;
import java.time.ZonedDateTime;
import org.locationtech.jts.geom.Coordinate;

/**
 *
 * @author Pedro
 */
public class LocalizacaoJSON implements Serializable {

    private static final long serialVersionUID = 92489489348938954L;

    // Variáveis
    private Long idLocalizacao;
    private ZonedDateTime dataHoraCriacao;
    private Coordinate coordenada;
    
    // Construtor
    public LocalizacaoJSON() {
    }

    public LocalizacaoJSON(Long idLocalizacao, ZonedDateTime dataHoraCriacao, Coordinate coordenada) {
        this.idLocalizacao = idLocalizacao;
        this.dataHoraCriacao = dataHoraCriacao;
        this.coordenada = coordenada;
    }

    // Getters && Setters
    public Long getIdLocalizacao() {
        return idLocalizacao;
    }

    public void setIdLocalizacao(Long idLocalizacao) {
        this.idLocalizacao = idLocalizacao;
    }

    public ZonedDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(ZonedDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Coordinate getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordinate coordenada) {
        this.coordenada = coordenada;
    }
}
