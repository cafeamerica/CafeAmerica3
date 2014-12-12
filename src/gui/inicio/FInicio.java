 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inicio;

import classes.ManipulaArquivo;
import dao.cadastros.DAOUsuarios;
import dao.sistema.DAOTabCadMenusUsuario;
import gui.cadastros.PCadastrosCidades;
import gui.cadastros.PCadastrosCtsBancarias;
import gui.cadastros.PCadastrosEstados;
import gui.cadastros.PCadastrosUsuarios;
import gui.config.PConfigCtrlUsuarios;
import gui.config.PCtrlAdcMenus;
import gui.controles.PGuiasControlesEmb;
import gui.financeiro.PFinContasReceberPeriodo;
import gui.guias.saida.nova.viagem.PGuiasSaidaNovaViagem;
import gui.guias.entrada.nova.guia.PGuiasEntradaNovaGuia;
import gui.guias.entrada.nova.viagem.PGuiasEntradaNovaViagem;
import gui.guias.notasfiscais.PGuiasNotasFiscaisConferencia;
import gui.guias.notasfiscais.PGuiasNotasFiscaisDados;
import gui.guias.notasfiscais.PGuiasNotasFiscaisPendentes;
import gui.guias.producao.PGuiasProducaoAltProd;
import gui.guias.producao.PGuiasProducaoDeCafe;
import gui.guias.saida.nova.viagem.PGuiasSaidaAddServico;
import gui.guias.saida.nova.viagem.PGuiasSaidaTransformacaoGuia;
import gui.guias.saida.nova.viagem.PGuiasSaidaLigaGuiaExistente;
import gui.login.FLoginEntrar;
import gui.relatorios.guias.PRelEntFiscalGuia;
import gui.relatorios.guias.PRelFinanCtasReceber;
import gui.relatorios.guias.PRelSaiFiscalGuia;
import gui.relatorios.guias.PRelatoriosGuiaEntradaGuia;
import gui.relatorios.guias.PRelatoriosGuiaEntradaPeriodo;
import gui.relatorios.guias.PRelatoriosGuiaEstoqueFiscal;
import gui.relatorios.guias.PRelatoriosGuiaEstoquePeriodo;
import gui.relatorios.guias.PRelatoriosGuiaFiscalGuia;
import gui.relatorios.guias.PRelatoriosGuiaGeral;
import gui.relatorios.guias.PRelatoriosGuiaSaidaGuia;
import gui.relatorios.guias.PRelatoriosGuiaSaidaPeriodo;
import gui.relatorios.guias.PRelatoriosGuiaSoFiscalPeriodo;
import gui.relatorios.producao.PRelatoriosDadosGuia;
import gui.relatorios.producao.PRelatoriosPdcGuia;
import gui.relatorios.producao.PRelatoriosPdcPeriodo;
import gui.relatorios.producao.PRelatoriosPdcResumoGuia;
import java.awt.AWTKeyStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Menu;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.sistema.TabCadMenusUsuario;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class FInicio extends javax.swing.JFrame {

    public FInicio(int id_usuario) {
        initComponents();

        realizaPermissoes(id_usuario);

        Set<AWTKeyStroke> fkeys = new HashSet<AWTKeyStroke>(KeyboardFocusManager.getCurrentKeyboardFocusManager().getDefaultFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        fkeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));

        KeyboardFocusManager.getCurrentKeyboardFocusManager().setDefaultFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, fkeys);

    }

  

    private void initComponents() {
        //inicializando componentes frame principal
        scrollPane = new javax.swing.JScrollPane();
        menuBar = new javax.swing.JMenuBar();
        panelPrincipal = new javax.swing.JPanel();
        panelPrincipal.setLayout(new MigLayout());
        
        menuBar.setForeground(Color.red);
        //fim

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension tamTela = kit.getScreenSize();
        //Pegando a largura total da tela  
        int larg = tamTela.width;

        //Pegando a altura total da tela  
        int alt = tamTela.height;

        /*Se você quiser que o seu JFrame ocupe 70% da tela por exemplo  
         multiplique a largura e altura totais por 0,7*/
        int minhaLargura = (int) (larg * 0.99);
        int minhaAltura = (int) (alt * 0.87);

        //configurações do jframe inicial
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("CAFÉ AMÉRICA LTDA");
        this.setJMenuBar(menuBar);
        // scrollPane.setPreferredSize(new java.awt.Dimension(1350, 670));
        dimensao = new java.awt.Dimension(minhaLargura, minhaAltura);
        scrollPane.setPreferredSize(dimensao);

        getContentPane().add(scrollPane);
        scrollPane.setViewportView(panelPrincipal);
        //fim

        //inicializando componentes jmenu bar
        menuArquivo = new javax.swing.JMenu("Arquivo");
        menuItemArqTrocarUser = new javax.swing.JMenuItem("Trocar usuário");
        menuItemArqInicio = new javax.swing.JMenuItem("Tela inicial");
        menuItemArqAttApp = new javax.swing.JMenuItem("Atualizar sistema");
        menuItemArqSair = new javax.swing.JMenuItem("Sair");

        menuCadastros = new javax.swing.JMenu("Cadastros");
        menuItemCadCidades = new javax.swing.JMenuItem("Cidades");
        menuItemCadEmpresas = new javax.swing.JMenuItem("Empresas");
        menuItemCadEstados = new javax.swing.JMenuItem("Estados");
        menuItemCadProdutores = new javax.swing.JMenuItem("Produtores");
        menuItemCadUsuarios = new javax.swing.JMenuItem("Usuários");
        menuItemCadCtsBanc = new javax.swing.JMenuItem("Contas bancárias");
        separaCadFinan = new javax.swing.JSeparator();
        separaCadUteis = new javax.swing.JSeparator();

        menuConfig = new javax.swing.JMenu("Configurações");

        menuRelatorios = new javax.swing.JMenu("Relatórios");
        menuRelatoriosGerenciais = new javax.swing.JMenu("Gerenciais");
        menuItemRelatoriosGerenciaisEntPeriodo = new javax.swing.JMenuItem("Entrada por período");
        menuItemRelatoriosGerenciaisEntGuia = new javax.swing.JMenuItem("Entrada por guia");
        menuItemRelatoriosGerenciaisEstoqueDiario = new javax.swing.JMenuItem("Estoque diário");
        menuItemRelatoriosGerenciaisGeral = new javax.swing.JMenuItem("Geral");
        menuItemRelatoriosGerenciaisSaidaGuia = new javax.swing.JMenuItem("Saida por guia");
        menuItemRelatoriosGerenciaisSaidaPeriodo = new javax.swing.JMenuItem("Saida por período");
        menuRelatoriosGerenciaisGuia = new javax.swing.JMenu("Guias");
        menuRelatoriosProducao = new javax.swing.JMenu("Produção");
        menuItemRelatoriosProducaoPeriodo = new javax.swing.JMenuItem("Produção por período");
        menuItemRelatoriosProducaoGuia = new javax.swing.JMenuItem("Produção por guia");
        menuItemRelatoriosResumoGuia = new javax.swing.JMenuItem("Resumo de guia");
        menuItemRelatoriosGerenciaisEstoqueDiarioFiscal = new javax.swing.JMenuItem("Estoque Real-Fiscal");
        menuItemRelatoriosDadosGuia = new javax.swing.JMenuItem("Ent. e Saí. de guia");
        menuItemRelatoriosGerenciaisFiscalPeriodo = new javax.swing.JMenuItem("Fiscal por período");
        menuItemRelatoriosGerenciaisFiscalGuia = new javax.swing.JMenuItem("Fiscal por guia");
        menuItemRelatoriosGerenciaisEntFiscalGuia = new javax.swing.JMenuItem("Entrada fiscal");
        menuItemRelatoriosGerenciaisSaiFiscalGuia  = new javax.swing.JMenuItem("Saída fiscal");
        separatorRelFiscalReal = new javax.swing.JSeparator();
        menuItemRelatoriosGerenciaisFinanCtsReceber = new javax.swing.JMenuItem("Contas a receber");
        menuRelatoriosGerenciaisFinan = new javax.swing.JMenu("Financeiro");
        
        
        
        menuContabil = new javax.swing.JMenu("Contábil");

        menuFinanceiro = new javax.swing.JMenu("Financeiro");
        menuItemFinContasRecerPeriodo = new javax.swing.JMenuItem("Contas à receber - período");

        menuGuias = new javax.swing.JMenu("Guias");
        menuItemGuiasProducaoProdDeCafe = new javax.swing.JMenuItem("Produção de café");
        menuGuiasEnt = new javax.swing.JMenu("Entradas");
        menuItemGuiasEntNovaGuia = new javax.swing.JMenuItem("Nova guia");
        menuItemGuiasEntNovaViagem = new javax.swing.JMenuItem("Nova viagem");
        menuGuiasSai = new javax.swing.JMenu("Saídas");
        menuItemGuiasSaiAddServicos = new javax.swing.JMenuItem("Serviços adicionais");
        menuItemGuiasSaiNovaViagem = new javax.swing.JMenuItem("Nova viagem");
        menuItemGuiasSaiTransformacaoGuia = new javax.swing.JMenuItem("Transformação em guia");
        menuGuiasNotasFiscais = new javax.swing.JMenu("Notas fiscais");
        menuItemGuiasNotasFiscaisConferencia = new javax.swing.JMenuItem("Conferência de NFe");
        menuItemGuiasNotasFiscaisDados = new javax.swing.JMenuItem("Consultar NFe");
        menuItemGuiasNotasFiscaisPendentes = new javax.swing.JMenuItem("NFe pendentes");
        menuItemGuiasControlesEmb = new javax.swing.JMenuItem("Controle de embalagens");
        menuGuiasControles = new javax.swing.JMenu("Controles");
        menuItemGuiasSaiTransformacaoGuiaExistente = new javax.swing.JMenuItem("Liga de guias existentes");
        menuItemGuiasProducaoAltProd = new javax.swing.JMenuItem("Alterar produção");
        menuGuiasProducao = new javax.swing.JMenu("Produção");

        menuItemConfigCtrlUsuarios = new javax.swing.JMenuItem("Controle de usuários");
        menuItemConfigCtrlAdcMenus = new javax.swing.JMenuItem("Add Menus");
        //fim

        //adicionando componentes jmenu bar
        menuBar.add(menuArquivo);
        menuArquivo.add(menuItemArqAttApp);
        menuArquivo.add(menuItemArqInicio);
        menuArquivo.add(menuItemArqTrocarUser);
        menuArquivo.add(menuItemArqSair);

        menuBar.add(menuCadastros);
        menuCadastros.add(menuItemCadCtsBanc);
        menuCadastros.add(separaCadFinan);
        menuCadastros.add(menuItemCadCidades);
        menuCadastros.add(menuItemCadEstados);
        menuCadastros.add(menuItemCadEmpresas);
        menuCadastros.add(menuItemCadProdutores);
        menuCadastros.add(separaCadUteis);
        menuCadastros.add(menuItemCadUsuarios);

        menuBar.add(menuGuias);
        menuGuias.add(menuGuiasEnt);
        menuGuiasEnt.add(menuItemGuiasEntNovaGuia);
        menuGuiasEnt.add(menuItemGuiasEntNovaViagem);
        menuGuias.add(menuGuiasSai);
        menuGuiasSai.add(menuItemGuiasSaiNovaViagem);
        menuGuiasSai.add(menuItemGuiasSaiAddServicos);
        menuGuiasSai.add(menuItemGuiasSaiTransformacaoGuia);
        menuGuiasSai.add(menuItemGuiasSaiTransformacaoGuiaExistente);
        menuGuias.add(menuGuiasNotasFiscais);
        menuGuiasNotasFiscais.add(menuItemGuiasNotasFiscaisDados);
      //  menuGuiasNotasFiscais.add(menuItemGuiasNotasFiscaisConferencia);
        menuGuiasNotasFiscais.add(menuItemGuiasNotasFiscaisPendentes);
        menuGuias.add(menuGuiasProducao);
        menuGuiasProducao.add(menuItemGuiasProducaoProdDeCafe);
        menuGuiasProducao.add(menuItemGuiasProducaoAltProd);
        menuGuias.add(menuGuiasControles);
        menuGuiasControles.add(menuItemGuiasControlesEmb);

        menuRelatorios.add(menuRelatoriosGerenciais);
        menuRelatoriosGerenciais.add(menuRelatoriosGerenciaisGuia);
        menuRelatoriosGerenciais.add(menuRelatoriosGerenciaisFinan);
        menuRelatoriosGerenciaisGuia.add(menuItemRelatoriosGerenciaisGeral);
        menuRelatoriosGerenciaisGuia.add(menuItemRelatoriosGerenciaisEstoqueDiario);
        menuRelatoriosGerenciaisGuia.add(menuItemRelatoriosGerenciaisEstoqueDiarioFiscal);
        menuRelatoriosGerenciaisGuia.add(menuItemRelatoriosGerenciaisFiscalPeriodo);
        menuRelatoriosGerenciaisGuia.add(menuItemRelatoriosGerenciaisFiscalGuia);
        menuRelatoriosGerenciaisGuia.add(menuItemRelatoriosGerenciaisEntFiscalGuia);
        menuRelatoriosGerenciaisGuia.add(menuItemRelatoriosGerenciaisSaiFiscalGuia);
        menuRelatoriosGerenciaisGuia.add(separatorRelFiscalReal);
        menuRelatoriosGerenciaisGuia.add(menuItemRelatoriosGerenciaisEntPeriodo);
        menuRelatoriosGerenciaisGuia.add(menuItemRelatoriosGerenciaisEntGuia);
        menuRelatoriosGerenciaisGuia.add(menuItemRelatoriosGerenciaisSaidaGuia);
        menuRelatoriosGerenciaisGuia.add(menuItemRelatoriosGerenciaisSaidaPeriodo);
        menuRelatoriosGerenciaisFinan.add(menuItemRelatoriosGerenciaisFinanCtsReceber);
        menuRelatorios.add(menuRelatoriosProducao);
        menuRelatoriosProducao.add(menuItemRelatoriosDadosGuia);
        menuRelatoriosProducao.add(menuItemRelatoriosProducaoPeriodo);
        menuRelatoriosProducao.add(menuItemRelatoriosProducaoGuia);
        menuRelatoriosProducao.add(menuItemRelatoriosResumoGuia);
        

        menuBar.add(menuContabil);
        
        menuBar.add(menuFinanceiro);
        menuFinanceiro.add(menuItemFinContasRecerPeriodo);
        
        
        menuBar.add(menuRelatorios);

        menuBar.add(menuConfig);
        menuConfig.add(menuItemConfigCtrlUsuarios);
        menuConfig.add(menuItemConfigCtrlAdcMenus);

        //fim
        //perguntando ao usuario se deseja fechar o jframe
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //caixa de dialogo retorna um inteiro  
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja sair do sistema?", "SAIR", JOptionPane.YES_NO_OPTION);

                if (resposta == 0) {
                    dispose();
                }

            }
        });

        //adicionando evento de menus e menus itens
        menuItemGuiasEntNovaGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verAlterarPanel();
                if(check_panel == false){
                    scrollPane.setViewportView(new PGuiasEntradaNovaGuia());
                    var_panel = 1;
                }else{
                    int resp = JOptionPane.showConfirmDialog(null, "Você deseja realmente trocar de tela?", "Trocar de tela", JOptionPane.YES_NO_OPTION);
                    if(resp==0){
                        scrollPane.setViewportView(new PGuiasEntradaNovaGuia());
                    }
                }
            }
        });
        menuItemGuiasEntNovaViagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PGuiasEntradaNovaViagem());
            }
        });
        menuItemGuiasProducaoProdDeCafe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PGuiasProducaoDeCafe());
            }
        });
        menuItemGuiasSaiNovaViagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PGuiasSaidaNovaViagem());
            }
        });
        menuItemRelatoriosGerenciaisGeral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelatoriosGuiaGeral());
            }
        });
        menuItemGuiasNotasFiscaisPendentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PGuiasNotasFiscaisPendentes());
            }
        });
        menuItemGuiasNotasFiscaisConferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PGuiasNotasFiscaisConferencia());
            }
        });
        menuItemGuiasSaiAddServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PGuiasSaidaAddServico());
            }
        });
        menuItemRelatoriosGerenciaisEstoqueDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelatoriosGuiaEstoquePeriodo());
            }
        });
        menuItemCadUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PCadastrosUsuarios());
            }
        });
        menuItemGuiasSaiTransformacaoGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PGuiasSaidaTransformacaoGuia());
            }
        });
        menuItemRelatoriosGerenciaisEntPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelatoriosGuiaEntradaPeriodo());
            }
        });
        menuItemRelatoriosGerenciaisSaidaPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelatoriosGuiaSaidaPeriodo());
            }
        });
        menuItemRelatoriosGerenciaisEntGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelatoriosGuiaEntradaGuia());
            }
        });
        menuItemRelatoriosGerenciaisSaidaGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelatoriosGuiaSaidaGuia());
            }
        });
        menuItemRelatoriosProducaoPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelatoriosPdcPeriodo());
            }
        });
        menuItemRelatoriosProducaoGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelatoriosPdcGuia());
            }
        });
        menuItemRelatoriosResumoGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelatoriosPdcResumoGuia());
            }
        });

        menuItemArqInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inicio();
            }
        });
        menuItemArqTrocarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente trocar de usuário?", "Trocar usuário", JOptionPane.YES_NO_OPTION);

                if (resp == 0) {
                    FLoginEntrar fl = new FLoginEntrar();
                    fl.setVisible(true);
                    dispose();
                }
            }
        }
        );
        menuItemRelatoriosGerenciaisEstoqueDiarioFiscal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelatoriosGuiaEstoqueFiscal());
            }
        });
        menuItemRelatoriosDadosGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelatoriosDadosGuia());
            }
        });
        menuItemGuiasControlesEmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PGuiasControlesEmb());
            }
        });
        menuItemConfigCtrlUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PConfigCtrlUsuarios());
            }
        });
        menuItemConfigCtrlAdcMenus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PCtrlAdcMenus());
            }
        });
        menuItemArqSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente sair do sistema?", "Sair do sistema", JOptionPane.YES_NO_OPTION);

                if (resp == 0) {
                    dispose();
                }
            }
        });
        menuItemRelatoriosGerenciaisFiscalPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelatoriosGuiaSoFiscalPeriodo());
            }
        });
        menuItemRelatoriosGerenciaisFiscalGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelatoriosGuiaFiscalGuia());
            }
        });

        menuItemRelatoriosGerenciaisEntFiscalGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelEntFiscalGuia());
            }
        });
        menuItemRelatoriosGerenciaisSaiFiscalGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PRelSaiFiscalGuia());
            }
        });
        
        menuItemFinContasRecerPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PFinContasReceberPeriodo());
            }
        });
        
        menuItemGuiasNotasFiscaisDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PGuiasNotasFiscaisDados());
            }
        });
        
        menuItemCadCtsBanc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PCadastrosCtsBancarias());
            }
        });
        
        menuItemRelatoriosGerenciaisFinanCtsReceber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
               scrollPane.setViewportView(new PRelFinanCtasReceber());
            }
        });
        
        menuItemGuiasSaiTransformacaoGuiaExistente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PGuiasSaidaLigaGuiaExistente());
            }
        });
        
        menuItemGuiasProducaoAltProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PGuiasProducaoAltProd());
            }
        });
        menuItemCadEstados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PCadastrosEstados());
            }
        });
        
        menuItemCadCidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new PCadastrosCidades());
            }
        });
        
        menuItemArqAttApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int resp = JOptionPane.showConfirmDialog(null, "Deseja atualizar o sistema? \nSe sim, feche seus trabalhos...", "Confirma atualização", JOptionPane.YES_NO_OPTION);

                if (resp == 0) {
                    File file = new File("\\\\192.168.0.192\\backup\\dist\\launch.jnlp");
                    try {
                        java.awt.Desktop.getDesktop().open(file);
                        dispose();
                    } catch (IOException ex) {
                        Logger.getLogger(FInicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        });

        //fim
        montaPanelPrincipal();

        pack();
    }

    public static void getButton(javax.swing.JButton btPrincipal) { //recebede o botao principal da tela e deixa ele funcionar c o enter
        btPrincipal.registerKeyboardAction(
                btPrincipal.getActionForKeyStroke(
                        KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED
        );

        btPrincipal.registerKeyboardAction(
                btPrincipal.getActionForKeyStroke(
                        KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED
        );

    }



    /*
     public static void main(String[] args) {
     java.awt.EventQueue.invokeLater(new Runnable() {
     public void run() {
     new FInicio().setVisible(true);
     }
     });
     } */ 
    
    public void verAlterarPanel(){
        if(var_panel == 0){//abre a tela normalmente
            check_panel = false;
        }else{//verifica se ja foi aberto um jpanel, se sim pergunta ao usuario se deseja sair da tela..
            check_panel = true;
        }
        
    } 
    
    public static Dimension getDimensionn() {
        return dimensao;
    }

    public void montaPanelPrincipal() {
        //inicializando componentes jpanel principal
        lblStatus = new javax.swing.JLabel("Status:");
        lblStatusR = new javax.swing.JLabel("Ativo");
        lblUsuario = new javax.swing.JLabel("Usuário:");
        lblUsuarioR = new javax.swing.JLabel("X");
        lblSeparar1 = new javax.swing.JLabel("__________________________________");
        lblBanco = new javax.swing.JLabel("Banco de dados:");
        lblEndereco = new javax.swing.JLabel("Endereço:");
        lblPorta = new javax.swing.JLabel("Porta:");
        lblBancoR = new javax.swing.JLabel("");
        lblEnderecoR = new javax.swing.JLabel("");
        lblPortaR = new javax.swing.JLabel("");
        lblStatusBanco = new javax.swing.JLabel("Status:");
        lblStatusBancoR = new javax.swing.JLabel("Conectado");
        lblTitulo = new javax.swing.JLabel("CAFÉ AMÉRICA LTDA");
        panelBanco = new javax.swing.JPanel();
        panelUsuario = new javax.swing.JPanel();
        panelBanco.setLayout(new MigLayout());
        panelUsuario.setLayout(new MigLayout());
        //fim

        //configuração componentes jpanel principal
        panelBanco.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Banco de dados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuário", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        lblUsuarioR.setForeground(Color.BLUE);
        lblStatusR.setForeground(Color.green);
        DAOUsuarios daou = new DAOUsuarios();
        try {
            String s_usuario = daou.retornaNomeId(Integer.parseInt(ManipulaArquivo.lerArquivoUsuario()));
            lblUsuarioR.setText(s_usuario);
            //fim
        } catch (SQLException ex) {
            Logger.getLogger(FInicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        String param[] = ManipulaArquivo.lerArquivo();
        String banco = param[0];
        String endereco = param[1];
        String porta = param[2];

        lblBancoR.setText(banco);
        lblEnderecoR.setText(endereco);
        lblPortaR.setText(porta);

        lblStatusBancoR.setForeground(Color.GREEN);
        lblPortaR.setForeground(Color.BLUE);
        lblEnderecoR.setForeground(Color.BLUE);
        lblBancoR.setForeground(Color.BLUE);
        //adicionando componentes jpanel principal
        //  panelPrincipal.add(lblTitulo,"wrap, align center");

        panelUsuario.add(lblUsuario, "split 2");
        panelUsuario.add(lblUsuarioR, "wrap");
        panelUsuario.add(lblStatus, "split 2, gapx 8");
        panelUsuario.add(lblStatusR, "wrap");
        //panelPrincipal.add(lblSeparar1, "wrap");
        panelBanco.add(lblBanco, "split 2");
        panelBanco.add(lblBancoR, "wrap");
        panelBanco.add(lblEndereco, "split 2, gapx 37");
        panelBanco.add(lblEnderecoR, "wrap");
        panelBanco.add(lblPorta, "split 2, gapx 60");
        panelBanco.add(lblPortaR, "wrap");
        panelBanco.add(lblStatusBanco, "split 2, gapx 57");
        panelBanco.add(lblStatusBancoR);

        panelPrincipal.add(panelUsuario,"top");
        panelPrincipal.add(panelBanco);
        //fim
    }
    
    public static void inicio() {
        scrollPane.setViewportView(panelPrincipal);
    }

    private void realizaPermissoes(int id_usuario) {
        DAOTabCadMenusUsuario daotcmu = new DAOTabCadMenusUsuario();
        List<TabCadMenusUsuario> dados;
        DAOUsuarios daou = new DAOUsuarios();
        try {
            int tipo_user = daou.retornaTipo(id_usuario);

            if (tipo_user == 1) { //T.I. E Administradores
                //Nada acontece
            } else if (tipo_user == 2) { //Produção
                menuConfig.setVisible(false);
                menuContabil.setVisible(false);
                menuFinanceiro.setVisible(false);
                menuItemCadCidades.setVisible(false);
                menuItemCadEstados.setVisible(false);
                menuItemCadProdutores.setVisible(false);
                menuItemCadUsuarios.setVisible(false);
                menuRelatoriosGerenciais.setVisible(true);
                menuItemRelatoriosGerenciaisGeral.setVisible(false);
                menuItemCadCtsBanc.setVisible(false);
                menuRelatoriosGerenciaisFinan.setVisible(false);
            } else if (tipo_user == 3) { //Financeiro
                menuConfig.setVisible(false);
                menuGuias.setVisible(false);
                menuItemCadCidades.setVisible(false);
                menuItemCadEstados.setVisible(false);
                menuItemCadProdutores.setVisible(false);
                menuItemCadUsuarios.setVisible(false);
                //menuRelatoriosGerenciais.setVisible(false);
            } else if (tipo_user == 4) { //Contábil
                menuGuias.setVisible(true);
                menuConfig.setVisible(false);
                menuFinanceiro.setVisible(false);
                menuItemCadCidades.setVisible(false);
                menuItemCadEstados.setVisible(false);
                menuItemCadProdutores.setVisible(false);
                menuItemCadUsuarios.setVisible(false);
                menuItemCadCtsBanc.setVisible(false);
                menuRelatoriosGerenciais.setVisible(true);
                menuItemRelatoriosGerenciaisGeral.setVisible(false);
                menuRelatoriosGerenciaisFinan.setVisible(false);
            } else { //Outros
                menuConfig.setVisible(false);
                menuContabil.setVisible(false);
                menuFinanceiro.setVisible(false);
                menuItemCadCidades.setVisible(false);
                menuItemCadEstados.setVisible(false);
                menuItemCadProdutores.setVisible(false);
                menuItemCadUsuarios.setVisible(false);
                // menuRelatoriosGerenciais.setVisible(false);
                menuRelatoriosGerenciaisFinan.setVisible(false);
                menuRelatorios.setVisible(false);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FInicio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //componentes gerais
    private javax.swing.JMenuBar menuBar;
    private static javax.swing.JScrollPane scrollPane;
    //fim componentes gerais

    //Jmenus
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenu menuCadastros;
    private javax.swing.JMenu menuGuias;
    private javax.swing.JMenu menuFinanceiro;
    private javax.swing.JMenu menuContabil;
    private javax.swing.JMenu menuRelatorios;
    private javax.swing.JMenu menuConfig;
    //fim jmenus

    //JMenuItems e sub menus
    //menu item arquivo
    private javax.swing.JMenuItem menuItemArqTrocarUser;
    private javax.swing.JMenuItem menuItemArqAttApp;
    private javax.swing.JMenuItem menuItemArqSair;
    private javax.swing.JMenuItem menuItemArqInicio;
    //fim menu item arquivo

    //menu item cadastros
    private javax.swing.JMenuItem menuItemCadProdutores;
    private javax.swing.JMenuItem menuItemCadEmpresas;
    private javax.swing.JMenuItem menuItemCadCidades;
    private javax.swing.JMenuItem menuItemCadEstados;
    private javax.swing.JMenuItem menuItemCadUsuarios;
    private javax.swing.JMenuItem menuItemCadCtsBanc;
    
    private javax.swing.JSeparator separaCadUteis;
    private javax.swing.JSeparator separaCadFinan;
    //fim menu item cadastros

    //jmenu items e sub menus
    //menus guias
    private javax.swing.JMenu menuGuiasEnt;

    //producao
    private javax.swing.JMenuItem menuItemGuiasProducaoProdDeCafe;
    private javax.swing.JMenuItem menuItemGuiasProducaoAltProd;
    private javax.swing.JMenu menuGuiasProducao;
    //fim

    //menu itens guias entrada
    private javax.swing.JMenuItem menuItemGuiasEntNovaGuia;
    private javax.swing.JMenuItem menuItemGuiasEntNovaViagem;
    //fim menu itens guias saida

    private javax.swing.JMenu menuGuiasSai;
    //menu itens guias saida
    private javax.swing.JMenuItem menuItemGuiasSaiNovaViagem;
    private javax.swing.JMenuItem menuItemGuiasSaiAddServicos;
    private javax.swing.JMenuItem menuItemGuiasSaiTransformacaoGuia;
    private javax.swing.JMenuItem menuItemGuiasSaiTransformacaoGuiaExistente;
    //fim menu itens guias saidas

    //menu guias notas fiscais
    private javax.swing.JMenu menuGuiasNotasFiscais;
    private javax.swing.JMenuItem menuItemGuiasNotasFiscaisConferencia;
    private javax.swing.JMenuItem menuItemGuiasNotasFiscaisPendentes;
    private javax.swing.JMenuItem menuItemGuiasNotasFiscaisDados;
    //fim

    private javax.swing.JMenu menuGuiasControles;
    private javax.swing.JMenuItem menuItemGuiasControlesEmb;
    
    
    private javax.swing.JMenuItem menuItemFinContasRecerPeriodo;

    //fim jmenu items e sub menus
    //inicio jmenu e jmenuitens relatorios
    private javax.swing.JMenu menuRelatoriosGerenciais;
    private javax.swing.JMenu menuRelatoriosGerenciaisGuia;
    private javax.swing.JMenuItem menuItemRelatoriosGerenciaisEntPeriodo;
    private javax.swing.JMenuItem menuItemRelatoriosGerenciaisEntGuia;
    private javax.swing.JMenuItem menuItemRelatoriosGerenciaisSaidaPeriodo;
    private javax.swing.JMenuItem menuItemRelatoriosGerenciaisSaidaGuia;
    private javax.swing.JMenuItem menuItemRelatoriosGerenciaisGeral;
    private javax.swing.JMenuItem menuItemRelatoriosGerenciaisEstoqueDiario;
    private javax.swing.JMenuItem menuItemRelatoriosGerenciaisFiscalPeriodo;
    private javax.swing.JMenuItem menuItemRelatoriosGerenciaisFiscalGuia;
    private javax.swing.JMenuItem menuItemRelatoriosGerenciaisEstoqueDiarioFiscal;
    private javax.swing.JMenuItem menuItemRelatoriosGerenciaisEntFiscalGuia;
    private javax.swing.JMenuItem menuItemRelatoriosGerenciaisSaiFiscalGuia;
    private javax.swing.JSeparator separatorRelFiscalReal;
    
    private javax.swing.JMenu menuRelatoriosGerenciaisFinan;
    private javax.swing.JMenuItem menuItemRelatoriosGerenciaisFinanCtsReceber;
    
    private javax.swing.JMenu menuRelatoriosProducao;
    private javax.swing.JMenuItem menuItemRelatoriosProducaoPeriodo;
    private javax.swing.JMenuItem menuItemRelatoriosProducaoGuia;
    private javax.swing.JMenuItem menuItemRelatoriosResumoGuia;
    private javax.swing.JMenuItem menuItemRelatoriosDadosGuia;
    
    //fim menu e jmenuitens relatorios

    //inicio menu e sub menus configuraçoes
    private javax.swing.JMenuItem menuItemConfigCtrlUsuarios;
    private javax.swing.JMenuItem menuItemConfigCtrlAdcMenus;

    //fim menu e sub menus configuraçoes
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblUsuarioR;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStatusR;
    private javax.swing.JLabel lblSeparar1;
    private javax.swing.JLabel lblBancoR;
    private javax.swing.JLabel lblEnderecoR;
    private javax.swing.JLabel lblPortaR;
    private javax.swing.JLabel lblBanco;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblPorta;
    private javax.swing.JLabel lblStatusBanco;
    private javax.swing.JLabel lblStatusBancoR;
    private javax.swing.JLabel lblTitulo;
    private static javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelUsuario;
    private javax.swing.JPanel panelBanco;
    private static int var_panel = 0;
    private boolean check_panel = false;

    private static java.awt.Dimension dimensao;
    CardLayout layout = new CardLayout();
}
