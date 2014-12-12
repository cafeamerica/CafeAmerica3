/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.guias.notasfiscais;

import classes.ManipulaDatas;
import dao.entradas.DAONfeEnt;
import dao.entradas.DAOViagemEnt;
import dao.saidas.DAONfeSai;
import dao.saidas.DAOViagemSai;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.entradas.ViagemEnt;
import model.saidas.ViagemSai;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PGuiasNotasFiscaisDados extends javax.swing.JPanel {

    public PGuiasNotasFiscaisDados() {
        initComponentes();
    }

    private void initComponentes() {
        //configurações jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar NFe", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel principal
        panelConsultar = new javax.swing.JPanel();
        panelConsultar.setLayout(new MigLayout());
        panelConsultar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar Nº", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));

        panelDados = new javax.swing.JPanel();
        panelDados.setLayout(new MigLayout());
        panelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados NFe", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel consultar
        lblNumNota = new javax.swing.JLabel("Número da nota:");
        lblTipoNota = new javax.swing.JLabel("Tipo:");
        txtNumNota = new javax.swing.JTextField(8);
        btConsultar = new javax.swing.JButton("Consultar");
        radioTipoEnt = new javax.swing.JRadioButton("Entrada");
        radioTipoSai = new javax.swing.JRadioButton("Saída");
        grupoBtsTipo = new javax.swing.ButtonGroup();
        //fim

        //configurações componentes jpanel consultar
        grupoBtsTipo.add(radioTipoEnt);
        grupoBtsTipo.add(radioTipoSai);
        radioTipoEnt.setSelected(true);
        //fim

        //adicionando componentes jpanel consultar
        panelConsultar.add(lblNumNota, "split 2");
        panelConsultar.add(txtNumNota, "wrap");
        panelConsultar.add(lblTipoNota, "split 2");
        panelConsultar.add(radioTipoEnt, "wrap");
        panelConsultar.add(radioTipoSai, "split 2, gapx 32");
        panelConsultar.add(btConsultar);
        //fim

        //inicializando componentes jpanel dados
        lblData = new javax.swing.JLabel("Data:");
        lblDoc = new javax.swing.JLabel("Documento:");
        lblPesoReal = new javax.swing.JLabel("Peso real:");
        lblTipoCafe = new javax.swing.JLabel("Tipo café:");
        lblPesoFiscal = new javax.swing.JLabel("Peso fiscal:");
        lblVolFiscal = new javax.swing.JLabel("Volume fiscal:");
        lblVolReal = new javax.swing.JLabel("Volume real:");
        txtData = new javax.swing.JTextField(6);
        txtDoc = new javax.swing.JTextField(10);
        txtPesoFiscal = new javax.swing.JTextField(8);
        txtPesoReal = new javax.swing.JTextField(8);
        txtVolFiscal = new javax.swing.JTextField(6);
        txtVolReal = new javax.swing.JTextField(6);
        txtTipoCafe = new javax.swing.JTextField(12);
        btFechar = new javax.swing.JButton("Fechar consulta");
        lblNumGuia = new javax.swing.JLabel("Nº Guia:");
        txtNumGuia = new javax.swing.JTextField(8);
        //fim

        //configurações componentes jpanel dados
        txtData.setEditable(false);
        txtDoc.setEditable(false);
        txtPesoFiscal.setEditable(false);
        txtPesoReal.setEditable(false);
        txtTipoCafe.setEditable(false);
        txtVolFiscal.setEditable(false);
        txtVolReal.setEditable(false);
        txtNumGuia.setEditable(false);
        //fim

        //adicionando componentes jpanel dados
        panelDados.add(lblNumGuia,"split 2, gapx 36");
        panelDados.add(txtNumGuia,"wrap");
        panelDados.add(lblData, "split 6, gapx 50");
        panelDados.add(txtData);
        panelDados.add(lblDoc);
        panelDados.add(txtDoc);
        panelDados.add(lblTipoCafe);
        panelDados.add(txtTipoCafe, "wrap");
        panelDados.add(lblVolReal, "split 4, gapx 9");
        panelDados.add(txtVolReal);
        panelDados.add(lblPesoReal);
        panelDados.add(txtPesoReal, "wrap");
        panelDados.add(lblVolFiscal, "split 4");
        panelDados.add(txtVolFiscal);
        panelDados.add(lblPesoFiscal);
        panelDados.add(txtPesoFiscal, "wrap");
        panelDados.add(btFechar, "gapx 220");
        //fim

        //adicionando componentes jpanel principal
        this.add(panelConsultar, "wrap");
        this.add(panelDados);
        //fim

        //eventos bts
        btFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btFecharActionPerformed(e);
            }
        });

        btConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btConsultarActionPerformed(e);
            }
        });
        //fim

        panelDados.setVisible(false);

    }

    private void limpaCampos() {
        txtData.setText("");
        txtDoc.setText("");
        txtNumNota.setText("");
        txtPesoFiscal.setText("");
        txtPesoReal.setText("");
        txtTipoCafe.setText("");
        txtVolFiscal.setText("");
        txtVolReal.setText("");
        txtNumGuia.setText("");
        radioTipoEnt.setSelected(true);
        panelDados.setVisible(false);
    }

    private void listaDados(String num_nota, int tipo_nota) {
        panelDados.setVisible(true);
        ViagemEnt ve = new ViagemEnt();
        DAOViagemEnt daove = new DAOViagemEnt();
        ViagemSai vs = new ViagemSai();
        DAOViagemSai daovs = new DAOViagemSai();
        DecimalFormat df = new DecimalFormat("###,###.###");
        try {
            if (tipo_nota == 1) {
                ve = daove.retornaDadosNfe(num_nota);
                txtData.setText(ManipulaDatas.converteSqlString(ve.getDt_ent()));
                txtDoc.setText(ve.getDoc_viagem_ent());
                txtPesoFiscal.setText("" + df.format(ve.getPeso_fiscal()));
                txtPesoReal.setText("" + df.format(ve.getPeso_ent()));
                txtTipoCafe.setText(ve.getTipo_cafe_ent());
                txtVolFiscal.setText("" + df.format(ve.getVol_fiscal()));
                txtVolReal.setText("" + df.format(ve.getQtd_sacas_ent()));
                txtNumGuia.setText(ve.getNum_guia());
                
            }else{
                vs = daovs.retornaDadosNfe(num_nota);
                txtData.setText(ManipulaDatas.converteSqlString(vs.getDt_sai()));
                txtDoc.setText(vs.getDoc_viagem_sai());
                txtPesoFiscal.setText("" + df.format(vs.getPeso_fiscal()));
                txtPesoReal.setText("" + df.format(vs.getPeso_sai()));
                txtTipoCafe.setText(vs.getStr_tipo_cafe());
                txtVolFiscal.setText("" + df.format(vs.getVol_fiscal()));
                txtVolReal.setText("" + df.format(vs.getQtd_sacas_sai()));
                txtNumGuia.setText(vs.getNum_guia());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasNotasFiscaisDados.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void btFecharActionPerformed(java.awt.event.ActionEvent evet) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente fechar esta consulta?", "Fechar consulta", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            limpaCampos();
        }
    }

    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_num_nota = txtNumNota.getText().toString().trim();
        int tipo_nota = -1;

        if (s_num_nota.isEmpty() || s_num_nota.equals(null)) {
            JOptionPane.showMessageDialog(null, "Informe um número de NFe!");
        } else {
            if (radioTipoEnt.isSelected() == true) {
                tipo_nota = 1;
            } else {
                tipo_nota = 2;
            }
            try {
                DAONfeEnt daone = new DAONfeEnt();
                DAONfeSai daons = new DAONfeSai();

                if (tipo_nota == 1) {
                    if (daone.consultaNumNota(s_num_nota) == true) {
                        listaDados(s_num_nota, tipo_nota);
                    } else {
                        JOptionPane.showMessageDialog(null, "A NFe de entrada " + s_num_nota + " não existe no sistema!");
                    }
                } else {
                    if (daons.consultaNumNota(s_num_nota) == true) {
                        listaDados(s_num_nota, tipo_nota);
                    } else {
                        JOptionPane.showMessageDialog(null, "A NFe de saída " + s_num_nota + " não existe no sistema!");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(PGuiasNotasFiscaisDados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private javax.swing.JLabel lblNumNota;
    private javax.swing.JLabel lblTipoNota;
    private javax.swing.JTextField txtNumNota;
    private javax.swing.JButton btConsultar;
    private javax.swing.JPanel panelConsultar;
    private javax.swing.JRadioButton radioTipoEnt;
    private javax.swing.JRadioButton radioTipoSai;
    private javax.swing.ButtonGroup grupoBtsTipo;
    private javax.swing.JPanel panelDados;
    private javax.swing.JLabel lblData;
    private javax.swing.JTextField txtData;
    private javax.swing.JLabel lblDoc;
    private javax.swing.JTextField txtDoc;
    private javax.swing.JLabel lblTipoCafe;
    private javax.swing.JTextField txtTipoCafe;
    private javax.swing.JLabel lblVolReal;
    private javax.swing.JTextField txtVolReal;
    private javax.swing.JLabel lblPesoReal;
    private javax.swing.JTextField txtPesoReal;
    private javax.swing.JLabel lblVolFiscal;
    private javax.swing.JTextField txtVolFiscal;
    private javax.swing.JLabel lblPesoFiscal;
    private javax.swing.JTextField txtPesoFiscal;
    private javax.swing.JLabel lblNumGuia;
    private javax.swing.JTextField txtNumGuia;
    private javax.swing.JButton btFechar;
}
