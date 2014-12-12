/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.saidas;

import dao.DAOModel;
import java.sql.SQLException;
import model.saidas.AddServGuia;

/**
 *
 * @author Rafael
 */
public class DAOAddServGuia extends DAOModel{
    
    
    public DAOAddServGuia(){
    
    }
    
    public void inserir(AddServGuia asg) throws SQLException{
        conectar();
        String sql = "INSERT INTO add_serv_guia(id_guia, id_serv, desc_serv, qtd_sacas_add, peso_add, vlr_unit_add, vlr_total_add)VALUES(?,?,?,?,?,?,?)";
        
        this.setPs(this.getCon().prepareStatement(sql));
        
        this.getPs().setInt(1, asg.getId_guia());
        this.getPs().setInt(2, asg.getId_serv());
        this.getPs().setString(3, asg.getDesc_serv());
        this.getPs().setDouble(4, asg.getQtd_sacas_add());
        this.getPs().setDouble(5, asg.getPeso_add());
        this.getPs().setDouble(6, asg.getVlr_unit_add());
        this.getPs().setDouble(7, asg.getVlr_total_add());
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
        
    }
}
