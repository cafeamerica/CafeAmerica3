/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entradas;

import dao.DAOModel;
import java.sql.SQLException;
import model.entradas.CtrlEmbViagemEnt;

/**
 *
 * @author Rafael
 */
public class DAOCtrlEmbViagemEnt extends DAOModel{
    
    public DAOCtrlEmbViagemEnt(){
    
    }
    
    public void inserir(CtrlEmbViagemEnt ceet) throws SQLException{
        conectar();
        String sql = "INSERT INTO ctrl_emb_viagem_ent(id_tipo_emb, id_viagem, id_guia, qtd_emb)VALUES(?,?,?,?)";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, ceet.getId_tipo_emb());
        this.getPs().setInt(2, ceet.getId_viagem());
        this.getPs().setInt(3, ceet.getId_guia());
        this.getPs().setDouble(4, ceet.getQtd_emb());
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
}
