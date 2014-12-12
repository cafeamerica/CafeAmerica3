/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.saidas;

import dao.DAOModel;
import java.sql.SQLException;
import model.entradas.NfeEnt;
import model.saidas.NfeSai;

/**
 *
 * @author Rafael
 */
public class DAONfeSai extends DAOModel{
    
    public DAONfeSai(){
    
    }
    
    public void inserir(NfeSai ns) throws SQLException{
        conectar();
        String sql = "INSERT INTO nfe_sai(num_nota, id_guia, id_viagem_sai, dt_nfe_sai, status_nfe, qtd_sacas_nfe_sai, peso_nfe_sai)VALUES(?,?,?,?,?,?,?)";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, ns.getNum_nota());
        this.getPs().setInt(2, ns.getId_guia());
        this.getPs().setInt(3, ns.getId_viagem_sai());
        this.getPs().setDate(4, ns.getDt_nfe_sai());
        this.getPs().setInt(5, ns.getStatus_nfe());
        this.getPs().setDouble(6, ns.getQtd_sacas_sai_fiscal());
        this.getPs().setDouble(7, ns.getPeso_nfe_sai());
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
    
    public boolean consultaNumNota(String num_nota) throws SQLException{
        boolean condicao = false;
        conectar();
        String sql = "SELECT num_nota FROM nfe_sai WHERE num_nota = ?";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, num_nota);
        
        this.setRs(this.getPs().executeQuery());
        
        while(this.getRs().next()){
            condicao = true;
        }
        
        this.getPs().close();
        this.getRs().close();
        this.getCon().close();
        return condicao;
    }
    
    public void alterarNumNfe(String nfe, int id_nfe) throws SQLException{
        conectar();
        String sql = "UPDATE nfe_sai SET num_nota = ? WHERE id_nfe_sai = ?";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, nfe);
        this.getPs().setInt(2, id_nfe);
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
    
     public void alterarQtdSacas(int id_nfe, double qtd_sacas) throws SQLException{
        conectar();
        
        String sql = "UPDATE nfe_sai SET qtd_sacas_nfe_sai = ? WHERE id_nfe_sai = ?";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setDouble(1, qtd_sacas);
        this.getPs().setInt(2, id_nfe);
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
    
    public void alteraStatusConf(int id_nfe) throws SQLException{ //altera status pra 1
    
        conectar();
        String sql = "UPDATE nfe_sai SET status_nfe = 1 WHERE id_nfe_sai = ?";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_nfe);
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
}
