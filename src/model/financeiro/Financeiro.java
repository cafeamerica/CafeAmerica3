/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.financeiro;

import java.sql.Date;

/**
 *
 * @author Rafael
 */
public class Financeiro {
    private int id_financeiro;
    private int tipo_financeiro;
    private String documento;
    private int tipo_operacao;
    private java.sql.Date dt_financeiro;
    private double vlr_financeiro;
    private double credito;
    private double debito;
    private String documento_referencia;
    private int usuario_cria;
    private int empresa;
    private String str_empresa;
    private String str_operacao;
    
    public Financeiro(){
    
    }

    public String getStr_operacao() {
        return str_operacao;
    }

    public void setStr_operacao(String str_operacao) {
        this.str_operacao = str_operacao;
    }
    
    

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public String getStr_empresa() {
        return str_empresa;
    }

    public void setStr_empresa(String str_empresa) {
        this.str_empresa = str_empresa;
    }
    
    

    public int getId_financeiro() {
        return id_financeiro;
    }

    public void setId_financeiro(int id_financeiro) {
        this.id_financeiro = id_financeiro;
    }

    public int getUsuario_cria() {
        return usuario_cria;
    }

    public void setUsuario_cria(int usuario_cria) {
        this.usuario_cria = usuario_cria;
    }

    
    public int getTipo_financeiro() {
        return tipo_financeiro;
    }

    public void setTipo_financeiro(int tipo_financeiro) {
        this.tipo_financeiro = tipo_financeiro;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getTipo_operacao() {
        return tipo_operacao;
    }

    public void setTipo_operacao(int tipo_operacao) {
        this.tipo_operacao = tipo_operacao;
    }

    public Date getDt_financeiro() {
        return dt_financeiro;
    }

    public void setDt_financeiro(Date dt_financeiro) {
        this.dt_financeiro = dt_financeiro;
    }

    public double getVlr_financeiro() {
        return vlr_financeiro;
    }

    public void setVlr_financeiro(double vlr_financeiro) {
        this.vlr_financeiro = vlr_financeiro;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public String getDocumento_referencia() {
        return documento_referencia;
    }

    public void setDocumento_referencia(String documento_referencia) {
        this.documento_referencia = documento_referencia;
    }
    
    
}
