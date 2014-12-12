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
public class TipoCafeViagem {
    private int tipo_cafe_viagem;
    private int tipo_cafe;
    private int id_guia;
    private int id_viagem;
    private int tipo_viagem;
    private double peso;
    
    public TipoCafeViagem(){
    
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    

    public int getTipo_cafe_viagem() {
        return tipo_cafe_viagem;
    }

    public void setTipo_cafe_viagem(int tipo_cafe_viagem) {
        this.tipo_cafe_viagem = tipo_cafe_viagem;
    }

    public int getTipo_cafe() {
        return tipo_cafe;
    }

    public void setTipo_cafe(int tipo_cafe) {
        this.tipo_cafe = tipo_cafe;
    }

    public int getId_guia() {
        return id_guia;
    }

    public void setId_guia(int id_guia) {
        this.id_guia = id_guia;
    }

    public int getId_viagem() {
        return id_viagem;
    }

    public void setId_viagem(int id_viagem) {
        this.id_viagem = id_viagem;
    }

    public int getTipo_viagem() {
        return tipo_viagem;
    }

    public void setTipo_viagem(int tipo_viagem) {
        this.tipo_viagem = tipo_viagem;
    }
    
    
}
