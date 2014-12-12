/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.sistema;

import bd.Conexao;
import classes.ManipulaArquivo;
import dao.DAOModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.sistema.TabCadMenus;
import model.sistema.TabCadMenusUsuario;

/**
 *
 * @author Rafael
 */
public class DAOTabCadMenusUsuario extends DAOModel {

    public DAOTabCadMenusUsuario() {

    }

    public void inserir(TabCadMenusUsuario tcmu) throws SQLException {
        conectar();
        String sql = "INSERT INTO tab_cad_menus_usuario(id_tab_cad_menu, id_usuario, status)VALUES(?,?,?)";

        this.setPs(this.getCon().prepareStatement(sql));

        this.getPs().setInt(1, tcmu.getId_tab_cad_menu());
        this.getPs().setInt(2, tcmu.getId_usuario());
        this.getPs().setInt(3, tcmu.getStatus());

        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();
    }

    public List<TabCadMenusUsuario> consultaNomeId(int id_usuario) throws SQLException {
        List<TabCadMenusUsuario> menus = new ArrayList<TabCadMenusUsuario>();
        conectar();
        String sql = "SELECT tb.id_tab_cad_menu as id, tb.desc_menu as nome, tu.status as status "
                + "FROM tab_cad_menus tb, tab_cad_menus_usuario tu, usuarios u "
                + "WHERE tb.id_tab_cad_menu = tu.id_tab_cad_menu AND "
                + "u.id_usuario = tu.id_usuario            AND "
                + "u.id_usuario = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_usuario);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            TabCadMenusUsuario tcmu = new TabCadMenusUsuario();

            tcmu.setId_tab_cad_menu(this.getRs().getInt("id"));
            tcmu.setNome_menu(this.getRs().getString("nome"));
            tcmu.setStatus(this.getRs().getInt("status"));

            menus.add(tcmu);
        }

        this.getPs().close();
        this.getCon().close();
        this.getRs().close();
        return menus;
    }

    public void alteraStatusCadMenuUsuario(int id_usuario, int id_tab_cad_menu_usuario, int status) throws SQLException {
        conectar();
        String sql = "UPDATE tab_cad_menus_usuario SET status = ? WHERE id_usuario = ? AND id_tab_cad_menu = ? ";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, status);
        this.getPs().setInt(2, id_usuario);
        this.getPs().setInt(3, id_tab_cad_menu_usuario);

        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();

    }

    public int[] retornaMenusUsuario(int id_usuario) throws SQLException {
        int[] qtd_menus = new int[16];
        int cont = 0;
        conectar();
        String sql = "SELECT status FROM tab_cad_menus_usuario WHERE id_usuario = ? ORDER BY id_tab_cad_menu_usuario";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_usuario);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            qtd_menus[cont] = this.getRs().getInt("status");
            cont++;
        }

        this.getPs().close();
        this.getCon().close();
        this.getRs().close();

        return qtd_menus;
    }

    public List<TabCadMenus> retornaTodosMenus() throws SQLException {
        List<TabCadMenus> list = new ArrayList<TabCadMenus>();
        conectar();

        String sql = "SELECT id_tab_cad_menu FROM tab_cad_menus";

        this.setPs(this.getCon().prepareStatement(sql));
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            TabCadMenus tcm = new TabCadMenus();

            tcm.setId_tab_cad_menu(this.getRs().getInt("id_tab_cad_menu"));

            list.add(tcm);
        }
        this.getPs().close();
        this.getCon().close();
        this.getRs().close();

        return list;
    }

    public List<TabCadMenus> retornaMenus() throws SQLException {
        List<TabCadMenus> list = new ArrayList<TabCadMenus>();
        conectar();
        String sql = "SELECT nome_menu, desc_menu FROM tab_cad_menus";

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

    public List<TabCadMenusUsuario> retornaPermissoes(int id_usuario) throws SQLException {
        List<TabCadMenusUsuario> list = new ArrayList<TabCadMenusUsuario>();
        conectar();

        String sql = "SELECT tb.nome_menu as nome, tu.status as status "
                + "FROM tab_cad_menus tb, tab_cad_menus_usuario tu "
                + "WHERE tb.id_tab_cad_menu = tu.id_tab_cad_menu AND "
                + "      tu.id_usuario = ? ";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_usuario);
        this.setRs(this.getPs().executeQuery());

       
        while (this.getRs().next()) {
           
            TabCadMenusUsuario tcmu = new TabCadMenusUsuario();

            tcmu.setNome_menu(this.getRs().getString("nome"));
            tcmu.setStatus(this.getRs().getInt("status"));

        

            list.add(tcmu);
        }
 
        
        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return list;
    }

}
