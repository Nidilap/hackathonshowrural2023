/*
 */
package com.sysagro.util;

import com.sysagro.enumeracao.TipoConteudoEnum;
import static com.sysagro.util.LogUtil.exibirErro;
import io.jsonwebtoken.security.Keys;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import static java.util.Objects.isNull;
import javax.crypto.SecretKey;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Pedro
 */
public final class ArquivoUtil {

    // Construtor
    private ArquivoUtil() {}

    // Geral
    public static byte[] converterParaBytes(String conteudoBase64) {
        if (isNull(conteudoBase64)) {
            return null;
        } else {
            return Base64.getDecoder().decode(conteudoBase64);
        }
    }
    
    public static String converterParaBase64(byte[] conteudo) {
        if (isNull(conteudo)) {
            return null;
        } else {
            return Base64.getEncoder().encodeToString(conteudo);
        }
    }
    
    public static InputStream converterParaInputStream(String base64) {
        if (isNull(base64)) {
            return null;
        }
        byte[] imageBytes = Base64.getDecoder().decode(base64);
        return new ByteArrayInputStream(imageBytes);
    }
    
    public static StreamedContent converterParaStreamedContent(String nome, String tipo, byte[] conteudo) {
        if (isNull(conteudo)) {
            return null;
        } else {
            return DefaultStreamedContent.builder()
                .name(nome)
                .contentType(tipo)
                .stream(() -> new ByteArrayInputStream(conteudo))
                .build();
        }
    }
    
    public static StreamedContent converterParaStreamedContent(String nome, TipoConteudoEnum tipoConteudoEnum, byte[] conteudo) {
        if (isNull(conteudo)) {
            return null;
        } else {
            return DefaultStreamedContent.builder()
                .name(nome)
                .contentType(tipoConteudoEnum.getNome())
                .stream(() -> new ByteArrayInputStream(conteudo))
                .build();
        }
    }
    
    public static String converterParaConteudo(String caminho) {
        try {
            File file = FileUtils.toFile(new URL(caminho));
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8.toString());
        } catch (IOException ex) {
            exibirErro(ArquivoUtil.class, ex);
            return null;
        }
    }

    public static String converterParaConteudo(InputStream dadosEntrada) {
        try {
            return IOUtils.toString(dadosEntrada, StandardCharsets.UTF_8.toString());
        } catch (IOException ex) {
            exibirErro(ArquivoUtil.class, ex);
            return null;
        }
    }
    
    public static String retornarCaminhoSource(Class classe, String caminho) {
        return classe.getClassLoader().getResource(caminho).getPath();
    }
    
    public static InputStream retornarInputStreamSource(Class classe, String caminho) {
        return classe.getClassLoader().getResourceAsStream(caminho);
    }
    
    public static SecretKey retornarChaveSecretaJWT() {
        String chaveJWT = converterParaConteudo(retornarInputStreamSource(ArquivoUtil.class, ConfiguracaoUtil.CAMINHO_ASSINATURA_JWT));
        return Keys.hmacShaKeyFor(chaveJWT.getBytes(StandardCharsets.UTF_8));
    }
}