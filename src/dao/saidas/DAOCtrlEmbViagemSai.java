/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.saidas;

import dao.DAOModel;
import java.sql.SQLException;
import model.entradas.CtrlEmbViagemEnt;
import model.saidas.CtrlEmbViagemSai;

/**
 *
 * @author Rafael
 */
public class DAOCtrlEmbViagemSai extends DAOModel{
    
    
    public DAOCtrlEmbViagemSai(){
    
    }
    
    public void inserir(CtrlEmbViagemSai  cees) throws SQLException{
        conectar();
        String sql = "INSERT INTO ctrl_emb_viagem_sai(id_tipo_emb, id_viagem, id_guia, qtd_emb)VALUES(?,?,?,?)";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, cees.getId_tipo_emb());
        this.getPs().setInt(2, cees.getId_viagem());
        this.getPs().setInt(3, cees.getId_guia());
        this.getPs().setDouble(4, cees.getQtd_emb());
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
}
