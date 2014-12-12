/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.cadastros;

import dao.DAOModel;
import java.sql.SQLException;
import model.cadastros.Cidades;

/**
 *
 * @author Rafael
 */
public class DAOCidades extends DAOModel {

    public DAOCidades() {

    }

    public void inserir(Cidades c) throws SQLException {
        conectar();
        String sql = "INSERT INTO cidades(nome_cid, est_cid)VALUES(?,?)";
        this.setPs(this.getCon().prepareStatement(sql));

        this.getPs().setString(1, c.getNome_cid());
        this.getPs().setInt(2, c.getEst_cid());

        this.getPs().execute();

        desconectar();
    }

    public boolean existeCid(String nome_cid, int est_cid) throws SQLException {
        boolean condicao = false;
        conectar();
        String sql = "SELECT * FROM CIDADES WHERE nome_cid = ? AND est_cid = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, nome_cid);
        this.getPs().setInt(2, est_cid);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            condicao = true;
        }

        desconectar();
        return condicao;
    }

}
