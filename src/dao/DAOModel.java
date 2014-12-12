package dao;


import bd.Conexao;
import classes.ManipulaArquivo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rafael
 */
public class DAOModel {
    
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private String[] param;
    
    public DAOModel(){
    
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }
    
    
  
    
    public void conectar(){
         param = ManipulaArquivo.lerArquivo();
        try {
            this.con = Conexao.conectar(param[0], param[1], param[2]);
        } catch (Exception ex) {
            Logger.getLogger(DAOModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados");
        }
    }
    
    public void desconectar() throws SQLException{
        this.getCon().close();
        this.getPs().close();
        this.getPs().close();
    }
    
    
}
