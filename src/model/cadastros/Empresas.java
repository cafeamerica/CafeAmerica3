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
public class Empresas {
    private int id_emp;
    private String nome_emp;
    private String razao_social_emp;
    private String cnpj_emp;
    private String ie_emp;
    private String end_emp;
    private String bairro_emp;
    private String tel1_emp;
    private String tel2_emp;
    private String responsavel_emp;
    private int cid_emp;
    private String usuario_cad_emp;

    public Empresas(){
    
    }
    
    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public String getNome_emp() {
        return nome_emp;
    }

    public void setNome_emp(String nome_emp) {
        this.nome_emp = nome_emp;
    }

    public String getRazao_social_emp() {
        return razao_social_emp;
    }

    public void setRazao_social_emp(String razao_social_emp) {
        this.razao_social_emp = razao_social_emp;
    }

    public String getCnpj_emp() {
        return cnpj_emp;
    }

    public void setCnpj_emp(String cnpj_emp) {
        this.cnpj_emp = cnpj_emp;
    }

    public String getIe_emp() {
        return ie_emp;
    }

    public void setIe_emp(String ie_emp) {
        this.ie_emp = ie_emp;
    }

    public String getEnd_emp() {
        return end_emp;
    }

    public void setEnd_emp(String end_emp) {
        this.end_emp = end_emp;
    }

    public String getBairro_emp() {
        return bairro_emp;
    }

    public void setBairro_emp(String bairro_emp) {
        this.bairro_emp = bairro_emp;
    }

    public String getTel1_emp() {
        return tel1_emp;
    }

    public void setTel1_emp(String tel1_emp) {
        this.tel1_emp = tel1_emp;
    }

    public String getTel2_emp() {
        return tel2_emp;
    }

    public void setTel2_emp(String tel2_emp) {
        this.tel2_emp = tel2_emp;
    }

    public String getResponsavel_emp() {
        return responsavel_emp;
    }

    public void setResponsavel_emp(String responsavel_emp) {
        this.responsavel_emp = responsavel_emp;
    }

    public int getCid_emp() {
        return cid_emp;
    }

    public void setCid_emp(int cid_emp) {
        this.cid_emp = cid_emp;
    }

    public String getUsuario_cad_emp() {
        return usuario_cad_emp;
    }

    public void setUsuario_cad_emp(String usuario_cad_emp) {
        this.usuario_cad_emp = usuario_cad_emp;
    }
    
    
}
