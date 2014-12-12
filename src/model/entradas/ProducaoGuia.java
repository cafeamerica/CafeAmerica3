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
public class ProducaoGuia {
    private int id_pdc_guia;
    private int id_guia;
    private int tipo_cafe_pdc;
    private double peso_pdc;
    private double qtd_sacas_pdc;
    private double saldo_pdc;
    private java.sql.Date dt_producao_pdc;
    private String desc_serv;
    private double peso_bruto;
    
    public ProducaoGuia(){
    
    }

    public double getPeso_bruto() {
        return peso_bruto;
    }

    public void setPeso_bruto(double peso_bruto) {
        this.peso_bruto = peso_bruto;
    }
    
    

    public String getDesc_serv() {
        return desc_serv;
    }

    public void setDesc_serv(String desc_serv) {
        this.desc_serv = desc_serv;
    }

    
    
    public int getId_pdc_guia() {
        return id_pdc_guia;
    }

    public void setId_pdc_guia(int id_pdc_guia) {
        this.id_pdc_guia = id_pdc_guia;
    }

    public int getId_guia() {
        return id_guia;
    }

    public void setId_guia(int id_guia) {
        this.id_guia = id_guia;
    }

    public int getTipo_cafe_pdc() {
        return tipo_cafe_pdc;
    }

    public void setTipo_cafe_pdc(int tipo_cafe_pdc) {
        this.tipo_cafe_pdc = tipo_cafe_pdc;
    }

    public double getPeso_pdc() {
        return peso_pdc;
    }

    public void setPeso_pdc(double peso_pdc) {
        this.peso_pdc = peso_pdc;
    }

    public double getQtd_sacas_pdc() {
        return qtd_sacas_pdc;
    }

    public void setQtd_sacas_pdc(double qtd_sacas_pdc) {
        this.qtd_sacas_pdc = qtd_sacas_pdc;
    }

    public double getSaldo_pdc() {
        return saldo_pdc;
    }

    public void setSaldo_pdc(double saldo_pdc) {
        this.saldo_pdc = saldo_pdc;
    }

    public Date getDt_producao_pdc() {
        return dt_producao_pdc;
    }

    public void setDt_producao_pdc(Date dt_producao_pdc) {
        this.dt_producao_pdc = dt_producao_pdc;
    }
    
    
}
