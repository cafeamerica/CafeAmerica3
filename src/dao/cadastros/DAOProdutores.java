/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.cadastros;

import dao.DAOModel;
import java.sql.SQLException;
import java.util.ArrayList;
import model.cadastros.Produtores;

/**
 *
 * @author Rafael
 */
public class DAOProdutores extends DAOModel {
    
    
    public DAOProdutores(){
    
    }
    
    public void inserir(Produtores pdtr) throws SQLException{
        conectar();
        String sql = "INSERT INTO produtores(nome_pdtr, cnpj_pdtr, tel1_pdtr, tel2_pdtr"
                + " cid_pdtr, usuario_cad_pdtr) VALUES(?,?,?,?,?,?)";
        
        this.setPs(getCon().prepareStatement(sql));
        this.getPs().setString(1, pdtr.getNome_pdtr());
        this.getPs().setString(2, pdtr.getCnpj_pdtr());
        this.getPs().setString(3, pdtr.getTel1_pdtr());
        this.getPs().setString(4, pdtr.getTel2_pdtr());
        this.getPs().setInt(5, pdtr.getCid_pdtr());
        this.getPs().setString(6, pdtr.getUsuario_cad_pdtr());
        
        this.getPs().execute();
        
        this.getPs().close();
        
        this.getCon().close();       
        
    }
    
    public ArrayList consultarProdutores() throws SQLException{
        conectar();
        ArrayList list = new ArrayList();
        String sql = "SELECT nome_pdtr FROM produtores ORDER BY nome_pdtr";
        
        this.setPs(getCon().prepareStatement(sql));
        this.setRs(getPs().executeQuery());
        
        while(this.getRs().next()){
            String x = "";
            x = this.getRs().getString("nome_pdtr");
            list.add(x);
            x = "";
        }
        
        this.getPs().close();
        this.getRs().close();
        this.getCon().close();
        
        return list;
    }
    
    public int retornaIDPorNome(String nome_pdtr) throws SQLException{
       int id_emp = 0;
       conectar();
       
       String sql = "SELECT id_pdtr FROM produtores WHERE nome_pdtr = ?";
       
       this.setPs(this.getCon().prepareStatement(sql));
       this.getPs().setString(1, nome_pdtr);
       
       this.setRs(this.getPs().executeQuery());
       
       while(this.getRs().next()){
           id_emp = this.getRs().getInt("id_pdtr");
       }
       
       this.getPs().close();
       this.getRs().close();
       this.getCon().close();
       
       return id_emp;
    }
}
