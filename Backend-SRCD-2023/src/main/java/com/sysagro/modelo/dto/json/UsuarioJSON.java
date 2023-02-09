/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.modelo.dto.json;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 *
 * @author Pedro
 */
public class UsuarioJSON implements Serializable {

    private static final long serialVersionUID = 8521858921895L;

    // Variáveis
    private Long idUsuario;
    private Long idFoto;
    private boolean isAtivo;
    private boolean isSincronizado = true;
    private String login;
    private String nome;
    private String email;
    private String senha;
    private ZonedDateTime dataHoraCriacao;
    
    // Objetos
    private List<PerfilJSON> perfis;
    
    // Construtor
    public UsuarioJSON(Long idUsuario, Long idFoto, boolean isAtivo, String login, String nome, String email, ZonedDateTime dataHoraCriacao) {
        this.idUsuario = idUsuario;
        this.idFoto = idFoto;
        this.isAtivo = isAtivo;
        this.login = login;
        this.nome = nome;
        this.email = email;
        this.dataHoraCriacao = dataHoraCriacao;
    }
    
    public UsuarioJSON(Long idUsuario, Long idFoto, boolean isAtivo, String login, String nome, String email,
            String senha, ZonedDateTime dataHoraCriacao) {
        this.idUsuario = idUsuario;
        this.idFoto = idFoto;
        this.isAtivo = isAtivo;
        this.login = login;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataHoraCriacao = dataHoraCriacao;
    }
    
    // Geral
    public void adicionarPerfil(PerfilJSON perfilJSON) {
        validarPerfis();
        perfis.add(perfilJSON);
    }
    
    public void adicionarPerfis(List<PerfilJSON> perfisJSON) {
        if (nonNull(perfisJSON)) {
            perfisJSON.forEach(perfilJSON -> adicionarPerfil(perfilJSON));
        }
    }
    
    // Processamentos de listas
    private void validarPerfis() {
        if (isNull(perfis)) {
            limparPerfis();
        }
    }
    
    private void limparPerfis() {
        perfis = new ArrayList<>();
    }

    // Getters && Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Long idFoto) {
        this.idFoto = idFoto;
    }

    public boolean isIsAtivo() {
        return isAtivo;
    }

    public void setIsAtivo(boolean isAtivo) {
        this.isAtivo = isAtivo;
    }
    
    public boolean isIsSincronizado() {
        return isSincronizado;
    }

    public void setIsSincronizado(boolean isSincronizado) {
        this.isSincronizado = isSincronizado;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ZonedDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(ZonedDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }
    
    public List<PerfilJSON> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<PerfilJSON> perfis) {
        this.perfis = perfis;
    }
}
