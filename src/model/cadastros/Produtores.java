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
public class Produtores {
    private int id_pdtr;
    private String nome_pdtr;
    private String cnpj_pdtr;
    private String tel1_pdtr;
    private String tel2_pdtr;
    private int cid_pdtr;
    private String usuario_cad_pdtr;
    
    public Produtores(){
    
    }

    public int getId_pdtr() {
        return id_pdtr;
    }

    public void setId_pdtr(int id_pdtr) {
        this.id_pdtr = id_pdtr;
    }

    public String getNome_pdtr() {
        return nome_pdtr;
    }

    public void setNome_pdtr(String nome_pdtr) {
        this.nome_pdtr = nome_pdtr;
    }

    public String getCnpj_pdtr() {
        return cnpj_pdtr;
    }

    public void setCnpj_pdtr(String cnpj_pdtr) {
        this.cnpj_pdtr = cnpj_pdtr;
    }

    public String getTel1_pdtr() {
        return tel1_pdtr;
    }

    public void setTel1_pdtr(String tel1_pdtr) {
        this.tel1_pdtr = tel1_pdtr;
    }

    public String getTel2_pdtr() {
        return tel2_pdtr;
    }

    public void setTel2_pdtr(String tel2_pdtr) {
        this.tel2_pdtr = tel2_pdtr;
    }

    public int getCid_pdtr() {
        return cid_pdtr;
    }

    public void setCid_pdtr(int cid_pdtr) {
        this.cid_pdtr = cid_pdtr;
    }

    public String getUsuario_cad_pdtr() {
        return usuario_cad_pdtr;
    }

    public void setUsuario_cad_pdtr(String usuario_cad_pdtr) {
        this.usuario_cad_pdtr = usuario_cad_pdtr;
    }
    
    
}
