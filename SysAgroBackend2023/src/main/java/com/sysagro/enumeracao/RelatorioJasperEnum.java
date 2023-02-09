/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

/**
 * @author Pedro
 */
public enum RelatorioJasperEnum {
    CADERNO_REGISTRO_LOTE_AVE(
        "Caderno de Registro do Lote de Ave",
        "jasper/relatorio/lote/ave/caderno_registro_lote_ave.jasper"),
    RELATORIO_LOTE_AVE(
        "Relatório de Lote de Ave",
        "jasper/relatorio/lote/ave/relatorio_lote_ave.jasper"),
    RELATORIO_MANEJO_PRE_ABATE_AVE(
        "Relatório de Manejo Pré-abate de Ave",
        "jasper/relatorio/manejo/preAbate/ave/relatorio_manejo_pre_abate_ave.jasper");

    // Variáveis
    private final String nome;
    private final String caminhoJasper;

    // Construtor
    private RelatorioJasperEnum(String nome, String caminhoJasper) {
        this.nome = nome;
        this.caminhoJasper = caminhoJasper;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getCaminhoJasper() {
        return caminhoJasper;
    }
}
