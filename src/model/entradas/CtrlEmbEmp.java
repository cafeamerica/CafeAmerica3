/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entradas;

/**
 *
 * @author Rafael
 */
public class CtrlEmbEmp {
    private int ctrl_emb_emp;
    private int id_tipo_emb;
    private int id_emp;
    private double qtd_emb_total;
    private double qtd_emb_baixada;
    private String str_tipo;
    
    public CtrlEmbEmp(){
    
    }

    public String getStr_tipo() {
        return str_tipo;
    }

    public void setStr_tipo(String str_tipo) {
        this.str_tipo = str_tipo;
    }
    
    

    public int getCtrl_emb_emp() {
        return ctrl_emb_emp;
    }

    public void setCtrl_emb_emp(int ctrl_emb_emp) {
        this.ctrl_emb_emp = ctrl_emb_emp;
    }

    public int getId_tipo_emb() {
        return id_tipo_emb;
    }

    public void setId_tipo_emb(int id_tipo_emb) {
        this.id_tipo_emb = id_tipo_emb;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public double getQtd_emb_total() {
        return qtd_emb_total;
    }

    public void setQtd_emb_total(double qtd_emb_total) {
        this.qtd_emb_total = qtd_emb_total;
    }

    public double getQtd_emb_baixada() {
        return qtd_emb_baixada;
    }

    public void setQtd_emb_baixada(double qtd_emb_baixada) {
        this.qtd_emb_baixada = qtd_emb_baixada;
    }
    
    
}
