/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class ManipulaArquivo {

    public static String banco = "", endereco_banco = "", porta_banco = "";
    public static String user = "";

    static String[] param = new String[3];

    //Méotodo que implementa a leitura do arquivo de config. do banco de dados
    public static String[] lerArquivo() {
        int cont = 0;
        File dir = new File("bd");
        File arq = new File(dir, "config.ini");
        if (dir.exists() || arq.exists()) {

            FileReader fr = null;
            try {
                fr = new FileReader(arq);
                BufferedReader br = new BufferedReader(fr);
                try {
                    //equanto houver mais linhas
                    while (br.ready()) {
                        if (cont == 0) {
                            banco = br.readLine();
                            param[0] = banco;
                            cont++;
                        } else if (cont == 1) {
                            endereco_banco = br.readLine();
                            param[1] = endereco_banco;
                            cont++;
                        } else if (cont == 2) {
                            porta_banco = br.readLine();
                            param[2] = porta_banco;
                            break;
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ManipulaArquivo.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ManipulaArquivo.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(ManipulaArquivo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            try {
                dir.mkdir();
                arq.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ManipulaArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return param;

    }

    //Método que implementa a escrita do arquivo de config. do banco de dados
    public void escreverArquivo() {

        File dir = new File("bd");
        File arq = new File(dir, "config.ini");

        if (dir.exists() || arq.exists()) {
            try {
                //criando o escritor de arquivos
                FileWriter fw = new FileWriter(arq);
                BufferedWriter bw = new BufferedWriter(fw);

//                String banco = txtBanco.getText().toString();
                //              String endereco = txtEndereco.getText().toString();
                //             String porta = txtPorta.getText().toString();
                bw.write(banco);
                bw.newLine();

//                bw.write(endereco);
                bw.newLine();

                //              bw.write(porta);
                bw.close();
                fw.close();

            } catch (IOException ex) {
                Logger.getLogger(ManipulaArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            escreverArquivo();

            try {
                arq.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ManipulaArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void setUsuario(String usuario) {
        int cont = 0;
        user = usuario;
        File dir = new File("bd");
        File arq = new File(dir, "config_user.ini");
        FileReader fr = null;
        if (arq.exists()) {
            try {

                //criando o escritor de arquivos
                FileWriter fw = new FileWriter(arq);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(user);

                bw.close();
                fw.close();

            } catch (IOException ex) {
                Logger.getLogger(ManipulaArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                arq.createNewFile();
                setUsuario(user);
            } catch (IOException ex) {
                Logger.getLogger(ManipulaArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static String lerArquivoUsuario() {
        String usuario = "";
        File dir = new File("bd");
        File arq = new File(dir, "config_user.ini");
        int cont = 0;

        if (dir.exists() || arq.exists()) {

            FileReader fr = null;
            try {
                fr = new FileReader(arq);
                BufferedReader br = new BufferedReader(fr);
                try {
                    //equanto houver mais linhas
                    while (br.ready()) {
                        if (cont == 0) {
                            usuario = br.readLine();
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ManipulaArquivo.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ManipulaArquivo.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(ManipulaArquivo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            try {
                dir.mkdir();
                arq.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ManipulaArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return usuario;
    }
}
