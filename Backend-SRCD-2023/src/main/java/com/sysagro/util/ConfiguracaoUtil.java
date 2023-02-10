/*
 */
package com.sysagro.util;

/**
 *
 * @author Pedro
 */
public final class ConfiguracaoUtil {

    // Arquivo
    public static final Long FILEUPLOAD_TAMANHO_MAXIMO_BYTES = 100000000L; // 100 MB
    
    // AutoComplete
    public static final Integer AUTOCOMPLETE_LIMITE_RESULTADOS = 20;
    
    // Banco de dados
    public static final String COLUNA_COM_TIME_ZONE = "TIMESTAMP WITH TIME ZONE";
    
    // Caminhos
    public static final String CAMINHO_ASSINATURA_JWT = "chaves/assinaturaJWT.txt"; // a partir de "resources"
    public static final String CAMINHO_LOGO_SISTEMA_COM_TEXTO = "imagem/logo/branca/logoComTexto.png"; // a partir de "resources"
    
    // Cabecalho HTTP
    public static final String CABECALHO_HTTP_ID_USUARIO = "IdUsuario";
    
    // Formatos/Padrões
    public static final String FORMATO_HORA = "HH:mm";
    public static final String FORMATO_DATA = "yyyy-MM-dd";
    public static final String FORMATO_DATA_HORA = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATO_DATA_HORA_COM_FUSO_HORARIO = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    
    // Servidor
    public static final String URL_SERVIDOR = "localhost:8080";
    
    // Textos
    public static final String AUTOCOMPLETE_NAVEGADOR = "off";
    
    // Construtor
    private ConfiguracaoUtil() {}
}