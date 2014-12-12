/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.util.Iterator;

/**
 *
 * @author Rafael
 */
public class ComboBoxModel extends javax.swing.JComboBox{
    
    
    public ComboBoxModel(){
    
    }
    
    private void initComponents(){
    
    }
    
    public void montaCombo(){
        this.setPreferredSize(new java.awt.Dimension(210,24));
        this.removeAllItems();
    }
    
    public void listaDadosCombo(java.util.ArrayList dados){
        Iterator i = dados.iterator();
            while (i.hasNext()) {
                String t = String.valueOf(i.next());
                this.addItem(t);
            }
    }
}
