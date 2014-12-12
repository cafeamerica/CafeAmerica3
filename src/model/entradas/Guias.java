/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entradas;

import java.sql.Date;

/**
 *
 * @author Rafael
 */
public class Guias {

    private int id_guia;
    private String num_guia;
    private String nosso_num_guia;
    private java.sql.Date dt_entrada_guia;
    private double vlr_unit_guia;
    private double vlr_total_guia;
    private double peso_guia;
    private double qtd_sacas_guia;
    private int referencia_guia;
    private int status_guia;
    private String str_status_guia;
    private String str_usuario_cad_guia;
    private String usuario_cad_guia;
    private String str_emp;
    private double vlr_total_ini_guia; //valor total inicial
    private String obs;
    
    public Guias() {

    }

    public String getStr_status_guia() {
        return str_status_guia;
    }

    public void setStr_status_guia(String str_status_guia) {
        this.str_status_guia = str_status_guia;
    }

    public String getStr_usuario_cad_guia() {
        return str_usuario_cad_guia;
    }

    public void setStr_usuario_cad_guia(String str_usuario_cad_guia) {
        this.str_usuario_cad_guia = str_usuario_cad_guia;
    }
    

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    

    public double getVlr_total_ini_guia() {
        return vlr_total_ini_guia;
    }

    public void setVlr_total_ini_guia(double vlr_total_ini_guia) {
        this.vlr_total_ini_guia = vlr_total_ini_guia;
    }

    
    public String getStr_emp() {
        return str_emp;
    }

    public void setStr_emp(String str_emp) {
        this.str_emp = str_emp;
    }

    public int getId_guia() {
        return id_guia;
    }

    public void setId_guia(int id_guia) {
        this.id_guia = id_guia;
    }

    public String getNum_guia() {
        return num_guia;
    }

    public void setNum_guia(String num_guia) {
        this.num_guia = num_guia;
    }

    public String getNosso_num_guia() {
        return nosso_num_guia;
    }

    public void setNosso_num_guia(String nosso_num_guia) {
        this.nosso_num_guia = nosso_num_guia;
    }

    public Date getDt_entrada_guia() {
        return dt_entrada_guia;
    }

    public void setDt_entrada_guia(Date dt_entrada_guia) {
        this.dt_entrada_guia = dt_entrada_guia;
    }

    public double getVlr_unit_guia() {
        return vlr_unit_guia;
    }

    public void setVlr_unit_guia(double vlr_unit_guia) {
        this.vlr_unit_guia = vlr_unit_guia;
    }

    public double getVlr_total_guia() {
        return vlr_total_guia;
    }

    public void setVlr_total_guia(double vlr_total_guia) {
        this.vlr_total_guia = vlr_total_guia;
    }

    public double getPeso_guia() {
        return peso_guia;
    }

    public void setPeso_guia(double peso_guia) {
        this.peso_guia = peso_guia;
    }

    public double getQtd_sacas_guia() {
        return qtd_sacas_guia;
    }

    public void setQtd_sacas_guia(double qtd_sacas_guia) {
        this.qtd_sacas_guia = qtd_sacas_guia;
    }

    public int getReferencia_guia() {
        return referencia_guia;
    }

    public void setReferencia_guia(int referencia_guia) {
        this.referencia_guia = referencia_guia;
    }

    public int getStatus_guia() {
        return status_guia;
    }

    public void setStatus_guia(int status_guia) {
        this.status_guia = status_guia;
    }

    public String getUsuario_cad_guia() {
        return usuario_cad_guia;
    }

    public void setUsuario_cad_guia(String usuario_cad_guia) {
        this.usuario_cad_guia = usuario_cad_guia;
    }

}
