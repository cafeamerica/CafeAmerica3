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
public class PdcUnicaGuia {
    private int id_pdc_unica_guia;
    private int id_guia;
    private double peso;
    private double saldo;
    
    public PdcUnicaGuia(){
    
    }

    public int getId_pdc_unica_guia() {
        return id_pdc_unica_guia;
    }

    public void setId_pdc_unica_guia(int id_pdc_unica_guia) {
        this.id_pdc_unica_guia = id_pdc_unica_guia;
    }

    public int getId_guia() {
        return id_guia;
    }

    public void setId_guia(int id_guia) {
        this.id_guia = id_guia;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
}
