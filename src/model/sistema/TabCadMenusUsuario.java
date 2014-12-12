/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.sistema;

/**
 *
 * @author Rafael
 */
public class TabCadMenusUsuario {
    private int id_tab_cad_menu_usuario;
    private int id_tab_cad_menu;
    private int id_usuario;
    private String nome_menu;
    private int status;
    
    
    public TabCadMenusUsuario(){
    
    }

    public TabCadMenusUsuario(int id_tab_cad_menu_usuario, int id_tab_cad_menu, int id_usuario, int status) {
        this.id_tab_cad_menu_usuario = id_tab_cad_menu_usuario;
        this.id_tab_cad_menu = id_tab_cad_menu;
        this.id_usuario = id_usuario;
        this.status = status;
    }

    public String getNome_menu() {
        return nome_menu;
    }

    public void setNome_menu(String nome_menu) {
        this.nome_menu = nome_menu;
    }
    
    

    public int getId_tab_cad_menu_usuario() {
        return id_tab_cad_menu_usuario;
    }

    public void setId_tab_cad_menu_usuario(int id_tab_cad_menu_usuario) {
        this.id_tab_cad_menu_usuario = id_tab_cad_menu_usuario;
    }

    public int getId_tab_cad_menu() {
        return id_tab_cad_menu;
    }

    public void setId_tab_cad_menu(int id_tab_cad_menu) {
        this.id_tab_cad_menu = id_tab_cad_menu;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
