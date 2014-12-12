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
public class ViagemEnt {
    private int id_viagem_ent_guia;
    private String doc_viagem_ent;
    private java.sql.Date dt_ent;
    private String tipo_cafe_ent;
    private double qtd_sacas_ent;
    private double peso_ent;
    private String nome_motor_ent;
    private int tipo_frete_ent;
    private double vlr_frete_unit;
    private double vlr_frete_total;
    private String usuario_cad_ent;
    private String placa_cam_ent;
    private String num_nota;
    private double vol_fiscal;
    private double peso_fiscal;
    private String num_guia;
    private String obs_ent;
    
    public ViagemEnt(){
    
    }

    public String getObs_ent() {
        return obs_ent;
    }

    public void setObs_ent(String obs_ent) {
        this.obs_ent = obs_ent;
    }
    
    

    public String getNum_guia() {
        return num_guia;
    }

    public void setNum_guia(String num_guia) {
        this.num_guia = num_guia;
    }
    
    

    public double getVol_fiscal() {
        return vol_fiscal;
    }

    public void setVol_fiscal(double vol_fiscal) {
        this.vol_fiscal = vol_fiscal;
    }

    public double getPeso_fiscal() {
        return peso_fiscal;
    }

    public void setPeso_fiscal(double peso_fiscal) {
        this.peso_fiscal = peso_fiscal;
    }
    
    

    public String getNum_nota() {
        return num_nota;
    }

    public void setNum_nota(String num_nota) {
        this.num_nota = num_nota;
    }
    
    

    public String getPlaca_cam_ent() {
        return placa_cam_ent;
    }

    public void setPlaca_cam_ent(String placa_cam_ent) {
        this.placa_cam_ent = placa_cam_ent;
    }
    
    

    public int getId_viagem_ent_guia() {
        return id_viagem_ent_guia;
    }

    public void setId_viagem_ent_guia(int id_viagem_ent_guia) {
        this.id_viagem_ent_guia = id_viagem_ent_guia;
    }

    public String getDoc_viagem_ent() {
        return doc_viagem_ent;
    }

    public void setDoc_viagem_ent(String doc_viagem_ent) {
        this.doc_viagem_ent = doc_viagem_ent;
    }

    public Date getDt_ent() {
        return dt_ent;
    }

    public void setDt_ent(Date dt_ent) {
        this.dt_ent = dt_ent;
    }

    public String getTipo_cafe_ent() {
        return tipo_cafe_ent;
    }

    public void setTipo_cafe_ent(String tipo_cafe_ent) {
        this.tipo_cafe_ent = tipo_cafe_ent;
    }

    public double getQtd_sacas_ent() {
        return qtd_sacas_ent;
    }

    public void setQtd_sacas_ent(double qtd_sacas_ent) {
        this.qtd_sacas_ent = qtd_sacas_ent;
    }

    public double getPeso_ent() {
        return peso_ent;
    }

    public void setPeso_ent(double peso_ent) {
        this.peso_ent = peso_ent;
    }

    public String getNome_motor_ent() {
        return nome_motor_ent;
    }

    public void setNome_motor_ent(String nome_motor_ent) {
        this.nome_motor_ent = nome_motor_ent;
    }

    public int getTipo_frete_ent() {
        return tipo_frete_ent;
    }

    public void setTipo_frete_ent(int tipo_frete_ent) {
        this.tipo_frete_ent = tipo_frete_ent;
    }

    public double getVlr_frete_unit() {
        return vlr_frete_unit;
    }

    public void setVlr_frete_unit(double vlr_frete_unit) {
        this.vlr_frete_unit = vlr_frete_unit;
    }

    public double getVlr_frete_total() {
        return vlr_frete_total;
    }

    public void setVlr_frete_total(double vlr_frete_total) {
        this.vlr_frete_total = vlr_frete_total;
    }

    public String getUsuario_cad_ent() {
        return usuario_cad_ent;
    }

    public void setUsuario_cad_ent(String usuario_cad_ent) {
        this.usuario_cad_ent = usuario_cad_ent;
    }
    
    
}
