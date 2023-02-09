/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

import java.time.ZoneId;

/**
 * @author Pedro
 */
public enum FusoHorarioEnum {
    UTC(ZoneId.of("UTC"));

    // Variáveis
    private final ZoneId zoneId;

    // Construtor
    private FusoHorarioEnum(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    // Getters
    public ZoneId getZoneId() {
        return zoneId;
    }
}