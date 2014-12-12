/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.saidas;

import dao.DAOModel;
import java.sql.SQLException;
import model.saidas.TipoCafeViagem;

/**
 *
 * @author Rafael
 */
public class DAOTipoCafeViagem extends DAOModel{
    
    public DAOTipoCafeViagem(){
    
    }
    
    public void inserir(TipoCafeViagem tcv) throws SQLException{
        conectar();
        String sql = "INSERT INTO tipo_cafe_viagem(tipo_cafe, id_guia, id_viagem, tipo_viagem, peso)VALUES(?,?,?,?,?)";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, tcv.getTipo_cafe());
        this.getPs().setInt(2, tcv.getId_guia());
        this.getPs().setInt(3, tcv.getId_viagem());
        this.getPs().setInt(4, tcv.getTipo_viagem());
        this.getPs().setDouble(5, tcv.getPeso());
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
}
