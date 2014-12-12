/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import javax.swing.JProgressBar;

/**
 *
 * @author Rafael
 */
public class BarraDeProgresso implements Runnable {

    private static JProgressBar jpb;  
    private boolean interrupted = false; 
    private static boolean valor;
      
    public BarraDeProgresso() {  
        
    }  
    public void setProgressBar(JProgressBar jpb) {  
        this.jpb = jpb;  
    }  
    public void setInterrupted (boolean interrupted) {  
        this.interrupted = interrupted;  
    }  

    public static void setValor(boolean valor) {
        BarraDeProgresso.valor = valor;
    }
    
    public static void zeraJpb(){
        jpb.setString("");
        jpb.setValue(0);
    }
    
    
      
    public void run() {  
        valor = false;
        jpb.setStringPainted(true);
        for (int i = 0; i<10000; ++i) { 
            try { Thread.sleep (100); } catch (InterruptedException ex) {}  
            jpb.setValue (i);
            jpb.setString(i+"%");
            if(valor == true){
                jpb.setString("100%");
                jpb.setValue(100);
                break;
            }
        }  
    }  
    
}
