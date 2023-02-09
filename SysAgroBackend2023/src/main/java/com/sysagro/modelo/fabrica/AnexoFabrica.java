package com.sysagro.modelo.fabrica;

import com.sysagro.modelo.dto.json.AnexoJSON;
import com.sysagro.modelo.entidade.Anexo;
import static com.sysagro.util.ArquivoUtil.converterParaBytes;
import com.sysagro.util.LogUtil;
import java.io.Serializable;
import javax.inject.Named;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Pedro
 */
@Named
public class AnexoFabrica implements Serializable {

    private static final long serialVersionUID = 924812478127848721L;

    // Geral
    public Anexo criar(UploadedFile arquivo) {
        try {
            Anexo anexo = new Anexo(arquivo.getSize(), arquivo.getContent(), arquivo.getFileName(), arquivo.getContentType());
            return anexo;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public Anexo criar(AnexoJSON anexoJSON) {
        try {
            Anexo anexo = new Anexo(
                anexoJSON.getTamanho(),
                converterParaBytes(anexoJSON.getConteudoBase64()),
                anexoJSON.getNome(),
                anexoJSON.getTipo());
            return anexo;
        } catch (NullPointerException excecao) {
            LogUtil.exibirErro(getClass(), excecao);
            return null;
        }
    }
}
