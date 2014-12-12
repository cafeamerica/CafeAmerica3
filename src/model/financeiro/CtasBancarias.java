/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.financeiro;

/**
 *
 * @author Rafael
 */
public class CtasBancarias {
    private int id_cta_bancaria;
    private String banco;
    private String agencia;
    private String numero;
    private String digito;
    private String tipo_conta;
    private String titular;
    private double saldo;
    
    public CtasBancarias(){
    
    }

    public int getId_cta_bancaria() {
        return id_cta_bancaria;
    }

    public void setId_cta_bancaria(int id_cta_bancaria) {
        this.id_cta_bancaria = id_cta_bancaria;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDigito() {
        return digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }

    public String getTipo_conta() {
        return tipo_conta;
    }

    public void setTipo_conta(String tipo_conta) {
        this.tipo_conta = tipo_conta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
}
