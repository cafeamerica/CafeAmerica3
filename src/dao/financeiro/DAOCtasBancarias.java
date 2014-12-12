/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.financeiro;

import dao.DAOModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.financeiro.CtasBancarias;

/**
 *
 * @author Rafael
 */
public class DAOCtasBancarias extends DAOModel {

    public DAOCtasBancarias() {

    }

    public void inserir(CtasBancarias cb) throws SQLException {
        conectar();
        String sql = "INSERT INTO ctas_bancarias(banco,agencia,titular,numero,tipo_conta,saldo)VALUES(?,?,?,?,?,?)";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, cb.getBanco());
        this.getPs().setString(2, cb.getAgencia());
        this.getPs().setString(3, cb.getTitular());
        this.getPs().setString(4, cb.getNumero());
        this.getPs().setString(5, cb.getTipo_conta());
        this.getPs().setDouble(6, cb.getSaldo());

        this.getPs().execute();

        desconectar();

    }

    public boolean consultaCtaExiste(String titular, String numero) throws SQLException {
        boolean condicao = false;
        conectar();
        String sql = "SELECT * FROM ctas_bancarias WHERE titular = ? AND numero = ?";
        
        this.setPs(this.getCon().prepareStatement(sql));
        
        this.getPs().setString(1, titular);
        this.getPs().setString(2, numero);
        
        this.setRs(this.getPs().executeQuery());
        
        while(this.getRs().next()){
            condicao = true;
        }
        
        desconectar();

        return condicao;
    }

    

}
