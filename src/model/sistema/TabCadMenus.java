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
public class TabCadMenus {
    private int id_tab_cad_menu;
    private String nome_menu;
    private String desc_menu;
    
    public TabCadMenus(){
    
    }

    public TabCadMenus(int id_tab_cad_menu, String nome_menu, String desc_menu) {
        this.id_tab_cad_menu = id_tab_cad_menu;
        this.nome_menu = nome_menu;
        this.desc_menu = desc_menu;
    }

    public int getId_tab_cad_menu() {
        return id_tab_cad_menu;
    }

    public void setId_tab_cad_menu(int id_tab_cad_menu) {
        this.id_tab_cad_menu = id_tab_cad_menu;
    }

    public String getNome_menu() {
        return nome_menu;
    }

    public void setNome_menu(String nome_menu) {
        this.nome_menu = nome_menu;
    }

    public String getDesc_menu() {
        return desc_menu;
    }

    public void setDesc_menu(String desc_menu) {
        this.desc_menu = desc_menu;
    }
    
    
}
