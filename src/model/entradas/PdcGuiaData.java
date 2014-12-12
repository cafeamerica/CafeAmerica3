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
public class PdcGuiaData {
    private int id_pdc_guia_data;
    private int id_guia;
    private double peso;
    private java.sql.Date data_pdc;
    private String usuario_cad_pdc;
    private int tipo_cafe;
    private double peso_bruto;
    
    
    public PdcGuiaData(){
    
    }

    public double getPeso_bruto() {
        return peso_bruto;
    }

    public void setPeso_bruto(double peso_bruto) {
        this.peso_bruto = peso_bruto;
    }
    
    

    public int getTipo_cafe() {
        return tipo_cafe;
    }

    public void setTipo_cafe(int tipo_cafe) {
        this.tipo_cafe = tipo_cafe;
    }
    
    

    public int getId_pdc_guia_data() {
        return id_pdc_guia_data;
    }

    public void setId_pdc_guia_data(int id_pdc_guia_data) {
        this.id_pdc_guia_data = id_pdc_guia_data;
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

    public Date getData_pdc() {
        return data_pdc;
    }

    public void setData_pdc(Date data_pdc) {
        this.data_pdc = data_pdc;
    }

    public String getUsuario_cad_pdc() {
        return usuario_cad_pdc;
    }

    public void setUsuario_cad_pdc(String usuario_cad_pdc) {
        this.usuario_cad_pdc = usuario_cad_pdc;
    }
    
    
}
