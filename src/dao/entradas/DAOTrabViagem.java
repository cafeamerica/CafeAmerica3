/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entradas;

import dao.DAOModel;
import java.sql.SQLException;
import model.entradas.TrabViagem;

/**
 *
 * @author Rafael
 */
public class DAOTrabViagem extends DAOModel{
    
    public DAOTrabViagem(){
    
    }
    
    public void inserir(TrabViagem tv) throws SQLException{
        conectar();
        String sql = "INSERT INTO trab_viagem(documento_viagem, id_viagem, id_guia, empresa_tv,"
                + "                          nome_trab_tv, vlr_saca_tv, qtd_saca_tv, vlr_total_tv,"
                + "                          id_tipo_viagem)VALUES(?,?,?,?,?,?,?,?,?)";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, tv.getDocumento_viagem());
        this.getPs().setInt(2, tv.getId_viagem());
        this.getPs().setInt(3, tv.getId_guia());
        this.getPs().setString(4, tv.getEmpresa_tv());
        this.getPs().setString(5, tv.getNome_trab_tv());
        this.getPs().setDouble(6, tv.getVlr_saca_tv());
        this.getPs().setDouble(7, tv.getQtd_saca_tv());
        this.getPs().setDouble(8, tv.getVlr_total_tv());
        this.getPs().setInt(9, tv.getId_tipo_viagem());
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
}
