/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.saidas;

/**
 *
 * @author Rafael
 */
public class Resumo {
    private String tipo_op;
    private double qtd;
    private double kilos;
    private double quebra;
    private String desc_serv_sai;
    private double qtd_sacas_sai;
    private double peso_sai;
    
    public Resumo(){
    
    }

    public String getTipo_op() {
        return tipo_op;
    }

    public void setTipo_op(String tipo_op) {
        this.tipo_op = tipo_op;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public double getKilos() {
        return kilos;
    }

    public void setKilos(double kilos) {
        this.kilos = kilos;
    }

    public double getQuebra() {
        return quebra;
    }

    public void setQuebra(double quebra) {
        this.quebra = quebra;
    }

    public String getDesc_serv_sai() {
        return desc_serv_sai;
    }

    public void setDesc_serv_sai(String desc_serv_sai) {
        this.desc_serv_sai = desc_serv_sai;
    }

    public double getQtd_sacas_sai() {
        return qtd_sacas_sai;
    }

    public void setQtd_sacas_sai(double qtd_sacas_sai) {
        this.qtd_sacas_sai = qtd_sacas_sai;
    }

    public double getPeso_sai() {
        return peso_sai;
    }

    public void setPeso_sai(double peso_sai) {
        this.peso_sai = peso_sai;
    }
    
    
}
