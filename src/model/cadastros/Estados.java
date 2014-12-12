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
public class Estados {
    private int id_est;
    private String nome_est;
    private String sigla_est;
    
    public Estados(){
    
    }

    public int getId_est() {
        return id_est;
    }

    public void setId_est(int id_est) {
        this.id_est = id_est;
    }

    public String getNome_est() {
        return nome_est;
    }

    public void setNome_est(String nome_est) {
        this.nome_est = nome_est;
    }

    public String getSigla_est() {
        return sigla_est;
    }

    public void setSigla_est(String sigla_est) {
        this.sigla_est = sigla_est;
    }
    
    
}
