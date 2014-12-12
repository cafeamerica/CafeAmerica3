/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.cadastros;

import dao.DAOModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.cadastros.Estados;

/**
 *
 * @author Rafael
 */
public class DAOEstados extends DAOModel {

    public DAOEstados() {

    }

    public void inserir(Estados e) throws SQLException {
        conectar();
        String sql = "INSERT INTO ESTADOS(nome_est, sigla_est) VALUES(?,?)";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, e.getNome_est());
        this.getPs().setString(2, e.getSigla_est());

        this.getPs().execute();

        desconectar();
    }

    public boolean existeEst(String nome_est, String sigla_est) throws SQLException {
        boolean condicao = false;
        conectar();
        String sql = "SELECT * FROM ESTADOS WHERE nome_est = ? OR sigla_est = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, nome_est);
        this.getPs().setString(2, sigla_est);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            condicao = true;
        }

        desconectar();
        return condicao;
    }

    public int retornaIdPorNome(String nome_est) throws SQLException {
        conectar();
        int id = 0;
        String sql = "SELECT id_est FROM estados WHERE nome_est = ?";
        this.setPs(this.getCon().prepareStatement(sql));

        this.getPs().setString(1, nome_est);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            id = this.getRs().getInt("id_est");
        }

        desconectar();
        return id;
    }

    public List<Estados> retornaNomes() throws SQLException {
        List<Estados> list = new ArrayList<Estados>();
        conectar();
        String sql = "SELECT nome_est FROM estados";

        this.setPs(this.getCon().prepareStatement(sql));
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            Estados e = new Estados();

            e.setNome_est(this.getRs().getString("nome_est"));

            list.add(e);
        }

        desconectar();
        return list;
    }
}
