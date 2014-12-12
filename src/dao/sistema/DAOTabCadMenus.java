/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.sistema;

import dao.DAOModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.sistema.TabCadMenus;

/**
 *
 * @author Rafael
 */
public class DAOTabCadMenus extends DAOModel {

    public DAOTabCadMenus() {

    }

    public void inserir(TabCadMenus tcm) throws SQLException {
        conectar();
        String sql = "INSERT INTO tab_cad_menus(nome_menu, desc_menu)VALUES(?,?)";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, tcm.getNome_menu());
        this.getPs().setString(2, tcm.getDesc_menu());

        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();
    }

    public List<TabCadMenus> retornaMenus() throws SQLException {
        List<TabCadMenus> list = new ArrayList<TabCadMenus>();
        conectar();
        String sql = "SELECT nome_menu, desc_menu FROM tab_cad_menus ORDER BY desc_menu";

        this.setPs(this.getCon().prepareStatement(sql));
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            TabCadMenus tcmu = new TabCadMenus();

            tcmu.setNome_menu(this.getRs().getString("nome_menu"));
            tcmu.setDesc_menu(this.getRs().getString("desc_menu"));

            list.add(tcmu);

        }

        this.getPs().close();
        this.getCon().close();
        this.getRs().close();

        return list;
    }

    public boolean consultaNomeMenu(String nome_menu) throws SQLException {
        conectar();
        boolean condicao = false;
        String sql = "SELECT nome_menu FROM tab_cad_menus WHERE nome_menu = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, nome_menu);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            condicao = true;

        }

        this.getPs().close();
        this.getCon().close();
        this.getRs().close();
        return condicao;

    }
    
    public int retornaUltimaID() throws SQLException {
        int id = 0;
        conectar();
        String sql = "SELECT MAX(id_tab_cad_menu) as maximo FROM tab_cad_menus";

        this.setPs(this.getCon().prepareStatement(sql));

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            id = this.getRs().getInt("maximo");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return id;
    }
}
