/*
 */
package com.sysagro.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public final class LogUtil {

    // Construtor
    private LogUtil() {}

    // Geral
    public static void exibirAviso(Class classe, String mensagem) {
        Logger.getLogger(classe.getName()).log(Level.WARNING, mensagem);
    }
    
    public static void exibirErro(Class classe, Throwable ex) {
        Logger.getLogger(classe.getName()).log(Level.SEVERE, null, ex);
    }
    
    public static void exibirErro(Class classe, String mensagem) {
        Logger.getLogger(classe.getName()).log(Level.SEVERE, mensagem);
    }
    
    public static void exibirErro(Class classe, String mensagem, Throwable ex) {
        Logger.getLogger(classe.getName()).log(Level.SEVERE, mensagem, ex);
    }
    
    public static void exibirInfo(Class classe, String mensagem) {
        Logger.getLogger(classe.getName()).log(Level.INFO, mensagem);
    }
}