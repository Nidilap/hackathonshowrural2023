/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

/**
 * @author Pedro
 */
public enum EspecieAnimalEnum {
    AVE("Ave", "Galinhas, Frangos, Patos e Gansos"),
    BOVINO("Bovino", "Bois e Vacas"),
    BUFALINO("Bufalino", "Búfalos"),
    CAPRINO("Caprino", "Cabras e Bodes"),
    EQUINO("Equino", "Cavalos"),
    OVINO("Ovino", "Ovelhas"),
    PEIXE("Peixe", "Peixes e demais animais aquáticos"),
    SUINO("Suíno", "Porcos");

    // Variáveis
    private final String nome;
    private final String animal;

    // Construtor
    private EspecieAnimalEnum(String nome, String animal) {
        this.nome = nome;
        this.animal = animal;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getAnimal() {
        return animal;
    }
}
