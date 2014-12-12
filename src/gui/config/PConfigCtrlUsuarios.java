/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.config;

import classes.ManipulaJTables;
import dao.cadastros.DAOUsuarios;
import dao.sistema.DAOTabCadMenusUsuario;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.cadastros.Usuarios;
import model.sistema.TabCadMenusUsuario;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PConfigCtrlUsuarios extends javax.swing.JPanel{
    
    
    public PConfigCtrlUsuarios(){
        initComponents();
    }
    
    private void initComponents(){
    
     //inicializando componentes do panel
        panelPermissoes = new javax.swing.JPanel();
        btSelecionarUsuario = new javax.swing.JButton("Selecionar");
        btAlternarPermissao = new javax.swing.JButton("Alternar status");
        scrollTUsuarios = new javax.swing.JScrollPane();
        lblUsuario = new javax.swing.JLabel("Usuário:");
        txtUsuario = new javax.swing.JTextField(20);
        //fim

        //configurações da tabela com dados do usuario
        //Tabela config
        String[] columnNames = {"ID", "Nome"};
        Object[][] data = {};
        modelUsuarios = new javax.swing.table.DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabelaUsuarios = new javax.swing.JTable(modelUsuarios);
        tabelaUsuarios.setPreferredScrollableViewportSize(new java.awt.Dimension(260, 200));
        tabelaUsuarios.getColumnModel().getColumn(0).setMaxWidth(60);
        tabelaUsuarios.getColumnModel().getColumn(0).setMinWidth(60);
        tabelaUsuarios.getColumnModel().getColumn(1).setMaxWidth(200);
        tabelaUsuarios.getColumnModel().getColumn(1).setMinWidth(200);

        //alinhando os valores das celulas da Tabela à direita
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);
        tabelaUsuarios.getColumnModel().getColumn(0).setCellRenderer(r);
        scrollTUsuarios = new javax.swing.JScrollPane(tabelaUsuarios);
        //fim

        //configurações da tabela com dados das permissões
        //Tabela config
        String[] columnNamess = {"ID", "Nome", "Status"};
        Object[][] dataa = {};
        modelPermissoes = new javax.swing.table.DefaultTableModel(dataa, columnNamess) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabelaPermissoes = new javax.swing.JTable(modelPermissoes);
        tabelaPermissoes.setPreferredScrollableViewportSize(new java.awt.Dimension(360, 300));
        tabelaPermissoes.getColumnModel().getColumn(0).setMaxWidth(60);
        tabelaPermissoes.getColumnModel().getColumn(0).setMinWidth(60);
        tabelaPermissoes.getColumnModel().getColumn(1).setMaxWidth(200);
        tabelaPermissoes.getColumnModel().getColumn(1).setMinWidth(200);
        tabelaPermissoes.getColumnModel().getColumn(2).setMaxWidth(100);
        tabelaPermissoes.getColumnModel().getColumn(2).setMinWidth(100);

        //alinhando os valores das celulas da Tabela à direita
        r.setHorizontalAlignment(SwingConstants.CENTER);
        tabelaPermissoes.getColumnModel().getColumn(0).setCellRenderer(r);
        tabelaPermissoes.getColumnModel().getColumn(2).setCellRenderer(r);
        //fim

        //configurações do scroll permissoes
        scrollTPermissoes = new javax.swing.JScrollPane(tabelaPermissoes);
        //fim

        //configurações panel Permissoes
        panelPermissoes.setLayout(new MigLayout());
        panelPermissoes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuários:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelPermissoes.add(lblUsuario, "split 2");
        panelPermissoes.add(txtUsuario);
       // panelPermissoes.add(scrollTPermissoes);
        //fim

        //configurações do panel principal
        this.setLayout(new MigLayout());
        this.add(scrollTUsuarios, "split 2");
        this.add(btSelecionarUsuario, "top, wrap");
        this.add(panelPermissoes, "wrap");
        this.add(scrollTPermissoes);
        this.add(btAlternarPermissao);
        //fim

        //evento de botoes
        btSelecionarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btSelectionUsuarioActionPerformed(e);
            }
        });
        
        btAlternarPermissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btAlternarStatusActionPerformed(e);
            }
        });
        //fim

        listaDadosUsuarios();

    }

    private void btSelectionUsuarioActionPerformed(java.awt.event.ActionEvent evt) {
        int li = tabelaUsuarios.getSelectedRow();

        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            String s_nome_usuario = tabelaUsuarios.getValueAt(li, 1).toString();
            int i_id_usuario = Integer.parseInt(tabelaUsuarios.getValueAt(li,0).toString());
            txtUsuario.setText(s_nome_usuario);
            txtUsuario.setEditable(false);
            listaDadosPermissoes(i_id_usuario);
        }
    }
   
    private void btAlternarStatusActionPerformed(java.awt.event.ActionEvent evt){
        int li = tabelaPermissoes.getSelectedRow();
        int id_tab_cad_menu_usuario = Integer.parseInt(tabelaPermissoes.getValueAt(li,0).toString());
        
        if(li == -1){
            JOptionPane.showMessageDialog(null,"Selecione um registro!");
        }else{
            String s_status = tabelaPermissoes.getValueAt(li,2).toString();
            int i_status = 0;
            
            if(s_status.equals("Bloqueado")){
                i_status = 1;
            }else if(s_status.equals("Permitido")){
                i_status = 0;
            }else{
                i_status = 0;
            }
            
            try {
               
                daotcmu.alteraStatusCadMenuUsuario(id_usuarioo, id_tab_cad_menu_usuario, i_status);
                listaDadosPermissoes(id_usuarioo);
            } catch (SQLException ex) {
                Logger.getLogger(PConfigCtrlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    private void listaDadosUsuarios() {
        DAOUsuarios daou = new DAOUsuarios();
        Usuarios u = new Usuarios();
        List<Usuarios> dados;

        try {
            dados = daou.consultaNomeId();

            if (dados.size() == 0) {
                JOptionPane.showMessageDialog(null, "Não há usuários cadastrados!");
            } else {
                String[] linha = {null, null};
                for (int i = 0; i < dados.size(); i++) {
                    DefaultTableModel dtm = (DefaultTableModel) tabelaUsuarios.getModel();
                    dtm.addRow(linha);
                    tabelaUsuarios.setModel(dtm);
                    tabelaUsuarios.setValueAt(dados.get(i).getId_usuario(), i, 0);
                    tabelaUsuarios.setValueAt(dados.get(i).getNome_usuario(), i, 1);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PConfigCtrlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listaDadosPermissoes(int id_usuario) {
        List<TabCadMenusUsuario> dados;
        try {
            dados = daotcmu.consultaNomeId(id_usuario);
            if (dados.size() == 0) {

            } else {
                id_usuarioo = id_usuario;
                String[] linha = {null, null, null};
                for (int i = 0; i < dados.size(); i++) {
                    DefaultTableModel dtm = (DefaultTableModel)tabelaPermissoes.getModel();
                    dtm.addRow(linha);
                    tabelaPermissoes.setModel(dtm);
                    tabelaPermissoes.setValueAt(dados.get(i).getId_tab_cad_menu(),i,0);
                    tabelaPermissoes.setValueAt(dados.get(i).getNome_menu(),i,1);
                    
                    int i_status = dados.get(i).getStatus();
                    String aux_status="";
                    if(i_status == 0 ){
                        aux_status = "Bloqueado";
                    }else if(i_status == 1){
                        aux_status = "Permitido";
                    }else{
                        aux_status = "Bloqueado";
                    }
                    
                    tabelaPermissoes.setValueAt(aux_status,i,2);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PConfigCtrlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ManipulaJTables.removeLinhasBrancas(tabelaPermissoes);
    }

    private javax.swing.JButton btSelecionarUsuario;
    private javax.swing.JButton btAlternarPermissao;
    private javax.swing.JTable tabelaUsuarios;
    private javax.swing.JScrollPane scrollTUsuarios;
    private javax.swing.JScrollPane scrollTPermissoes;
    private javax.swing.table.DefaultTableModel modelUsuarios;
    private javax.swing.JTable tabelaPermissoes;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.table.DefaultTableModel modelPermissoes;
    private javax.swing.JPanel panelPermissoes;
    private int id_usuarioo = 0;
    private DAOTabCadMenusUsuario daotcmu = new DAOTabCadMenusUsuario();
}
