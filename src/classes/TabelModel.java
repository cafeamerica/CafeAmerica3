/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Rafael
 */
public class TabelModel extends javax.swing.table.DefaultTableModel {

    public TabelModel(String[] colunas, Object linhas) {

    }

    public void setColumns() {

    }

    public boolean isCellEditable(int rowIndex, int colIndwx) {
        return false;
    }
}
