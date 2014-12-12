/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.financeiro;

import dao.DAOModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.financeiro.Financeiro;

/**
 *
 * @author Rafael
 */
public class DAOFinanceiro extends DAOModel {

    public DAOFinanceiro() {

    }

    public void inserir(Financeiro f) throws SQLException {
        conectar();
        String sql = "INSERT INTO financeiro(tipo_financeiro, documento, tipo_operacao, dt_financeiro, vlr_financeiro, credito, debito, documento_referencia, usuario_cria, empresa)VALUES(?,?,?,?,?,?,?,?,?,?)";

        this.setPs(this.getCon().prepareStatement(sql));

        this.getPs().setInt(1, f.getTipo_financeiro());
        this.getPs().setString(2, f.getDocumento());
        this.getPs().setInt(3, f.getTipo_operacao());
        this.getPs().setDate(4, f.getDt_financeiro());
        this.getPs().setDouble(5, f.getVlr_financeiro());
        this.getPs().setDouble(6, f.getCredito());
        this.getPs().setDouble(7, f.getDebito());
        this.getPs().setString(8, f.getDocumento_referencia());
        this.getPs().setInt(9, f.getUsuario_cria());
        this.getPs().setInt(10, f.getEmpresa());

        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();
    }

    public int retornaUltimaID() throws SQLException {
        int id_financeiro = 0;
        conectar();

        String sql = "SELECT max(id_financeiro) as maximo FROM financeiro";

        this.setPs(this.getCon().prepareStatement(sql));
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            id_financeiro = this.getRs().getInt("maximo");
        }

        id_financeiro++;

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return id_financeiro;
    }

    public List<Financeiro> contasAReceberPeriodo(int emp, java.sql.Date dt_ini, java.sql.Date dt_fim) throws SQLException {
        List<Financeiro> list = new ArrayList<Financeiro>();
        conectar();
        String sql = "SELECT CASE f.tipo_operacao WHEN '1' THEN 'GUIA' WHEN '2' THEN 'FRETE ENTRADA' WHEN '4' THEN 'FRETE SAÍDA' END as operacao, "
                + "	f.documento_referencia as referencia, "
                + "	f.documento as doc, "
                + "	f.dt_financeiro as dt, "
                + "	f.vlr_financeiro as vlr, "
                + "	f.debito as debito, "
                + "	e.nome_emp as empresa, "
                + "     f.id_financeiro as id "
                + "FROM    financeiro f, empresas e "
                + "WHERE   f.empresa = e.id_emp AND "
                + "        f.dt_financeiro BETWEEN ? AND ? AND "
                + "        e.id_emp = ? AND "
                + "        f.tipo_financeiro = 1 AND "
                + "        f.debito < f.credito "
                + "ORDER BY 4";

        this.setPs(this.getCon().prepareStatement(sql));

        this.getPs().setDate(1, dt_ini);
        this.getPs().setDate(2, dt_fim);
        this.getPs().setInt(3, emp);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            Financeiro f = new Financeiro();

            f.setStr_operacao(this.getRs().getString("operacao"));
            f.setDocumento_referencia(this.getRs().getString("referencia"));
            f.setDocumento(this.getRs().getString("doc"));
            f.setDt_financeiro(this.getRs().getDate("dt"));
            f.setVlr_financeiro(this.getRs().getDouble("vlr"));
            f.setDebito(this.getRs().getDouble("debito"));
            f.setStr_empresa(this.getRs().getString("empresa"));
            f.setId_financeiro(this.getRs().getInt("id"));

            list.add(f);
        }

        desconectar();

        return list;
    }
    
    public void atualizaDebito(double debito, int id_financeiro) throws SQLException{
        conectar();
        String sql = "UPDATE financeiro SET debito = ? WHERE id_financeiro = ?";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setDouble(1, debito);
        this.getPs().setInt(2, id_financeiro);
        
        this.getPs().execute();
        
        desconectar();
    }
    
    public Financeiro retornaDadosVlr(String doc_referencia) throws SQLException{
        Financeiro f = new Financeiro();
        conectar();
        String sql = "SELECT credito, debito FROM financeiro WHERE documento_referencia = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, doc_referencia);
        
        this.setRs(this.getPs().executeQuery());
        
        while(this.getRs().next()){
            f.setCredito(this.getRs().getDouble("credito"));
            f.setDebito(this.getRs().getDouble("debito"));
        }       
        
        desconectar();
        
        return f;
    }   
}
