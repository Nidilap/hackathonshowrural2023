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
public enum TelaEnum {
    // Administração
    ADMINISTRACAO_PARAMETRO(
        "Administração/Parâmetro",
        "tela.administracao.parametro",
        ModuloTelaEnum.ADMINISTRACAO),
    ADMINISTRACAO_PERFIL(
        "Administração/Perfil",
        "tela.administracao.perfil",
        ModuloTelaEnum.ADMINISTRACAO),
    ADMINISTRACAO_USUARIO(
        "Administração/Usuário",
        "tela.administracao.usuario",
        ModuloTelaEnum.ADMINISTRACAO),
    // Cadastros
    CADASTRO_AVIARIO(
        "Cadastros/Aviário",
        "tela.cadastro.aviario",
        ModuloTelaEnum.CADASTRO),
    CADASTRO_CIDADE(
        "Cadastros/Cidade",
        "tela.cadastro.cidade",
        ModuloTelaEnum.CADASTRO),
    CADASTRO_ESTADO(
        "Cadastros/Estado",
        "tela.cadastro.estado",
        ModuloTelaEnum.CADASTRO),
    CADASTRO_PAIS(
        "Cadastros/País",
        "tela.cadastro.pais",
        ModuloTelaEnum.CADASTRO),
    // Lotes
    LOTE_AVE(
        "Lotes/Ave",
        "tela.lote.ave",
        ModuloTelaEnum.LOTE),
    // Manejos Pré-abates
    MANEJO_PRE_ABATE_AVE(
        "Manejos Pré-abates/Ave",
        "tela.manejo.preAbate.ave",
        ModuloTelaEnum.MANEJO_PRE_ABATE),
    // Relatórios
    RELATORIO_LOTE_AVE(
        "Relatórios/Lote de ave",
        "tela.relatorio.lote.ave",
        ModuloTelaEnum.RELATORIO),
    RELATORIO_MANEJO_PRE_ABATE_AVE(
        "Relatórios/Manejo pré-abate de ave",
        "tela.relatorio.manejo.preAbate.ave",
        ModuloTelaEnum.RELATORIO);

    // Variáveis
    private final String nome;
    private final String nomeI18N;
    private final ModuloTelaEnum moduloTelaEnum;

    // Construtor
    private TelaEnum(String nome, String nomeI18N, ModuloTelaEnum moduloTelaEnum) {
        this.nome = nome;
        this.nomeI18N = nomeI18N;
        this.moduloTelaEnum = moduloTelaEnum;
    }
    
    // Geral
    public static List<TelaEnum> listar() {
        return new ArrayList<>(Arrays.asList(TelaEnum.values()));
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getNomeI18N() {
        return nomeI18N;
    }

    public ModuloTelaEnum getModuloTelaEnum() {
        return moduloTelaEnum;
    }
}
