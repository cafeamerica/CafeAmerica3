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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entradas.Guias;

/**
 *
 * @author Rafael
 */
public class DAOGuias extends DAOModel {

    public DAOGuias() {

    }

    public void inserir(Guias g) throws SQLException {
        conectar();
        String sql = "INSERT INTO guias(num_guia,nosso_num_guia, dt_entrada_guia, vlr_unit_guia,"
                + "                     vlr_total_guia, peso_guia, qtd_sacas_guia, referencia_guia,"
                + "                     status_guia, usuario_cad_guia, vlr_total_ini_guia, obs)VALUES(?,?,?,?,?,?,?,?,?,?,?, ?)";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, g.getNum_guia());
        this.getPs().setString(2, g.getNosso_num_guia());
        this.getPs().setDate(3, g.getDt_entrada_guia());
        this.getPs().setDouble(4, g.getVlr_unit_guia());
        this.getPs().setDouble(5, g.getVlr_total_guia());
        this.getPs().setDouble(6, g.getPeso_guia());
        this.getPs().setDouble(7, g.getQtd_sacas_guia());
        this.getPs().setInt(8, g.getReferencia_guia());
        this.getPs().setInt(9, g.getStatus_guia());
        this.getPs().setString(10, g.getUsuario_cad_guia());
        this.getPs().setDouble(11, g.getVlr_total_ini_guia());
        this.getPs().setString(12, g.getObs());
        this.getPs().execute();

        this.getPs().close();

