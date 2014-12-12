/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entradas;

/**
 *
 * @author Rafael
 */
public class EmpresaGuia {
    private int id_emp_guia;
    private int id_emp;
    private int id_guia;
    private String num_guia;
    
    public EmpresaGuia(){
    
    }

    public String getNum_guia() {
        return num_guia;
    }

    public void setNum_guia(String num_guia) {
        this.num_guia = num_guia;
    }
    
    

    public int getId_emp_guia() {
        return id_emp_guia;
    }

    public void setId_emp_guia(int id_emp_guia) {
        this.id_emp_guia = id_emp_guia;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public int getId_guia() {
        return id_guia;
    }

    public void setId_guia(int id_guia) {
        this.id_guia = id_guia;
    }
    
    
    
}
