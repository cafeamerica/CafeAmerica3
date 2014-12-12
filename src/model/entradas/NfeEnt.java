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
public class NfeEnt {
    private int id_nfe_ent;
    private String num_nota;
    private int id_guia;
    private int id_viagem_ent;
    private java.sql.Date dt_nfe_ent;
    private int status;
    private double qtd_sacas_ent_fiscal;
    private double peso_nfe_ent;
    
    public NfeEnt(){
    
    }

    public double getPeso_nfe_ent() {
        return peso_nfe_ent;
    }

    public void setPeso_nfe_ent(double peso_nfe_ent) {
        this.peso_nfe_ent = peso_nfe_ent;
    }
    
    
    public double getQtd_sacas_ent_fiscal() {
        return qtd_sacas_ent_fiscal;
    }

    public void setQtd_sacas_ent_fiscal(double qtd_sacas_ent_fiscal) {
        this.qtd_sacas_ent_fiscal = qtd_sacas_ent_fiscal;
    }
    

    public int getId_nfe_ent() {
        return id_nfe_ent;
    }

    public void setId_nfe_ent(int id_nfe_ent) {
        this.id_nfe_ent = id_nfe_ent;
    }

    public String getNum_nota() {
        return num_nota;
    }

    public void setNum_nota(String num_nota) {
        this.num_nota = num_nota;
    }

    public int getId_guia() {
        return id_guia;
    }

    public void setId_guia(int id_guia) {
        this.id_guia = id_guia;
    }

    public int getId_viagem_ent() {
        return id_viagem_ent;
    }

    public void setId_viagem_ent(int id_viagem_ent) {
        this.id_viagem_ent = id_viagem_ent;
    }

    public Date getDt_nfe_ent() {
        return dt_nfe_ent;
    }

    public void setDt_nfe_ent(Date dt_nfe_ent) {
        this.dt_nfe_ent = dt_nfe_ent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
