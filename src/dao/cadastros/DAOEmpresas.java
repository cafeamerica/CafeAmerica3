/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.cadastros;

import dao.DAOModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.cadastros.Empresas;

/**
 *
 * @author Rafael
 */
public class DAOEmpresas extends DAOModel{
    
    public DAOEmpresas(){
    
    }
    
    public void inserir(Empresas emp) throws SQLException{
        
        conectar();
        
        String sql = "INSERT INTO empresas(nome_emp, razao_social_emp, cnpj_emp, ie_emp, end_emp,bairro_emp, tel1_emp, tel2_emp, responsavel_emp"
                + "cid_emp, usuario_cad_emp) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        
        this.setPs(getCon().prepareStatement(sql));
        this.getPs().setString(1, emp.getNome_emp());
        this.getPs().setString(2, emp.getRazao_social_emp());
        this.getPs().setString(3, emp.getCnpj_emp());
        this.getPs().setString(4, emp.getIe_emp());
        this.getPs().setString(5, emp.getEnd_emp());
        this.getPs().setString(6, emp.getBairro_emp());
        this.getPs().setString(7, emp.getTel1_emp());
        this.getPs().setString(8, emp.getTel2_emp());
        this.getPs().setString(9, emp.getResponsavel_emp());
        this.getPs().setInt(10, emp.getCid_emp());
        this.getPs().setString(1, emp.getUsuario_cad_emp());
        
        this.getPs().execute();
        
        this.getPs().close();
        
        this.getCon().close();

    }
    
    public ArrayList consultarEmpresas() throws SQLException{
        conectar();
        ArrayList list = new ArrayList();
        String sql = "SELECT nome_emp FROM empresas ORDER BY nome_emp";
        
        this.setPs(getCon().prepareStatement(sql));
        this.setRs(getPs().executeQuery());
        
        while(this.getRs().next()){
            String x = "";
            x = this.getRs().getString("nome_emp");
            list.add(x);
            x = "";
        }
        
        this.getPs().close();
        this.getRs().close();
        this.getCon().close();
        
        return list;
    }
    
    public int retornaIDPorNome(String nome_emp) throws SQLException{
       int id_emp = 0;
       conectar();
       
       String sql = "SELECT id_emp FROM empresas WHERE nome_emp = ?";
       
       this.setPs(this.getCon().prepareStatement(sql));
       this.getPs().setString(1, nome_emp);
       
       this.setRs(this.getPs().executeQuery());
       
       while(this.getRs().next()){
           id_emp = this.getRs().getInt("id_emp");
       }
       
       this.getPs().close();
       this.getRs().close();
       this.getCon().close();
       
       return id_emp;
    }
}
