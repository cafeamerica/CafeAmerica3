/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entradas;

import dao.DAOModel;
import java.sql.SQLException;
import model.entradas.NfeEnt;

/**
 *
 * @author Rafael
 */
public class DAONfeEnt extends DAOModel {
    
    public DAONfeEnt(){
    
    }
    
    public void inserir(NfeEnt ne) throws SQLException{
        conectar();
        String sql = "INSERT INTO nfe_ent(num_nota, id_guia, id_viagem_ent, dt_nfe_ent, status_nfe, qtd_sacas_nfe_ent, peso_nfe_ent)VALUES(?,?,?,?,?,?,?)";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, ne.getNum_nota());
        this.getPs().setInt(2, ne.getId_guia());
        this.getPs().setInt(3, ne.getId_viagem_ent());
        this.getPs().setDate(4, ne.getDt_nfe_ent());
        this.getPs().setInt(5, ne.getStatus());
        this.getPs().setDouble(6, ne.getQtd_sacas_ent_fiscal());
        this.getPs().setDouble(7, ne.getPeso_nfe_ent());
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
    
    public boolean consultaNumNota(String num_nota) throws SQLException{
        boolean condicao = false;
        conectar();
        String sql = "SELECT num_nota FROM nfe_ent WHERE num_nota = ?";
        
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
        String sql = "UPDATE nfe_ent SET num_nota = ? WHERE id_nfe_ent = ?";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, nfe);
        this.getPs().setInt(2, id_nfe);
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
    
    public void alterarQtdSacas(int id_nfe, double qtd_sacas) throws SQLException{
        conectar();
        
        String sql = "UPDATE nfe_ent SET qtd_sacas_nfe_ent = ? WHERE id_nfe_ent = ?";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setDouble(1, qtd_sacas);
        this.getPs().setInt(2, id_nfe);
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
    
    public void alteraStatusConf(int id_nfe) throws SQLException{ //altera status pra 1
    
        conectar();
        String sql = "UPDATE nfe_ent SET status_nfe = 1 WHERE id_nfe_ent = ?";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_nfe);
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
    
   
}