        this.getCon().close();

    }

    public int retornaUltimaID() throws SQLException {
        int id_guia = 0;
        conectar();

        String sql = "SELECT max(id_guia) as maximo FROM guias";

        this.setPs(this.getCon().prepareStatement(sql));
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            id_guia = this.getRs().getInt("maximo");
        }

        id_guia++;

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return id_guia;
    }

    public String retornaNossoNum(String num_guia, int id_emp) throws SQLException {
        String s_nosso_num = "";
        conectar();
        String sql = "SELECT g.nosso_num_guia as nosso_num "
                + "FROM guias g, emp_guia eg "
                + "WHERE g.id_guia = eg.id_guia AND "
                + "      eg.id_emp = ? AND"
                + "      g.num_guia = ? ";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_emp);
        this.getPs().setString(2, num_guia);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            s_nosso_num = this.getRs().getString("nosso_num");
        }

        this.getRs().close();
        this.getPs().close();
        this.getCon().close();

        return s_nosso_num;
    }

    public int retornaIdGuiaEmp(String num_guia, int id_emp) throws SQLException {
        int id = 0;
        conectar();
        String sql = "SELECT g.id_guia as id "
                + "FROM guias g, emp_guia eg "
                + "WHERE g.id_guia = eg.id_guia AND "
                + "      eg.id_emp = ? AND"
                + "      g.num_guia = ? ";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_emp);
        this.getPs().setString(2, num_guia);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            id = this.getRs().getInt("id");
        }

        this.getRs().close();
        this.getPs().close();
        this.getCon().close();

        return id;
    }

    public List<Guias> consultaDadosGuiaPeriodo(java.sql.Date dt_ini, java.sql.Date dt_fim, int empresa) throws SQLException {
        conectar();
        String sql = "SELECT g.nosso_num_guia as nosso_num, g.dt_entrada_guia as dt, e.nome_emp as emp, g.num_guia as num_guia, g.peso_guia as peso, g.qtd_sacas_guia as qtd_sacas, g.vlr_unit_guia as vlr_unit, g.vlr_total_guia as vlr_total "
                + " FROM guias g, empresas e, emp_guia eg "
                + " WHERE g.id_guia = eg.id_guia AND "
                + "       eg.id_emp = e.id_emp AND "
                + "       dt_entrada_guia BETWEEN ? AND ?"
                + "ORDER BY dt";

        List<Guias> guias = new ArrayList<Guias>();

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setDate(1, dt_ini);
        this.getPs().setDate(2, dt_fim);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            Guias g = new Guias();

            g.setDt_entrada_guia(this.getRs().getDate("dt"));
            g.setNum_guia(this.getRs().getString("num_guia"));
            g.setStr_emp(this.getRs().getString("emp"));
            g.setPeso_guia(this.getRs().getDouble("peso"));
            g.setQtd_sacas_guia(this.getRs().getInt("qtd_sacas"));
            g.setVlr_unit_guia(this.getRs().getDouble("vlr_unit"));
            g.setVlr_total_guia(this.getRs().getDouble("vlr_total"));
            g.setNosso_num_guia(this.getRs().getString("nosso_num"));
            guias.add(g);
        }

        this.getRs().close();
        this.getPs().close();
        this.getCon().close();
        return guias;

    }

    public void alteraVlrTotal(double vlr, int id_guia) throws SQLException {
        conectar();

        String sql = "UPDATE guias SET vlr_total_guia = ? WHERE id_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setDouble(1, vlr);
        this.getPs().setInt(2, id_guia);

        this.getPs().execute();

        this.getPs().close();

        this.getCon().close();

    }
    
    public void alterarQtdSacas(double qtd, int id_guia) throws SQLException{
        conectar();
        String sql = "UPDATE guias set qtd_sacas_guia = ? WHERE id_guia = ?";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setDouble(1, qtd);
        this.getPs().setInt(2, id_guia);
        
        desconectar();
    }

    public double retornaVlrTotal(int id_guia) throws SQLException {
        conectar();
        double vlr = 0;

        String sql = "SELECT vlr_total_guia as vlr FROM guias WHERE id_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            vlr = this.getRs().getDouble("vlr");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return vlr;
    }

    public List<Guias> consultaNumGuiaNossoNum() throws SQLException {
        conectar();
        List<Guias> list = new ArrayList<Guias>();

        String sql = "SELECT e.nome_emp as nome_emp, g.num_guia as num_guia , g.nosso_num_guia as nosso_num FROM guias g, emp_guia eg, empresas e WHERE status_guia = 0 AND eg.id_guia = g.id_guia AND eg.id_emp = e.id_emp";

        this.setPs(this.getCon().prepareStatement(sql));

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            Guias g = new Guias();

            g.setNum_guia(this.getRs().getString("num_guia"));
            g.setNosso_num_guia(this.getRs().getString("nosso_num"));
            g.setStr_emp(this.getRs().getString("nome_emp"));
            list.add(g);
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return list;
    }

    public boolean consultaGuiaEmpNossoNum(String nosso_num) throws SQLException {
        boolean condicao = false;
        conectar();

        String sql = " SELECT * "
                + " FROM emp_guia eg, guias g, empresas e "
                + " WHERE g.id_guia = eg.id_guia AND "
                + "       e.id_emp = eg.id_emp AND "
                + "       g.nosso_num_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, nosso_num);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            condicao = true;
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return condicao;
    }

    public int retornaIdNossoNum(String nosso_num) throws SQLException {
        int id = 0;
        conectar();
        String sql = "SELECT id_guia FROM guias WHERE nosso_num_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, nosso_num);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            id = this.getRs().getInt("id_guia");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return id;
    }

    public String retornaNumGuia(int id_guia) throws SQLException {
        String num_guia = "";
        conectar();
        String sql = "SELECT num_guia FROM guias WHERE id_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            num_guia = this.getRs().getString("num_guia");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return num_guia;
    }

    public String retornaNossoNumId(int id_guia) throws SQLException {
        String sql = "SELECT nosso_num_guia FROM guias WHERE id_guia = ?";
        String nosso_num = "";
        conectar();

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            nosso_num = this.getRs().getString("nosso_num_guia");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return nosso_num;
    }

    public double retornaQtdSacas(int id_guia) throws SQLException {
        double d_sacas = 0;
        conectar();
        String sql = "SELECT qtd_sacas_guia as sacas FROM guias WHERE id_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            d_sacas = this.getRs().getDouble("sacas");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return d_sacas;
    }

    public double retornaPeso(int id_guia) throws SQLException {
        double peso = 0;
        conectar();
        String sql = "SELECT peso_guia FROM guias WHERE id_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            peso = this.getRs().getDouble("peso_guia");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return peso;
    }

    public Guias retornaDados(int id_guia) throws SQLException {
        Guias g = new Guias();
        conectar();
        String sql = "SELECT num_guia, nosso_num_guia, dt_entrada_guia, qtd_sacas_guia, peso_guia, CASE status_guia WHEN '0' THEN 'ABERTA' WHEN '1' THEN 'FECHADA' END as status_guia, usuario_cad_guia FROM guias WHERE id_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            g.setNum_guia(this.getRs().getString("num_guia"));
            g.setNosso_num_guia(this.getRs().getString("nosso_num_guia"));
            g.setDt_entrada_guia(this.getRs().getDate("dt_entrada_guia"));
            g.setQtd_sacas_guia(this.getRs().getDouble("qtd_sacas_guia"));
            g.setPeso_guia(this.getRs().getDouble("peso_guia"));
            g.setStr_status_guia(this.getRs().getString("status_guia"));
            g.setUsuario_cad_guia(this.getRs().getString("usuario_cad_guia"));
        }

        desconectar();

        return g;
    }

    public String retornaNossoNumGuia(String num_guia) throws SQLException {
        String nosso_num = "";
        conectar();
        String sql = "SELECT nosso_num_guia as nosso FROM guias WHERE num_guia = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, num_guia);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            nosso_num = this.getRs().getString("nosso");
        }

        desconectar();

        return nosso_num;
    }
}
