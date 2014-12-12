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
public class AddServGuia {
    private int id_add_serv_guia;
    private int id_guia;
    private int id_serv;
    private String desc_serv;
    private double qtd_sacas_add;
    private double peso_add;
    private double vlr_unit_add;
    private double vlr_total_add;
    
    public AddServGuia(){
    
    }

    public int getId_add_serv_guia() {
        return id_add_serv_guia;
    }

    public void setId_add_serv_guia(int id_add_serv_guia) {
        this.id_add_serv_guia = id_add_serv_guia;
    }

    public int getId_guia() {
        return id_guia;
    }

    public void setId_guia(int id_guia) {
        this.id_guia = id_guia;
    }

    public int getId_serv() {
        return id_serv;
    }

    public void setId_serv(int id_serv) {
        this.id_serv = id_serv;
    }

    public String getDesc_serv() {
        return desc_serv;
    }

    public void setDesc_serv(String desc_serv) {
        this.desc_serv = desc_serv;
    }

    public double getQtd_sacas_add() {
        return qtd_sacas_add;
    }

    public void setQtd_sacas_add(double qtd_sacas_add) {
        this.qtd_sacas_add = qtd_sacas_add;
    }

    public double getPeso_add() {
        return peso_add;
    }

    public void setPeso_add(double peso_add) {
        this.peso_add = peso_add;
    }

    public double getVlr_unit_add() {
        return vlr_unit_add;
    }

    public void setVlr_unit_add(double vlr_unit_add) {
        this.vlr_unit_add = vlr_unit_add;
    }

    public double getVlr_total_add() {
        return vlr_total_add;
    }

    public void setVlr_total_add(double vlr_total_add) {
        this.vlr_total_add = vlr_total_add;
    }
    
    
}
