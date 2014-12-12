/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entradas;

import dao.DAOModel;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import model.entradas.PdcUnicaGuia;

/**
 *
 * @author Rafael
 */
public class DAOPdcUnicaGuia extends DAOModel{

    public DAOPdcUnicaGuia() {
    }
    
    public void inserir(PdcUnicaGuia pug) throws SQLException{
        conectar();
        String sql = "INSERT INTO pdc_unica_guia(id_guia, peso, saldo)VALUES(?,?,?)";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, pug.getId_guia());
        this.getPs().setDouble(2, pug.getPeso());
        this.getPs().setDouble(3, pug.getSaldo());
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
    
    public double retornaPesoAtualPdc(int id_guia) throws SQLException{
        conectar();
        double peso = 0;
        
        String sql = "SELECT peso FROM pdc_unica_guia WHERE id_guia = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);
        this.setRs(this.getPs().executeQuery());
        
        
        while(this.getRs().next()){
            peso = this.getRs().getDouble("peso");
        }
        
        this.getRs().close();
        this.getPs().close();
        this.getCon().close();
        
        return peso;
    }
    public double retornaSaldoAtualPdc(int id_guia) throws SQLException{
        conectar();
        double saldo = 0;
        
        String sql = "SELECT saldo FROM pdc_unica_guia WHERE id_guia = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);
        this.setRs(this.getPs().executeQuery());
        
        
        while(this.getRs().next()){
            saldo = this.getRs().getDouble("saldo");
        }
        
        this.getRs().close();
        this.getPs().close();
        this.getCon().close();
        
        return saldo;
    }
    
    public void alteraPesoPdc(int id_guia, double peso) throws SQLException{
    
        conectar();
        String sql = "UPDATE pdc_unica_guia SET peso = ? WHERE id_guia = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setDouble(1, peso);
        this.getPs().setInt(2, id_guia);
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
    
     public void alteraSaldoPdc(int id_guia, double saldo) throws SQLException{
    
        conectar();
        String sql = "UPDATE pdc_unica_guia SET saldo = ? WHERE id_guia = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setDouble(1, saldo);
        this.getPs().setInt(2, id_guia);
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
     
     public List<PdcUnicaGuia> retornaDados(int id_guia) throws SQLException{
         List<PdcUnicaGuia> list = new ArrayList<PdcUnicaGuia>();
         conectar();
         String sql = "SELECT peso, saldo FROM pdc_unica_guia WHERE id_guia = ?";
         
         this.setPs(this.getCon().prepareStatement(sql));
         this.getPs().setInt(1, id_guia);
         this.setRs(this.getPs().executeQuery());
         
         while(this.getRs().next()){
             PdcUnicaGuia pug = new PdcUnicaGuia();
             
             pug.setPeso(this.getRs().getDouble("peso"));
             pug.setSaldo(this.getRs().getDouble("saldo"));
             
             list.add(pug);
         }
         
         this.getPs().close();
         this.getRs().close();
         this.getCon().close();
         
         return list;
     }
}
