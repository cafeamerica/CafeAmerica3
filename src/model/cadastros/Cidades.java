/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.cadastros;

/**
 *
 * @author Rafael
 */
public class Cidades {
    private int id_cid;
    private String nome_cid;
    private int est_cid;
    private String str_est_cid;
    
    public Cidades(){
    
    }

    public int getId_cid() {
        return id_cid;
    }

    public void setId_cid(int id_cid) {
        this.id_cid = id_cid;
    }

    public String getNome_cid() {
        return nome_cid;
    }

    public void setNome_cid(String nome_cid) {
        this.nome_cid = nome_cid;
    }

    public int getEst_cid() {
        return est_cid;
    }

    public void setEst_cid(int est_cid) {
        this.est_cid = est_cid;
    }

    public String getStr_est_cid() {
        return str_est_cid;
    }

    public void setStr_est_cid(String str_est_cid) {
        this.str_est_cid = str_est_cid;
    }
    
    
}
