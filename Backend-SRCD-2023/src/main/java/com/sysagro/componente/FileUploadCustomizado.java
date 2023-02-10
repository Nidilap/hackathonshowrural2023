/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.componente;

import com.sysagro.util.ConfiguracaoUtil;
import org.primefaces.component.fileupload.FileUpload;

/**
 *
 * @author Pedro
 */
public class FileUploadCustomizado extends FileUpload {
    
    // Construtor
    public FileUploadCustomizado() {
    }
    
    // Geral
    @Override
    public Long getSizeLimit() {
        return ConfiguracaoUtil.FILEUPLOAD_TAMANHO_MAXIMO_BYTES;
    }
}
