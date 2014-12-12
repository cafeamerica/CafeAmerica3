/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entradas;

import dao.DAOModel;
import java.sql.SQLException;
import model.entradas.PdcGuiaData;

/**
 *
 * @author Rafael
 */
public class DAOPdcGuiaData extends DAOModel{
    
    public DAOPdcGuiaData(){
    
    }
    
    public void inserir(PdcGuiaData pgd) throws SQLException{
        conectar();
        String sql = "INSERT INTO pdc_guia_data(id_guia, peso, data_pdc, usuario_cad_pdc, tipo_cafe, peso_bruto)VALUES(?,?,?,?,?,?)";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, pgd.getId_guia());
        this.getPs().setDouble(2, pgd.getPeso());
        this.getPs().setDate(3, pgd.getData_pdc());
        this.getPs().setString(4, pgd.getUsuario_cad_pdc());
        this.getPs().setInt(5, pgd.getTipo_cafe());
        this.getPs().setDouble(6, pgd.getPeso_bruto());
        
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
}
