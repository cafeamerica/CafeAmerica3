/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.Dimension;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.MaskFormatter;
import org.joda.time.DateTime;
import org.joda.time.Duration;


/**
 *
 * @author Rafael
 */
public class ManipulaDatas {

    //variavel que ira armazenar a diferença de datas
    public static long calc_datas;

    public ManipulaDatas() {

    }

    public static Date converteStrDt(String str_data) {
        String str_convt = "";
        Date date = null;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = (java.util.Date) formatter.parse(str_data);
        } catch (ParseException ex) {
            Logger.getLogger(ManipulaDatas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return date;
    }

    public static java.sql.Date converteStrSql(String str_data) {
        java.sql.Date date = null;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.sql.Date data = new java.sql.Date(format.parse(str_data).getTime());
            date = data;
        } catch (ParseException ex) {
            Logger.getLogger(ManipulaDatas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return date;
    }

    //validando as datas
    public static boolean validaData(String dt) {
        boolean vlr = false;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date data = sdf.parse(dt); //se é uma data com dia/mes/ano com valores corretos, ok, está tudo certo
            int ano = Integer.parseInt(dt.substring(6, 10));
            if (ano < 2014) {
                vlr = false;
            } else {
                vlr = true;
            }

        } catch (ParseException e) {
            vlr = false;
        }

        return vlr;
    }

    //Calculando atraso de datas
    public static long calculaAtraso(String dataVenc, String dataFim) {
        long calculo = 0;

        try {
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy"); // criando um modelo de datas
            java.util.Date d1 = df.parse(dataVenc); //passando os valores das Strings para objetos do tipo Date
            java.util.Date d2 = df.parse(dataFim);

            DateTime dtf2 = new DateTime(d2);
            DateTime dtv2 = new DateTime(d1);

            Duration d = new Duration(dtf2, dtv2);
            long dias = (d.getStandardDays()) * -1;

            calculo = dias;
            calc_datas = calculo; //variavel global que armazena o conteudo do calculo

        } catch (ParseException ex) {
            Logger.getLogger(ManipulaDatas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return calc_datas;
    }

    public static int comparaData(String dataVenc, String dataBase) {

        //Primeiro passa as datas do tipo String
        //para o tipo Date, jogando no formato dd/MM/yyyy
        //depois compara a data_base com a data_venc
        Date dateVenc = null; //criando instancia do tipo Date
        Date dateBase = null; //criando instancia do tipo Date

        int condicao = 0;
        try {

            //criando um formato de data para dia/mes/ano
            DateFormat formatter_venc = new SimpleDateFormat("dd/MM/yyyy");

            //aplicando a String contendo a data recebida com o formato criado no objeto do tipo Date
            dateVenc = (java.util.Date) formatter_venc.parse(dataVenc);

            DateFormat formatter_base = new SimpleDateFormat("dd/MM/yyyy");
            dateBase = (java.util.Date) formatter_base.parse(dataBase);

            condicao = dateBase.compareTo(dateVenc); //irá retornar um tipo int dependendo da condição apresentada
            // 1 ~ primeira data maior
            // 0 ~ datas iguais
            //-1 ~ segunda data maior

        } catch (ParseException ex) {
            Logger.getLogger(ManipulaDatas.class.getName()).log(Level.SEVERE, null, ex);

        }

        return condicao;
    }

    public static Dimension retornaDimensaoDt() {
        Dimension d = new Dimension(75, 24);
        return d;
    }

    public static MaskFormatter retornaMascaraDt() {
        MaskFormatter mf = null;
        try {
            MaskFormatter m = new MaskFormatter("##/##/####");
            mf = m;
        } catch (ParseException ex) {
            Logger.getLogger(ManipulaDatas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mf;
    }

    public static String converteSqlString(java.sql.Date data) {

        String s_data = data.toString();

        String s_dia = s_data.substring(8, 10);
        String s_mes = s_data.substring(5, 7);
        String s_ano = s_data.substring(0, 4);

        String aux_data = s_dia + "/" + s_mes + "/" + s_ano;

        return aux_data;
    }

    public static String retornaDataAtual() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        
        
        return dateFormat.format(date);
    }
}
