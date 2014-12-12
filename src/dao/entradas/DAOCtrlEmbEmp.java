/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entradas;

import dao.DAOModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.entradas.CtrlEmbEmp;

/**
 *
 * @author Rafael
 */
public class DAOCtrlEmbEmp extends DAOModel {

    public DAOCtrlEmbEmp() {

    }

    public void inserir(CtrlEmbEmp cee) throws SQLException {
        conectar();
        String sql = "INSERT INTO ctrl_emb_emp(id_tipo_emb, id_emp, qtd_emb_total, qtd_emb_baixada)VALUES(?,?,?,?)";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, cee.getId_tipo_emb());
        this.getPs().setInt(2, cee.getId_emp());
        this.getPs().setDouble(3, cee.getQtd_emb_total());
        this.getPs().setDouble(4, cee.getQtd_emb_baixada());

        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();
    }

    public void alteraQtdTotal(int id_emp, int id_emb, double qtd_baixar) throws SQLException {
        conectar();
        String sql = "UPDATE ctrl_emb_emp SET qtd_emb_total = ? WHERE id_emp = ? AND id_tipo_emb = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setDouble(1, qtd_baixar);
        this.getPs().setInt(2, id_emp);
        this.getPs().setInt(3, id_emb);

        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();
    }

    public int retornaQtdBaixada(int id_emp, int id_emb) throws SQLException {
        conectar();
        int qtd = 0;
        String sql = "SELECT qtd_emb_baixada as qtd FROM ctrl_emb_emp WHERE id_emp = ? AND id_tipo_emb = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_emp);
        this.getPs().setInt(2, id_emb);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            qtd = this.getRs().getInt("qtd");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return qtd;
    }

    public int retornaQtdTotal(int tipo, int id_emp) throws SQLException {
        conectar();
        int qtd_total = 0;
        String sql = "SELECT qtd_emb_total FROM ctrl_emb_emp WHERE id_tipo_emb = ? AND id_emp = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, tipo);
        this.getPs().setLong(2, id_emp);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            qtd_total = this.getRs().getInt("qtd_emb_total");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return qtd_total;
    }

    public void alterarQtdBaixadas(int tipo, double qtd_emb_baixadas, int id_empresa) throws SQLException {
        conectar();
        String sql = "UPDATE ctrl_emb_emp SET qtd_emb_baixada = ? WHERE id_tipo_emb = ? AND id_emp = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setDouble(1, qtd_emb_baixadas);
        this.getPs().setInt(2, tipo);
        this.getPs().setInt(3, id_empresa);

        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();

    }

    public List<CtrlEmbEmp> retornaDadosBaixa(int id_emp) throws SQLException {
        List<CtrlEmbEmp> list = new ArrayList<CtrlEmbEmp>();
        conectar();
        String sql = "SELECT CASE cee.id_tipo_emb WHEN 1 THEN 'BBAG' WHEN 3 THEN 'SACARIA' ELSE 'GRANEL' END as tipo, cee.qtd_emb_total as total, cee.qtd_emb_baixada as baixada "
                + "FROM ctrl_emb_emp cee "
                + "WHERE cee.id_tipo_emb = 1 OR"
                + "      cee.id_tipo_emb = 3 AND "
                + "      cee.id_emp = ? "
                + "GROUP BY 1,2,3;";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_emp);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            CtrlEmbEmp cee = new CtrlEmbEmp();
            cee.setStr_tipo(this.getRs().getString("tipo"));
            cee.setQtd_emb_baixada(this.getRs().getDouble("baixada"));
            cee.setQtd_emb_total(this.getRs().getDouble("total"));

            list.add(cee);
        }

        this.getRs().close();
        this.getCon().close();
        this.getPs().close();

        return list;
    }
}
