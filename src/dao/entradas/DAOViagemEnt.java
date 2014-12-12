/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entradas;

import dao.DAOModel;
import java.sql.SQLException;
import model.entradas.ViagemEnt;

/**
 *
 * @author Rafael
 */
public class DAOViagemEnt extends DAOModel {

    public DAOViagemEnt() {

    }

    public void inserir(ViagemEnt ve) throws SQLException {
        conectar();
        String sql = "INSERT INTO viagem_ent(doc_viagem_ent, dt_ent, tipo_cafe_ent, qtd_sacas_ent, peso_ent, nome_motor_ent, "
                + "placa_cam_ent, tipo_frete_ent,  vlr_frete_unit, vlr_frete_total, usuario_cad_ent, num_nota, obs_sai)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ";

        this.setPs(this.getCon().prepareStatement(sql));

        this.getPs().setString(1, ve.getDoc_viagem_ent());
        this.getPs().setDate(2, ve.getDt_ent());
        this.getPs().setString(3, ve.getTipo_cafe_ent());
        this.getPs().setDouble(4, ve.getQtd_sacas_ent());
        this.getPs().setDouble(5, ve.getPeso_ent());
        this.getPs().setString(6, ve.getNome_motor_ent());
        this.getPs().setString(7, ve.getPlaca_cam_ent());
        this.getPs().setInt(8, ve.getTipo_frete_ent());
        this.getPs().setDouble(9, ve.getVlr_frete_unit());
        this.getPs().setDouble(10, ve.getVlr_frete_total());
        this.getPs().setString(11, ve.getUsuario_cad_ent());
        this.getPs().setString(12, ve.getNum_nota());
        this.getPs().setString(13, ve.getObs_ent());

        this.getPs().execute();

        this.getPs().close();

        this.getCon().close();
    }

    public void alterarNumNfe(String nfe, String doc_viagem) throws SQLException {
        conectar();
        String sql = "UPDATE viagem_ent SET num_nota = ? WHERE doc_viagem_ent = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, nfe);
        this.getPs().setString(2, doc_viagem);
        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();
    }

    public String retornaUltimoDocViagemGuia(int id_guia) throws SQLException {
        conectar();
        String docx = "";
        String sql = "SELECT (SELECT veee.doc_viagem_ent as doc FROM viagem_ent veee WHERE veee.id_viagem_ent_guia = "
                + "(SELECT MAX(vee.id_viagem_ent_guia) "
                + "FROM viagem_ent vee, guia_viagem_ent gvee WHERE gvee.id_guia_viagem_ent = vee.id_viagem_ent_guia "
                + "AND gvee.id_guia = ? "
                + "ORDER BY 1 DESC LIMIT 1)) as doc";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            docx = this.getRs().getString("doc");
        }

        desconectar();

        return docx;
    }

    public ViagemEnt retornaDadosInfo(String doc) throws SQLException {
        ViagemEnt ve = new ViagemEnt();
        conectar();
        String sql = "SELECT num_nota as nota, qtd_sacas_ent as sacas, qtd_sacas_ent*60 as peso FROM viagem_ent WHERE doc_viagem_ent = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, doc);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            ve.setNum_nota(this.getRs().getString("nota"));
            ve.setQtd_sacas_ent(this.getRs().getDouble("sacas"));
            ve.setPeso_ent(this.getRs().getDouble("peso"));
        }

        desconectar();

        return ve;
    }

    public ViagemEnt retornaDadosNfe(String num_nota) throws SQLException {
        ViagemEnt ve = new ViagemEnt();
        conectar();
        String sql = "SELECT ve.dt_ent as dt, ve.doc_viagem_ent as doc, ve.tipo_cafe_ent as tipo_cafe, ve.qtd_sacas_ent as vol_real, ve.peso_ent as peso_real, ne.qtd_sacas_nfe_ent as vol_fiscal, ne.peso_nfe_ent as peso_fiscal, g.num_guia as num_guia "
                + "FROM viagem_ent ve, nfe_ent ne, guia_viagem_ent gve, guias g "
                + "WHERE ve.num_nota = ne.num_nota AND "
                + "      ve.id_viagem_ent_guia = gve.id_guia_viagem_ent AND "
                + "      gve.id_guia = g.id_guia AND"
                + "      ve.num_nota = ?";
        
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, num_nota);
        
        this.setRs(this.getPs().executeQuery());
        
        while(this.getRs().next()){
            ve.setDoc_viagem_ent(this.getRs().getString("doc"));
            ve.setDt_ent(this.getRs().getDate("dt"));
            ve.setTipo_cafe_ent(this.getRs().getString("tipo_cafe"));
            ve.setQtd_sacas_ent(this.getRs().getDouble("vol_real"));
            ve.setPeso_ent(this.getRs().getDouble("peso_real"));
            ve.setVol_fiscal(this.getRs().getDouble("vol_fiscal"));
            ve.setPeso_fiscal(this.getRs().getDouble("peso_fiscal"));
            ve.setNum_guia(this.getRs().getString("num_guia"));
        }
        
        desconectar();

        return ve;
    }
    
   

}
