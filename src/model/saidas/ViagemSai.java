/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.saidas;

import java.sql.Date;

/**
 *
 * @author Rafael
 */
public class ViagemSai {
    private int id_viagem_sai_guia;
    private String doc_viagem_sai;
    private java.sql.Date dt_sai;
    private int tipo_cafe_sai;
    private double qtd_sacas_sai;
    private double peso_sai;
    private String nome_motor_sai;
    private int tipo_frete_sai;
    private double vlr_frete_unit;
    private double vlr_frete_total;
    private String usuario_cad_sai;
    private String placa_cam_sai;
    private String num_nota;
    private double vol_fiscal;
    private double peso_fiscal;
    private String str_tipo_cafe;
    private String num_guia;
    private String obs_sai;
    
    public ViagemSai(){
    
    }

    public String getObs_sai() {
        return obs_sai;
    }

    public void setObs_sai(String obs_sai) {
        this.obs_sai = obs_sai;
    }
    
    

    public String getNum_guia() {
        return num_guia;
    }

    public void setNum_guia(String num_guia) {
        this.num_guia = num_guia;
    }
    
    

    public String getStr_tipo_cafe() {
        return str_tipo_cafe;
    }

    public void setStr_tipo_cafe(String str_tipo_cafe) {
        this.str_tipo_cafe = str_tipo_cafe;
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
    
    

    public String getPlaca_cam_sai() {
        return placa_cam_sai;
    }

    public void setPlaca_cam_sai(String placa_cam_sai) {
        this.placa_cam_sai = placa_cam_sai;
    }

    public String getNum_nota() {
        return num_nota;
    }

    public void setNum_nota(String num_nota) {
        this.num_nota = num_nota;
    }

    
    
    public int getId_viagem_sai_guia() {
        return id_viagem_sai_guia;
    }

    public void setId_viagem_sai_guia(int id_viagem_sai_guia) {
        this.id_viagem_sai_guia = id_viagem_sai_guia;
    }

    public String getDoc_viagem_sai() {
        return doc_viagem_sai;
    }

    public void setDoc_viagem_sai(String doc_viagem_sai) {
        this.doc_viagem_sai = doc_viagem_sai;
    }

    public Date getDt_sai() {
        return dt_sai;
    }

    public void setDt_sai(Date dt_sai) {
        this.dt_sai = dt_sai;
    }

    public int getTipo_cafe_sai() {
        return tipo_cafe_sai;
    }

    public void setTipo_cafe_sai(int tipo_cafe_sai) {
        this.tipo_cafe_sai = tipo_cafe_sai;
    }

    public double getQtd_sacas_sai() {
        return qtd_sacas_sai;
    }

    public void setQtd_sacas_sai(double qtd_saca_sai) {
        this.qtd_sacas_sai = qtd_saca_sai;
    }

    public double getPeso_sai() {
        return peso_sai;
    }

    public void setPeso_sai(double peso_sai) {
        this.peso_sai = peso_sai;
    }

    public String getNome_motor_sai() {
        return nome_motor_sai;
    }

    public void setNome_motor_sai(String nome_motor_sai) {
        this.nome_motor_sai = nome_motor_sai;
    }

    public int getTipo_frete_sai() {
        return tipo_frete_sai;
    }

    public void setTipo_frete_sai(int tipo_frete_sai) {
        this.tipo_frete_sai = tipo_frete_sai;
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

    public String getUsuario_cad_sai() {
        return usuario_cad_sai;
    }

    public void setUsuario_cad_sai(String usuario_cad_sai) {
        this.usuario_cad_sai = usuario_cad_sai;
    }
    
    
}
