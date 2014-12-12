/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entradas;

import dao.DAOModel;
import java.sql.SQLException;
import model.entradas.ServicosGuia;

/**
 *
 * @author Rafael
 */
public class DAOServicosGuia extends DAOModel{
    
    public DAOServicosGuia(){
    
    }
    
    public void inserir(ServicosGuia sg) throws SQLException{
        conectar();
        String sql = "INSERT INTO serv_guia(id_guia, id_serv) VALUES(?,?)";
        
        this.setPs(this.getCon().prepareStatement(sql));
        
        this.getPs().setInt(1, sg.getId_guia());
        this.getPs().setInt(2, sg.getId_serv());
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
}
