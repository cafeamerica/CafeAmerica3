/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios.guias;

import bd.Conexao;
import classes.ManipulaArquivo;
import dao.DAOModel;
import java.awt.BorderLayout;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Rafael
 */
public class Relatorios extends DAOModel{

    private Connection con;
    private String[] param;
    private PreparedStatement ps;
    private ResultSet rs;
    String[] str_rel = new String[20];

    public Relatorios() {
        inicializaVetorRel();
       /* param = ManipulaArquivo.lerArquivo();
        try {
            this.con = Conexao.conectar(param[0], param[1], param[2]);
        } catch (Exception ex) {
            Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados");
        } */

    }

    public void inicializaVetorRel() {
        str_rel[0] = "jaspers/RelGeralGuias.jasper";
        str_rel[1] = "jaspers/RelGuiaViagemEnt.jasper";
        str_rel[2] = "jaspers/RelGuiaViagemSai.jasper";
        str_rel[3] = "jaspers/RelEstoqueGuiaDia.jasper";
        str_rel[4] = "jaspers/RelViagemEnt.jasper";
        str_rel[5] = "jaspers/RelViagemSai.jasper";
        str_rel[6] = "jaspers/RelViagemEntGuia.jasper";
        str_rel[7] = "jaspers/RelViagemSaiGuia.jasper";
        str_rel[8] = "jaspers/RelProducaoPeriodo.jasper";
        str_rel[9] = "jaspers/RelProducaoGuia.jasper";
        str_rel[10] = "jaspers/RelEstoqueDiarioFiscal.jasper";
        str_rel[11] = "jaspers/RelPdcEnt.jasper";
        str_rel[12] = "jaspers/RelEstoqueSoFiscalPeriodo.jasper";
        str_rel[13] = "jaspers/RelEstoqueSoFiscalGuia.jasper";
        str_rel[14] = "jaspers/RelEntFiscalGuia.jasper";
        str_rel[15] = "jaspers/RelSaiFiscalGuia.jasper";
    }

    public void gerarRelatorio(HashMap parametros, int opcao, int id_rel) {
        conectar();
        try {

            InputStream is = getClass().getResourceAsStream(str_rel[id_rel]);
            //  JasperReport report = JasperCompileManager.compileReport(".\\.\\model\\relatorios\\report7.jrxml");
            JasperPrint print = JasperFillManager.fillReport(is, parametros, this.getCon());
            // JasperExportManager.exportReportToPdfFile(print, "CAFE-AMERICA.pdf");

            if (opcao == 0) {
                viewReportFrame("Guias por período", print); //abre na tela
            } else if (opcao == 1) {
                imprimir(print); //imprime direto selecionando a impressora padrão
            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida!");
            }

        } catch (JRException ex) {
            Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void viewReportFrame(String titulo, JasperPrint print) {

        /*
         * Cria um JRViewer para exibir o relatório.
         * Um JRViewer é uma JPanel.
         */
        JRViewer viewer = new JRViewer(print);

        // cria o JFrame
        JFrame frameRelatorio = new JFrame(titulo);

        // adiciona o JRViewer no JFrame
        frameRelatorio.add(viewer, BorderLayout.CENTER);

        // configura o tamanho padrão do JFrame
        frameRelatorio.setSize(500, 500);

        // maximiza o JFrame para ocupar a tela toda.
        frameRelatorio.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // configura a operação padrão quando o JFrame for fechado.
        frameRelatorio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // exibe o JFrame
        frameRelatorio.setVisible(true);

    }

    private void imprimir(JasperPrint impressao) {
        try {
            //pegando o nome da impressora padrão!
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();

            JRPrintServiceExporter exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRPrintServiceExporterParameter.JASPER_PRINT, impressao);
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(new Copies(1));
            aset.add(MediaSizeName.ISO_A4);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, aset);
            PrintServiceAttributeSet serviceAttributeSet = new HashPrintServiceAttributeSet();
            //serviceAttributeSet.add(new PrinterName("HP DeskJet 610C (cópia 1)", Locale.getDefault()));  
            serviceAttributeSet.add(new PrinterName(service.getName(), Locale.getDefault()));
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, serviceAttributeSet);
            System.out.println("Inicializando impressão");
            exporter.exportReport();
            System.out.println("Impressão finalizada");
        } catch (JRException ex) {
            Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public JRViewer relatoriosEmTela(HashMap parametros, int pos, int op) {
        conectar();
        JRViewer jv = null;
        try {
            InputStream is = getClass().getResourceAsStream(str_rel[pos]);
            JasperPrint jp = JasperFillManager.fillReport(is, parametros, this.getCon());
            JRViewer jasperViewer = new JRViewer(jp);
            jv = jasperViewer;
        } catch (JRException ex) {
            Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           jv.setPreferredSize(new java.awt.Dimension(860, 800));
        try {
            this.getCon().close();
            return jv;
        } catch (SQLException ex) {
            Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
           return jv;
        
        
        
    }

}
