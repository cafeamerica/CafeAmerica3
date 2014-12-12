/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entradas;

import dao.DAOModel;
import java.sql.SQLException;
import model.entradas.ProdutorGuia;

/**
 *
 * @author Rafael
 */
public class DAOProdutorGuia extends DAOModel{
    
    public DAOProdutorGuia(){
    
    }
    
    public void inserir(ProdutorGuia pg) throws SQLException{
        conectar();
        String sql = "INSERT INTO pdtr_guia(id_pdtr, id_guia, num_guia) VALUES(?,?,?)";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, pg.getId_pdtr());
        this.getPs().setInt(2, pg.getId_guia());
        this.getPs().setString(3, pg.getNum_guia());
        this.getPs().execute();
        
        this.getPs().close();
        this.getCon().close();
    }
    
    public boolean consultaGuiaPdtr(String num_guia, int id_pdtr) throws SQLException{
        boolean condicao = false;
        conectar();
                
        String sql = "SELECT * FROM pdtr_guia WHERE num_guia = ? AND id_pdtr = ?";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, num_guia);
        this.getPs().setInt(2, id_pdtr);
        
        this.setRs(this.getPs().executeQuery());
        
        while(this.getRs().next()){
            condicao = true;
        }
        
        this.getPs().close();
        this.getRs().close();
        this.getCon().close();
        
        return condicao;
    }

    public boolean retornaExiste(int id_tipo_cafe_pai, int id_guia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int retornaIdPorTipoGuia(int id_tipo_cafe_pai, int id_guia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
