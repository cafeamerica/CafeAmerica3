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
import model.cadastros.Usuarios;

/**
 *
 * @author Rafael
 */
public class DAOUsuarios extends DAOModel {

    public DAOUsuarios() {

    }

    public void inserir(Usuarios u) throws SQLException {
        conectar();
        String sql = "INSERT INTO usuarios(usuario,nome_usuario,dpto_usuario, senha_usuario, super_senha, status_usuario, tipo_usuario)VALUES(?,?,?,?,?,?,?)";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, u.getUsuario());
        this.getPs().setString(2, u.getNome_usuario());
        this.getPs().setString(3, u.getDpto_usuario());
        this.getPs().setString(4, u.getSenha());
        this.getPs().setString(5, u.getSuper_senha());
        this.getPs().setInt(6, u.getStatus_usuario());
        this.getPs().setInt(7, u.getTipo_usuario());

        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();
    }

    public boolean consultaUserExiste(String usuario, String senha) throws SQLException {
        boolean condicao = false;
        conectar();

        String sql = "SELECT usuario, senha_usuario FROM usuarios WHERE usuario = ? AND senha_usuario = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, usuario);
        this.getPs().setString(2, senha);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            condicao = true;
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return condicao;
    }

    public boolean consultaUser(String usuario) throws SQLException {
        boolean condicao = false;
        conectar();

        String sql = "SELECT usuario FROM usuarios WHERE usuario = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, usuario);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            condicao = true;
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return condicao;
    }

    public int retornaIDUser(String usuario) throws SQLException {
        int id = 0;
        conectar();
        String sql = "SELECT id_usuario FROM usuarios WHERE nome_usuario = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, usuario);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            id = this.getRs().getInt("id_usuario");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return id;
    }

    public List<Usuarios> consultaNomeId() throws SQLException {
        conectar();
        List<Usuarios> usuarios = new ArrayList<Usuarios>();
        String sql = "SELECT id_usuario, nome_usuario FROM usuarios";

        this.setPs(this.getCon().prepareStatement(sql));

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            Usuarios u = new Usuarios();

            u.setId_usuario(this.getRs().getInt("id_usuario"));
            u.setNome_usuario(this.getRs().getString("nome_usuario"));

            usuarios.add(u);
        }
        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return usuarios;
    }

    public int retornaUltimaID() throws SQLException {
        int id = 0;
        conectar();
        String sql = "SELECT MAX(id_usuario) as maximo FROM usuarios";

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

    public List<Usuarios> listaIdUsuarios() throws SQLException {
        List<Usuarios> list = new ArrayList<Usuarios>();
        conectar();
        String sql = "SELECT id_usuario FROM usuarios";

        this.setPs(this.getCon().prepareStatement(sql));

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            Usuarios u = new Usuarios();

            u.setId_usuario(this.getRs().getInt("id_usuario"));

            list.add(u);
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return list;
    }

    public int retornaIDPorUsuario(String s_usuario) throws SQLException {
        int id = 0;
        conectar();
        String sql = "SELECT id_usuario FROM usuarios WHERE usuario = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, s_usuario);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            id = this.getRs().getInt("id_usuario");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return id;
    }

    public int retornaTipo(int id_usuario) throws SQLException {
        int tipo = 0;
        conectar();
        String sql = "SELECT tipo_usuario FROM usuarios WHERE id_usuario = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_usuario);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            tipo = this.getRs().getInt("tipo_usuario");
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return tipo;
    }

    public int retornaTipoUsuario(int id_usuario) throws SQLException {
        int tipo = 0;
        conectar();
        String sql = "SELECT tipo_usuario as tipo FROM usuarios WHERE id_usuario = ?";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_usuario);
        
        this.setRs(this.getPs().executeQuery());
        
        while(this.getRs().next()){
            tipo = this.getRs().getInt("tipo");
        }
        
        this.getPs().close();
        this.getRs().close();
        this.getCon().close();
        
        return tipo;
    }
    
    public String retornaNomeId(int id_usuario) throws SQLException{
        String nome = "";
        conectar();
        String sql = "SELECT nome_usuario as nome FROM usuarios WHERE id_usuario = ?";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_usuario);
        
        this.setRs(this.getPs().executeQuery());
        
        while(this.getRs().next()){
            nome = this.getRs().getString("nome");
        }
        
        this.getPs().close();
        this.getRs().close();
        this.getCon().close();
        
        return nome;
    }

}
