/*
 */
package com.sysagro.modelo.servico;

import com.sysagro.modelo.entidade.Localizacao;
import com.sysagro.util.LocalizacaoUtil;
import java.io.Serializable;
import javax.inject.Named;
import javax.script.ScriptException;
import org.locationtech.jts.geom.Coordinate;

/**
 *
 * @author Pedro
 */
@Named
public class LocalizacaoServico implements Serializable {

    private static final long serialVersionUID = 818218928918921L;

    // Criação
    public Localizacao criarCAR(String latitudeEmStr, String longitudeEmStr) throws ScriptException {
        double latitudeEmGrauDecimal = LocalizacaoUtil.transformarLatitudeEmGrauDecimal(latitudeEmStr);
        double longitudeEmGrauDecimal = LocalizacaoUtil.transformarLongitudeEmGrauDecimal(longitudeEmStr);
        Localizacao localizacao = new Localizacao();
        localizacao.setCoordenada(new Coordinate(latitudeEmGrauDecimal, longitudeEmGrauDecimal, 0d));
        return localizacao;
    }
}