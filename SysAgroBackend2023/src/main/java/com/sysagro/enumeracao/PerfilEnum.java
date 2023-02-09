/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Pedro
 */
public enum PerfilEnum {
    ADMIN("ADMINISTRADOR"),
    COOP("COOPERADO"), // Aviário
    EXTEN("EXTENSIONISTA"),
    COOP_F("FUNCIONÁRIO COOPERADO"),
    GENER("GENÉRICO"), // Quando o Perfil é criado pelo Usuário
    MOTOR("MOTORISTA"),
    PRESI("PRESIDENTE");

    // Variáveis
    private final String nome;

    // Construtor
    private PerfilEnum(String nome) {
        this.nome = nome;
    }
    
    // Geral
    public static List<PerfilEnum> listarPermitidosVinculoAviarios() {
        return new ArrayList<>(Arrays.asList(COOP, COOP_F));
    }

    // Getters
    public String getNome() {
        return nome;
    }
}