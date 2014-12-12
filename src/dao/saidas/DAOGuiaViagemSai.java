/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.saidas;

import dao.DAOModel;
import java.sql.SQLException;
import model.saidas.GuiaViagemSai;

/**
 *
 * @author Rafael
 */
public class DAOGuiaViagemSai extends DAOModel{
    
    
    public DAOGuiaViagemSai(){
        
    }
    
    public void inserir(GuiaViagemSai gvs) throws SQLException{
        conectar();
        String sql = "INSERT INTO guia_viagem_sai(id_guia, id_viagem, num_guia) VALUES (?,?,?)";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, gvs.getId_guia());
        this.getPs().setInt(2, gvs.getId_viagem());
        this.getPs().setString(3, gvs.getNum_guia());

        this.getPs().execute();

        this.getPs().close();

        this.getCon().close();
    }
    
    public int retornaIdMaisUm(int id_guia) throws SQLException {
        int id = 0;
        conectar();
        String sql = "SELECT max(id_viagem)as maximo FROM guia_viagem_sai WHERE id_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            id = this.getRs().getInt("maximo");
        }
        
        id++;
        
        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return id;
    }
    
    public double retornaQtdSacasTotal(int id_guia) throws SQLException {
        double d_sacas = 0;
        conectar();
        String sql = "SELECT SUM(ve.qtd_sacas_sai) as sacas "
                + "FROM guia_viagem_sai gve, viagem_sai ve, guias g "
                + "WHERE gve.id_guia_viagem_sai = ve.id_viagem_sai_guia AND "
                + "      g.id_guia = gve.id_guia AND "
                + "      g.id_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);
        
        this.setRs(this.getPs().executeQuery());
        
        while(this.getRs().next()){
            d_sacas = this.getRs().getDouble("sacas");
        }
        
        this.getPs();
        this.getRs();
        this.getCon();
        
        return d_sacas;
    }
}
