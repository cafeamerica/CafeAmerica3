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
public class CtrlEmbViagemSai {
    private int id_ctrl_emb_viagem_sai;
    private int id_tipo_emb;
    private int id_guia;
    private int id_viagem;
    private double qtd_emb;
    
    public CtrlEmbViagemSai(){
    
    }

    public int getId_ctrl_emb_viagem_sai() {
        return id_ctrl_emb_viagem_sai;
    }

    public void setId_ctrl_emb_viagem_sai(int id_ctrl_emb_viagem_sai) {
        this.id_ctrl_emb_viagem_sai = id_ctrl_emb_viagem_sai;
    }

    public int getId_tipo_emb() {
        return id_tipo_emb;
    }

    public void setId_tipo_emb(int id_tipo_emb) {
        this.id_tipo_emb = id_tipo_emb;
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

    public double getQtd_emb() {
        return qtd_emb;
    }

    public void setQtd_emb(double qtd_emb) {
        this.qtd_emb = qtd_emb;
    }
    
    
}
