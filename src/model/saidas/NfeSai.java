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
public class NfeSai {
    private int id_nfe_sai;
    private String num_nota;
    private int id_guia;
    private int id_viagem_sai;
    private java.sql.Date dt_nfe_sai;
    private int status_nfe;
    private double qtd_sacas_sai_fiscal;
    private double peso_nfe_sai;
    
    public NfeSai(){
    
    }

    public double getPeso_nfe_sai() {
        return peso_nfe_sai;
    }

    public void setPeso_nfe_sai(double peso_nfe_sai) {
        this.peso_nfe_sai = peso_nfe_sai;
    }
    
    

    public double getQtd_sacas_sai_fiscal() {
        return qtd_sacas_sai_fiscal;
    }

    public void setQtd_sacas_sai_fiscal(double qtd_sacas_sai_fiscal) {
        this.qtd_sacas_sai_fiscal = qtd_sacas_sai_fiscal;
    }
    
    

    public int getId_nfe_sai() {
        return id_nfe_sai;
    }

    public void setId_nfe_sai(int id_nfe_sai) {
        this.id_nfe_sai = id_nfe_sai;
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

    public int getId_viagem_sai() {
        return id_viagem_sai;
    }

    public void setId_viagem_sai(int id_viagem_sai) {
        this.id_viagem_sai = id_viagem_sai;
    }

    public Date getDt_nfe_sai() {
        return dt_nfe_sai;
    }

    public void setDt_nfe_sai(Date dt_nfe_sai) {
        this.dt_nfe_sai = dt_nfe_sai;
    }

    public int getStatus_nfe() {
        return status_nfe;
    }

    public void setStatus_nfe(int status_nfe) {
        this.status_nfe = status_nfe;
    }
    
    
            
            
}
