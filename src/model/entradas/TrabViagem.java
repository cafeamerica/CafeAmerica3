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
public class TrabViagem {
    private int id_trab_viagem;
    private String documento_viagem;
    private int id_viagem;
    private int id_guia;
    private String empresa_tv;
    private String nome_trab_tv;
    private double vlr_saca_tv;
    private double qtd_saca_tv;
    private double vlr_total_tv;
    private int id_tipo_viagem;
    
    public TrabViagem(){
    
    }
    
    

    public int getId_trab_viagem() {
        return id_trab_viagem;
    }

    public void setId_trab_viagem(int id_trab_viagem) {
        this.id_trab_viagem = id_trab_viagem;
    }

    public String getDocumento_viagem() {
        return documento_viagem;
    }

    public void setDocumento_viagem(String documento_viagem) {
        this.documento_viagem = documento_viagem;
    }

    public int getId_viagem() {
        return id_viagem;
    }

    public void setId_viagem(int id_viagem) {
        this.id_viagem = id_viagem;
    }

    public int getId_guia() {
        return id_guia;
    }

    public void setId_guia(int id_guia) {
        this.id_guia = id_guia;
    }

    public String getEmpresa_tv() {
        return empresa_tv;
    }

    public void setEmpresa_tv(String empresa_tv) {
        this.empresa_tv = empresa_tv;
    }

    public String getNome_trab_tv() {
        return nome_trab_tv;
    }

    public void setNome_trab_tv(String nome_trab_tv) {
        this.nome_trab_tv = nome_trab_tv;
    }

    public double getVlr_saca_tv() {
        return vlr_saca_tv;
    }

    public void setVlr_saca_tv(double vlr_saca_tv) {
        this.vlr_saca_tv = vlr_saca_tv;
    }

    public double getQtd_saca_tv() {
        return qtd_saca_tv;
    }

    public void setQtd_saca_tv(double qtd_saca_tv) {
        this.qtd_saca_tv = qtd_saca_tv;
    }

    public double getVlr_total_tv() {
        return vlr_total_tv;
    }

    public void setVlr_total_tv(double vlr_total_tv) {
        this.vlr_total_tv = vlr_total_tv;
    }

    public int getId_tipo_viagem() {
        return id_tipo_viagem;
    }

    public void setId_tipo_viagem(int id_tipo_viagem) {
        this.id_tipo_viagem = id_tipo_viagem;
    }
    
    
}
