/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entradas;

import dao.DAOModel;
import java.sql.SQLException;
import model.entradas.EmpresaGuia;

/**
 *
 * @author Rafael
 */
public class DAOEmpresaGuia extends DAOModel {

    public DAOEmpresaGuia() {

    }

    public void inserir(EmpresaGuia eg) throws SQLException {
        conectar();
        String sql = "INSERT INTO emp_guia(id_emp, id_guia, num_guia)VALUES(?,?,?)";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, eg.getId_emp());
        this.getPs().setInt(2, eg.getId_guia());
        this.getPs().setString(3, eg.getNum_guia());
        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();
    }

    public boolean consultaGuiaEmp(String num_guia, int id_emp) throws SQLException {
        boolean condicao = false;
        conectar();

        String sql = "SELECT * FROM emp_guia WHERE num_guia = ? AND id_emp = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, num_guia);
        this.getPs().setInt(2, id_emp);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            condicao = true;
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return condicao;
    }

    public int retornaIdEmp(String num_guia) throws SQLException {
        int id_emp = 0;
        conectar();
        String sql = "SELECT E.ID_EMP AS id "
                +    "FROM EMPRESAS E, EMP_GUIA EG "
                +    "WHERE E.ID_EMP  = EG.ID_EMP AND "
                +    "      EG.NUM_GUIA = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, num_guia);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            id_emp = this.getRs().getInt("id");
        }

        desconectar();

        return id_emp;
    }

}
