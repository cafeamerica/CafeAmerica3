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
import model.entradas.ProducaoGuia;

/**
 *
 * @author Rafael
 */
public class DAOProducaoGuia extends DAOModel {

    public DAOProducaoGuia() {

    }

    public void inserir(ProducaoGuia pg) throws SQLException {
        conectar();
        String sql = "INSERT INTO producao_guia(id_guia, tipo_cafe_pdc, peso_pdc, qtd_sacas_pdc, saldo_pdc, dt_producao_pdc, peso_bruto)VALUES(?,?,?,?,?,?,?)";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, pg.getId_guia());
        this.getPs().setInt(2, pg.getTipo_cafe_pdc());
        this.getPs().setDouble(3, pg.getPeso_pdc());
        this.getPs().setDouble(4, pg.getQtd_sacas_pdc());
        this.getPs().setDouble(5, pg.getSaldo_pdc());
        this.getPs().setDate(6, pg.getDt_producao_pdc());
        this.getPs().setDouble(7, pg.getPeso_bruto());

        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();
    }

    public int retornaIdPorTipoGuia(int id_tipo, int id_guia) throws SQLException {
        int id = 0;
        conectar();
        String sql = "SELECT id_pdc_guia FROM producao_guia WHERE tipo_cafe_pdc = ? AND id_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_tipo);
        this.getPs().setInt(2, id_guia);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            id = this.getRs().getInt("id_pdc_guia");
        }

        this.getRs().close();
        this.getPs().close();
        this.getCon().close();

        return id;
    }

    public boolean retornaExiste(int id_tipo, int id_guia) throws SQLException {
        boolean condicao = false;
        conectar();
        String sql = "SELECT * FROM producao_guia WHERE tipo_cafe_pdc = ? AND id_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_tipo);
        this.getPs().setInt(2, id_guia);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            condicao = true;
        }

        this.getRs().close();
        this.getPs().close();
        this.getCon().close();

        return condicao;
    }

    public void alteraPeso(int id_pdc_guia, double peso) throws SQLException {
        conectar();
        String sql = "UPDATE producao_guia SET peso_pdc = ? WHERE id_pdc_guia = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setDouble(1, peso);
        this.getPs().setInt(2, id_pdc_guia);

        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();
    }

    public void alteraPesoBruto(int id_pdc_guia, double peso_bruto) throws SQLException {
        conectar();
        String sql = "UPDATE producao_guia SET peso_bruto = ? WHERE id_pdc_guia = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setDouble(1, peso_bruto);
        this.getPs().setInt(2, id_pdc_guia);

        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();
    }

    public void alteraSaldo(int id_pdc_guia, double saldo) throws SQLException {
        conectar();
        String sql = "UPDATE producao_guia SET saldo_pdc = ? WHERE id_pdc_guia = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setDouble(1, saldo);
        this.getPs().setInt(2, id_pdc_guia);

        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();
    }

    public double retornaPeso(int id_pdc_guia) throws SQLException {
        double peso = 0;
        conectar();
        String sql = "SELECT peso_pdc FROM producao_guia WHERE id_pdc_guia = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_pdc_guia);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            peso = this.getRs().getDouble("peso_pdc");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return peso;
    }

    public double retornaPesoBruto(int id_pdc_guia) throws SQLException {
        double peso = 0;
        conectar();
        String sql = "SELECT peso_bruto FROM producao_guia WHERE id_pdc_guia = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_pdc_guia);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            peso = this.getRs().getDouble("peso_bruto");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return peso;
    }

    public double retornaSaldo(int id_pdc_guia) throws SQLException {
        double saldo = 0;
        conectar();
        String sql = "SELECT saldo_pdc FROM producao_guia WHERE id_pdc_guia = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_pdc_guia);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            saldo = this.getRs().getDouble("saldo_pdc");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return saldo;
    }

    public double retornaQtdSacas(int id_pdc_guia) throws SQLException {
        double qtd_sacas = 0;
        conectar();
        String sql = "SELECT qtd_sacas_pdc FROM producao_guia WHERE id_pdc_guia = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_pdc_guia);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            qtd_sacas = this.getRs().getDouble("qtd_sacas_pdc");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return qtd_sacas;
    }

    public void alteraQtdSacas(int id_pdc_guia, double qtd) throws SQLException {
        conectar();
        String sql = "UPDATE producao_guia SET qtd_sacas_pdc = ? WHERE id_pdc_guia = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setDouble(1, qtd);
        this.getPs().setInt(2, id_pdc_guia);

        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();
    }

    public List<ProducaoGuia> retornaDados(int id_guia) throws SQLException {
        List<ProducaoGuia> list = new ArrayList<ProducaoGuia>();
        conectar();
        String sql = " SELECT s.desc_serv as desc , pg.peso_pdc as peso, pg.saldo_pdc  as saldo, pg.peso_bruto as bruto"
                + " FROM producao_guia pg, servicos s, guias g "
                + " WHERE pg.id_guia = g.id_guia AND "
                + "       s.id_serv = pg.tipo_cafe_pdc AND "
                + "       s.desc_serv NOT LIKE '%REBENEFICIO DE CAFE CONTABIL%' AND"
                + "       g.id_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            ProducaoGuia pg = new ProducaoGuia();
            pg.setDesc_serv(this.getRs().getString("desc"));
            pg.setPeso_pdc(this.getRs().getDouble("peso"));
            pg.setSaldo_pdc(this.getRs().getDouble("saldo"));
            pg.setPeso_bruto(this.getRs().getDouble("bruto"));
            list.add(pg);
        }

        this.desconectar();

        return list;
    }

    public List<ProducaoGuia> retornaPrevia(int id_guia) throws SQLException {
        List<ProducaoGuia> list = new ArrayList<ProducaoGuia>();
        conectar();
        String sql = "SELECT s.desc_serv as serv, pg.saldo_pdc as saldo "
                + "FROM producao_guia pg, servicos s, guias g "
                + "WHERE pg.tipo_cafe_pdc = s.id_serv AND "
                + "      pg.id_guia = g.id_guia AND "
                + "      pg.saldo_pdc > 0 AND "
                + "      g.id_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            ProducaoGuia pg = new ProducaoGuia();
            pg.setDesc_serv(this.getRs().getString("serv"));
            pg.setSaldo_pdc(this.getRs().getDouble("saldo"));

            list.add(pg);
        }

        desconectar();
        return list;
    }

}
