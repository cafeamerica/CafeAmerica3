/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.config;

import classes.ManipulaJTables;
import dao.cadastros.DAOUsuarios;
import dao.sistema.DAOTabCadMenus;
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
import model.sistema.TabCadMenus;
import model.sistema.TabCadMenusUsuario;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PCtrlAdcMenus extends javax.swing.JPanel {

    public PCtrlAdcMenus() {
        initComponents();
    }

    private void initComponents() {
        //configuções panel principal
        this.setLayout(new MigLayout());

        //fim
        //configurações panel menus
        panelMenus = new javax.swing.JPanel();
        panelMenus.setLayout(new MigLayout());
        //fim

        //configurações panel salvar
        panelSalvar = new javax.swing.JPanel();
        panelSalvar.setLayout(new MigLayout());
        //fim

        //inicilizando componentes jpanel salvar
        lblDescMenu = new javax.swing.JLabel("Descrição:");
        lblNomeMenu = new javax.swing.JLabel("Nome:");
        txtDescMenu = new javax.swing.JTextField(30);
        txtNomeMenu = new javax.swing.JTextField(30);
        btSalvar = new javax.swing.JButton("Salvar");
        //fim

        //adicionando componentes jpanel salvar
        panelSalvar.add(lblDescMenu, "split 2");
        panelSalvar.add(txtDescMenu, "wrap");
        panelSalvar.add(lblNomeMenu, "split 2, gapx 25");
        panelSalvar.add(txtNomeMenu, "wrap");
        panelSalvar.add(btSalvar, "gapx 120");
        //fim

        //configurações da tabela com dados do menus
        //Tabela config
        String[] columnNames = {"Nome", "Descrição"};
        Object[][] data = {};
        model = new javax.swing.table.DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabela = new javax.swing.JTable(model);
        tabela.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
        tabela.getColumnModel().getColumn(0).setMaxWidth(250);
        tabela.getColumnModel().getColumn(0).setMinWidth(250);
        tabela.getColumnModel().getColumn(1).setMaxWidth(250);
        tabela.getColumnModel().getColumn(1).setMinWidth(250);

        //alinhando os valores das celulas da Tabela à direita
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.getColumnModel().getColumn(0).setCellRenderer(r);
        scroll = new javax.swing.JScrollPane(tabela);
        //fim

        panelMenus.add(scroll);

        this.add(panelMenus, "wrap");
        this.add(panelSalvar);

        listaDadosMenu();

        //EVENTO bts
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btSalvarActionPerformed(e);
            }
        });

        //fim
    }

    private void listaDadosMenu() {
        List<TabCadMenus> dados;
        DAOTabCadMenus daotcm = new DAOTabCadMenus();

        try {
            dados = daotcm.retornaMenus();
            if (dados.size() == 0) {
                JOptionPane.showMessageDialog(null, "Não há menus no sistema!");
            } else {
                String[] linhas = {null, null};
                for (int i = 0; i < dados.size(); i++) {
                    DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
                    dtm.addRow(linhas);
                    tabela.setModel(dtm);
                    tabela.setValueAt(dados.get(i).getNome_menu(), i, 0);
                    tabela.setValueAt(dados.get(i).getDesc_menu(), i, 1);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PCtrlAdcMenus.class.getName()).log(Level.SEVERE, null, ex);
        }

        ManipulaJTables.removeLinhasBrancas(tabela);
    }

    private void limpaCampos() {
        txtDescMenu.setText("");
        txtNomeMenu.setText("");
        listaDadosMenu();
    }

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_nome = txtNomeMenu.getText().toString();
        String s_desc = txtDescMenu.getText().toString();

        if (s_nome.isEmpty() || s_nome.equals(null) || s_desc.isEmpty() || s_desc.equals(null)) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            DAOTabCadMenus daotcm = new DAOTabCadMenus();
            try {
                boolean condicao_nome_existe = daotcm.consultaNomeMenu(s_nome);

                if (condicao_nome_existe == true) {
                    JOptionPane.showMessageDialog(null, "Menu já existente!");
                } else {
                    //cadastra o menu e salva para cada usuario que tem no sistema jogando como bloqueado por enquanto
                    TabCadMenus tcm = new TabCadMenus();
                    DAOUsuarios daou = new DAOUsuarios();
                    DAOTabCadMenusUsuario daotcmu = new DAOTabCadMenusUsuario();
                    
                    List<Usuarios> dados_user;

                    dados_user = daou.listaIdUsuarios();

                    tcm.setNome_menu(s_nome);
                    tcm.setDesc_menu(s_desc);

                    daotcm.inserir(tcm);
                    
                    int ultima_id_tcm = daotcm.retornaUltimaID();

                    for (int i = 0; i < dados_user.size(); i++) {
                        TabCadMenusUsuario tcmu = new TabCadMenusUsuario();
                        
                        tcmu.setId_tab_cad_menu(ultima_id_tcm);
                        tcmu.setId_usuario(dados_user.get(i).getId_usuario());
                        tcmu.setStatus(0);
                        
                        daotcmu.inserir(tcmu);
                    }
                    
                    limpaCampos();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PCtrlAdcMenus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private javax.swing.JTable tabela;
    private javax.swing.JScrollPane scroll;
    private javax.swing.table.DefaultTableModel model;
    private javax.swing.JPanel panelMenus;
    private javax.swing.JLabel lblNomeMenu;
    private javax.swing.JLabel lblDescMenu;
    private javax.swing.JTextField txtNomeMenu;
    private javax.swing.JTextField txtDescMenu;
    private javax.swing.JButton btSalvar;
    private javax.swing.JPanel panelSalvar;
}
