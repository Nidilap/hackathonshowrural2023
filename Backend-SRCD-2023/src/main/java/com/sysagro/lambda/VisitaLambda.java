/*
 */
package com.sysagro.lambda;

import com.sysagro.modelo.dto.json.VisitaJSON;
import com.sysagro.modelo.entidade.Visita;
import com.sysagro.modelo.fabrica.json.VisitaFabricaJSON;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author Pedro
 */
public class VisitaLambda implements Serializable {

    private static final long serialVersionUID = 9832893891891893L;
    
    @Inject
    private VisitaFabricaJSON visitaFabricaJSON;
    
    // Processamentos de listas
    public List<VisitaJSON> mapearParaVisitasJSON(List<Visita> visitas) {
        List<VisitaJSON> lista = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(visitas)) {
            visitas.forEach(visita -> lista.add(visitaFabricaJSON.criar(visita)));
        }
        return lista;
    }
    
    // Lambdas com parâmetros
}
