/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael
 */
public class ManipulaJTables {
    
    public ManipulaJTables(){
    
    }
    
    public static void removeLinhasBrancas(JTable tabela){
        
        //tabela não ficar com linhas em branco
        DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
        int i = 0;
        while (i < dtm.getRowCount()) {
            if (dtm.getValueAt(i, 0) == null) {
                dtm.removeRow(i);
                continue;
            }
            i++;
        }
    }
}
