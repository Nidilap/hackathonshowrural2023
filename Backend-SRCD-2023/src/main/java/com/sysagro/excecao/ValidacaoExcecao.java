package com.sysagro.excecao;

import static java.util.Objects.isNull;

/**
 *
 * @author Pedro
 */
public class ValidacaoExcecao extends Exception {

    // Variáveis
    private String titulo;
    private String detalhe;
    
    // Construtor
    public ValidacaoExcecao() {}

    public ValidacaoExcecao(String titulo) {
        super(titulo);
        this.titulo = titulo;
    }
    
    public ValidacaoExcecao(String titulo, String detalhe) {
        super(titulo);
        this.titulo = titulo;
        this.detalhe = detalhe;
    }
    
    // Geral
    public String retornarDescricaoCompleta() {
        return new StringBuilder()
            .append(isNull(titulo) ? "" : titulo)
            .append(".")
            .append(isNull(detalhe) ? "" : " ".concat(detalhe))
            .toString();
    }

    // Getters && Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }
}