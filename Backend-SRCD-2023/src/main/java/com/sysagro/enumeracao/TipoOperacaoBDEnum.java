/* @Pedro
   -----
   Tipos de operações no Banco de Dados
*/
package com.sysagro.enumeracao;

/**
 * @author Pedro
 */
public enum TipoOperacaoBDEnum {
    INS(0, "Insert"),
    UPD(1, "Update"),
    DEL(2, "Delete");
    
    // Variáveis
    private final Integer operacao;
    private final String observacao;

    // Construtor
    private TipoOperacaoBDEnum(Integer operacao, String observacao) {
        this.operacao = operacao;
        this.observacao = observacao;
    }
    
    // Getters
    public Integer getOperacao() {
        return operacao;
    }

    public String getObservacao() {
        return observacao;
    }
}
