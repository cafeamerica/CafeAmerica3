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
import java.awt.AWTKeyStroke;  
import java.awt.KeyboardFocusManager;  
import java.awt.event.KeyEvent;  
import java.util.HashSet;  
import javax.swing.JPanel;  
  
public class PassaCamposComEnter {  
  
   public void passaCamposComEnter(JPanel painel){  
        // Colocando enter para pular de campo  
        HashSet conj = new HashSet(painel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));  
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));  
        painel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);  
    }  
}  
  
