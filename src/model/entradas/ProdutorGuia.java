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
public class ProdutorGuia {
    private int id_pdtr_guia;
    private int id_pdtr;
    private int id_guia;
    private String num_guia;
    
    public ProdutorGuia(){
    
    }

    public String getNum_guia() {
        return num_guia;
    }

    public void setNum_guia(String num_guia) {
        this.num_guia = num_guia;
    }
    
    

    public int getId_pdtr_guia() {
        return id_pdtr_guia;
    }

    public void setId_pdtr_guia(int id_pdtr_guia) {
        this.id_pdtr_guia = id_pdtr_guia;
    }

    public int getId_pdtr() {
        return id_pdtr;
    }

    public void setId_pdtr(int id_pdtr) {
        this.id_pdtr = id_pdtr;
    }

    public int getId_guia() {
        return id_guia;
    }

    public void setId_guia(int id_guia) {
        this.id_guia = id_guia;
    }
    
    
}
