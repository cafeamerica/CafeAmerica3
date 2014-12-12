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
public class GuiaViagemEnt {
    private int id_guia_viagem_ent;
    private int id_guia;
    private int id_viagem;
    private String num_guia;
    
    
    public GuiaViagemEnt(){
    
    }

    public int getId_guia_viagem_ent() {
        return id_guia_viagem_ent;
    }

    public void setId_guia_viagem_ent(int id_guia_viagem_ent) {
        this.id_guia_viagem_ent = id_guia_viagem_ent;
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
