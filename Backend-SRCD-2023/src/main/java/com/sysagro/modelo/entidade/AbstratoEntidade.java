/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.modelo.entidade;

import com.sysagro.enumeracao.TipoOperacaoBDEnum;
import com.sysagro.util.SessaoUtil;
import com.sysagro.util.WebUtil;
import java.time.ZonedDateTime;
import java.util.Objects;
import static java.util.Objects.isNull;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import org.hibernate.envers.Audited;

/**
 *
 * @author Pedro
 */
@MappedSuperclass
public abstract class AbstratoEntidade {

    // Colunas
    @Audited
    @Column(name = "data_hora_criacao", updatable = false)
    private ZonedDateTime dataHoraCriacao;

    @Audited
    @Column(name = "data_hora_atualizacao")
    private ZonedDateTime dataHoraAtualizacao;

    @Audited
    @Column(name = "usuario_criacao", updatable = false) // Id usuário criação
    private Long usuarioCriacao;

    @Audited
    @Column(name = "usuario_atualizacao") // Id usuário atualização
    private Long usuarioAtualizacao;

    // Transients
    @Transient
    private TipoOperacaoBDEnum tipoOperacaoBDTR = TipoOperacaoBDEnum.INS;

    // Listeners
    @PrePersist
    protected void prePersist() {
		if (Objects.isNull(dataHoraCriacao)) {
            dataHoraCriacao = ZonedDateTime.now();
		}
        if (Objects.isNull(usuarioCriacao)) {
            usuarioCriacao = retornarIdUsuario();
        }
    }

    @PreUpdate
    protected void preUpdate() {
        dataHoraAtualizacao = ZonedDateTime.now();
        usuarioAtualizacao = retornarIdUsuario();
    }

    // Geral
    public boolean isNovo() {
        return isNull(dataHoraCriacao);
    }

    private Long retornarIdUsuario() {
        Usuario usuario = (Usuario) WebUtil.converterParaObjeto(SessaoUtil.USUARIO);
        return isNull(usuario) ? 0 : usuario.getId();
    }

    // Getters && Setters
    public ZonedDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(ZonedDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public ZonedDateTime getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(ZonedDateTime dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }
    
    public Long getUsuarioCriacao() {
        return usuarioCriacao;
    }

    public void setUsuarioCriacao(Long usuarioCriacao) {
        this.usuarioCriacao = usuarioCriacao;
    }

    public Long getUsuarioAtualizacao() {
        return usuarioAtualizacao;
    }

    public void setUsuarioAtualizacao(Long usuarioAtualizacao) {
        this.usuarioAtualizacao = usuarioAtualizacao;
    }

    public TipoOperacaoBDEnum getTipoOperacaoBDTR() {
        return tipoOperacaoBDTR;
    }

    public void setTipoOperacaoBDTR(TipoOperacaoBDEnum tipoOperacaoBDTR) {
        this.tipoOperacaoBDTR = tipoOperacaoBDTR;
    }
}