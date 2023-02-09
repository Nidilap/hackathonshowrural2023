/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

/**
 * @author Pedro
 */
public enum TipoConteudoEnum {
    PDF("application/pdf", "pdf"),
    RTF("application/rtf", "rtf");

    // Variáveis
    private final String nome;
    private final String extensao;

    // Construtor
    private TipoConteudoEnum(String nome, String extensao) {
        this.nome = nome;
        this.extensao = extensao;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getExtensao() {
        return extensao;
    }
}
