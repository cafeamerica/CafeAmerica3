/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.cadastros;

/**
 *
 * @author Rafael
 */
public class Usuarios {
    private int id_usuario;
    private String usuario;
    private String senha;
    private String nome_usuario;
    private String dpto_usuario;
    private String super_senha;
    private int status_usuario;
    private int tipo_usuario;
    
    public Usuarios(){
    
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getDpto_usuario() {
        return dpto_usuario;
    }

    public void setDpto_usuario(String dpto_usuario) {
        this.dpto_usuario = dpto_usuario;
    }

    public String getSuper_senha() {
        return super_senha;
    }

    public void setSuper_senha(String super_senha) {
        this.super_senha = super_senha;
    }

    public int getStatus_usuario() {
        return status_usuario;
    }

    public void setStatus_usuario(int status_usuario) {
        this.status_usuario = status_usuario;
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    
    
}
