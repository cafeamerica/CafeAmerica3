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
public class NfeModel {
    private int id;
    private String tipo;
    private String num_guia;
    private String nosso_num;
    private String doc_viagem;
    private String motorista;
    private double peso;
    private java.sql.Date dt;
    private double qtd_sacas_real;
    private String num_nota;
    
    public NfeModel(){
    
    }

    public Date getDt() {
        return dt;
    }

    public String getNum_nota() {
        return num_nota;
    }

    public void setNum_nota(String num_nota) {
        this.num_nota = num_nota;
    }
    
    

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public double getQtd_sacas_real() {
        return qtd_sacas_real;
    }

    public void setQtd_sacas_real(double qtd_sacas_real) {
        this.qtd_sacas_real = qtd_sacas_real;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNum_guia() {
        return num_guia;
    }

    public void setNum_guia(String num_guia) {
        this.num_guia = num_guia;
    }

    public String getNosso_num() {
        return nosso_num;
    }

    public void setNosso_num(String nosso_num) {
        this.nosso_num = nosso_num;
    }

    public String getDoc_viagem() {
        return doc_viagem;
    }

    public void setDoc_viagem(String doc_viagem) {
        this.doc_viagem = doc_viagem;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    
}
