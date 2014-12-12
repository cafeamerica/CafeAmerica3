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
public class GuiaViagemSai {
    private int id_guia_viagem_sai;
    private int id_guia;
    private int id_viagem;
    private String num_guia;
    
    public GuiaViagemSai(){
    
    }

    public int getId_guia_viagem_sai() {
        return id_guia_viagem_sai;
    }

    public void setId_guia_viagem_sai(int id_guia_viagem_sai) {
        this.id_guia_viagem_sai = id_guia_viagem_sai;
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

    public String getNum_guia() {
        return num_guia;
    }

    public void setNum_guia(String num_guia) {
        this.num_guia = num_guia;
    }
    
    
}
