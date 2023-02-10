/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.modelo.dto.json;

import com.sysagro.enumeracao.PerfilEnum;
import java.io.Serializable;

/**
 *
 * @author Pedro
 */
public class PerfilJSON implements Serializable {

    private static final long serialVersionUID = 8521858921895L;

    // Variáveis
    private Long idPerfil;
    private String nome;
    private String sigla;
    private String observacao;
    private PerfilEnum perfilEnum;
    
    // Construtor
    public PerfilJSON(Long idPerfil, String nome, String sigla, String observacao, PerfilEnum perfilEnum) {
        this.idPerfil = idPerfil;
        this.nome = nome;
        this.sigla = sigla;
        this.observacao = observacao;
        this.perfilEnum = perfilEnum;
    }
    
    // Getters && Setters
    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public PerfilEnum getPerfilEnum() {
        return perfilEnum;
    }

    public void setPerfilEnum(PerfilEnum perfilEnum) {
        this.perfilEnum = perfilEnum;
    }
}