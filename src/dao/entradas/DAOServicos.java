/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entradas;

import dao.DAOModel;
import java.sql.SQLException;
import java.util.ArrayList;
import model.entradas.Servicos;

/**
 *
 * @author Rafael
 */
public class DAOServicos extends DAOModel{
    
    
    public DAOServicos(){
    
    }
    
    public void inserir(Servicos serv) throws SQLException{
        String sql = "INSERT INTO servicos(desc_serv) VALUES(?)";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, serv.getDesc_serv());
        
        this.getPs().execute();
        
        this.getPs().close();
        
        this.getCon().close();
        
    }
    
    public ArrayList consultarProdutores() throws SQLException{
        conectar();
        ArrayList list = new ArrayList();
        String sql = "SELECT desc_serv FROM servicos ORDER BY desc_serv";
        
        this.setPs(getCon().prepareStatement(sql));
        this.setRs(getPs().executeQuery());
        
        while(this.getRs().next()){
            String x = "";
            x = this.getRs().getString("desc_serv");
            list.add(x);
            x = "";
        }
        
        this.getPs().close();
        this.getRs().close();
        this.getCon().close();
        
        return list;
    }
    
    public int retornaIDPorNome(String desc_serv) throws SQLException{
       int id_emp = 0;
       conectar();
       
       String sql = "SELECT id_serv FROM servicos WHERE desc_serv = ?";
       
       this.setPs(this.getCon().prepareStatement(sql));
       this.getPs().setString(1, desc_serv);
       
       this.setRs(this.getPs().executeQuery());
       
       while(this.getRs().next()){
           id_emp = this.getRs().getInt("id_serv");
       }
       
       this.getPs().close();
       this.getRs().close();
       this.getCon().close();
       
       return id_emp;
    }
    
    public String retornaNomePorID(int id_serv) throws SQLException{
       String s_nome = "";
       conectar();
       
       String sql = "SELECT desc_serv FROM servicos WHERE id_serv = ?";
       
       this.setPs(this.getCon().prepareStatement(sql));
       this.getPs().setInt(1, id_serv);
       
       this.setRs(this.getPs().executeQuery());
       
       while(this.getRs().next()){
           s_nome = this.getRs().getString("desc_serv");
       }
       
       this.getPs().close();
       this.getRs().close();
       this.getCon().close();
       
       return s_nome;
    }
}
