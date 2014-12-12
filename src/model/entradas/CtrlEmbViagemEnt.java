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
public class CtrlEmbViagemEnt {
    private int id_ctrl_emb_viagem_ent;
    private int id_tipo_emb;
    private int id_viagem;
    private int id_guia;
    private double qtd_emb;
    
    public CtrlEmbViagemEnt(){
    
    }

    public int getId_ctrl_emb_viagem_ent() {
        return id_ctrl_emb_viagem_ent;
    }

    public void setId_ctrl_emb_viagem_ent(int id_ctrl_emb_viagem_ent) {
        this.id_ctrl_emb_viagem_ent = id_ctrl_emb_viagem_ent;
    }

    public int getId_tipo_emb() {
        return id_tipo_emb;
    }

    public void setId_tipo_emb(int id_tipo_emb) {
        this.id_tipo_emb = id_tipo_emb;
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

    public double getQtd_emb() {
        return qtd_emb;
    }

    public void setQtd_emb(double qtd_emb) {
        this.qtd_emb = qtd_emb;
    }
    
    
}
