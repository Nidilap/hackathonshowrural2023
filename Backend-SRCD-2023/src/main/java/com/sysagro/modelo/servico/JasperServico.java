/*
 */
package com.sysagro.modelo.servico;

import com.sysagro.enumeracao.RelatorioJasperEnum;
import com.sysagro.enumeracao.TipoConteudoEnum;
import com.sysagro.modelo.fabrica.ConexaoBancoDadosFabrica;
import com.sysagro.util.ArquivoUtil;
import com.sysagro.util.ConfiguracaoUtil;
import com.sysagro.util.LogUtil;
import com.sysagro.util.ValidacaoUtil;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Pedro
 */
@Named
public class JasperServico implements Serializable {
    
    private static final long serialVersionUID = 82148712841892724L;

    @Inject
    private ConexaoBancoDadosFabrica conexaoBancoDadosFabrica;

    // PDF
    public StreamedContent gerarStreamedContentPDF(
            String nomeArquivo,
            RelatorioJasperEnum relatorioJasperEnum,
            Map<String, Object> parametros) {
        StreamedContent arquivo = null;
        try (Connection conexaoBD = conexaoBancoDadosFabrica.criarConexao()) {
            ValidacaoUtil.validarParametroObrigatorio(nomeArquivo, "nomeArquivo");
            ValidacaoUtil.validarParametroObrigatorio(relatorioJasperEnum, "relatorioJasperEnum");
            // Stream para armazenar relatório
            ByteArrayOutputStream saidaDados = new ByteArrayOutputStream();
            // Preenchimento do relatório
            JasperPrint relatorioGerado = gerarRelatorio(conexaoBD, relatorioJasperEnum, parametros);
            // Exportação para PDF
            exportarPDF(relatorioGerado, saidaDados);
            // Geração do StreamedContent
            arquivo = ArquivoUtil.converterParaStreamedContent(
                formatarNomeArquivo(nomeArquivo, TipoConteudoEnum.PDF),
                TipoConteudoEnum.PDF,
                saidaDados.toByteArray());
        } catch (Exception ex) {
            LogUtil.exibirErro(JasperServico.class, "\"gerarStreamedContentPDF\"", ex);
        }
        return arquivo;
    }
    
    // Utilitários
    public Map<String, Object> criarParametrosPadroes(String tituloRelatorio) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_RELATORIO", ArquivoUtil.retornarInputStreamSource(getClass(), ConfiguracaoUtil.CAMINHO_LOGO_SISTEMA_COM_TEXTO));
        parametros.put("TITULO_RELATORIO", tituloRelatorio);
        return parametros;
    }
    
    private JasperPrint gerarRelatorio(Connection conexaoBD, RelatorioJasperEnum relatorioJasperEnum, Map<String, Object> parametros) throws JRException {
        return JasperFillManager.fillReport(
            ArquivoUtil.retornarInputStreamSource(this.getClass(), relatorioJasperEnum.getCaminhoJasper()),
            parametros,
            conexaoBD);
    }
    
    private void exportarPDF(JasperPrint relatorioGerado, ByteArrayOutputStream saidaDados) throws JRException {
        Exporter exportador = new JRPdfExporter();
        exportador.setExporterInput(new SimpleExporterInput(relatorioGerado));
        exportador.setExporterOutput(new SimpleOutputStreamExporterOutput(saidaDados));
        exportador.exportReport();
    }
    
    private String formatarNomeArquivo(String nomeArquivo, TipoConteudoEnum tipoConteudoEnum) {
        return String.format("%s.%s", nomeArquivo, tipoConteudoEnum.getExtensao());
    }
}