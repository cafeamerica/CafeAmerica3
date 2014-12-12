/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.saidas;

import dao.DAOModel;
import java.sql.SQLException;
import model.entradas.ViagemEnt;
import model.saidas.ViagemSai;

/**
 *
 * @author Rafael
 */
public class DAOViagemSai extends DAOModel {

    public DAOViagemSai() {

    }

    public void inserir(ViagemSai vs) throws SQLException {
        conectar();
        String sql = "INSERT INTO viagem_sai(doc_viagem_sai, dt_sai, tipo_cafe_sai, qtd_sacas_sai, peso_sai, nome_motor_sai, placa_cam_sai, tipo_frete_sai,"
                + "              vlr_frete_unit, vlr_frete_total, usuario_cad_sai, num_nota, obs_sai)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        this.setPs(this.getCon().prepareStatement(sql));

        this.getPs().setString(1, vs.getDoc_viagem_sai());
        this.getPs().setDate(2, vs.getDt_sai());
        this.getPs().setInt(3, vs.getTipo_cafe_sai());
        this.getPs().setDouble(4, vs.getQtd_sacas_sai());
        this.getPs().setDouble(5, vs.getPeso_sai());
        this.getPs().setString(6, vs.getNome_motor_sai());
        this.getPs().setString(7, vs.getPlaca_cam_sai());
        this.getPs().setInt(8, vs.getTipo_frete_sai());
        this.getPs().setDouble(9, vs.getVlr_frete_unit());
        this.getPs().setDouble(10, vs.getVlr_frete_total());
        this.getPs().setString(11, vs.getUsuario_cad_sai());
        this.getPs().setString(12, vs.getNum_nota());
        this.getPs().setString(13, vs.getObs_sai());

        this.getPs().execute();

        this.getPs().close();

        this.getCon().close();
    }

    public void alterarNumNfe(String nfe, String doc_viagem) throws SQLException {
        conectar();
        String sql = "UPDATE viagem_sai SET num_nota = ? WHERE doc_viagem_sai = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, nfe);
        this.getPs().setString(2, doc_viagem);
        this.getPs().execute();

        this.getPs().close();
        this.getCon().close();
    }

    public ViagemSai retornaDadosInfo(String doc) throws SQLException {
        ViagemSai vs = new ViagemSai();
        conectar();
        String sql = "SELECT num_nota as nota, qtd_sacas_sai as sacas, qtd_sacas_sai*60 as peso FROM viagem_sai WHERE doc_viagem_sai = ?";
        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, doc);
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            vs.setNum_nota(this.getRs().getString("nota"));
            vs.setQtd_sacas_sai(this.getRs().getDouble("sacas"));
            vs.setPeso_sai(this.getRs().getDouble("peso"));
        }

        desconectar();

        return vs;
    }

    public String retornaUltimoDocViagemGuia(int id_guia) throws SQLException {
        conectar();
        String docx = "";
        String sql = "SELECT (SELECT veee.doc_viagem_sai as doc FROM viagem_sai veee WHERE veee.id_viagem_sai_guia = "
                + "(SELECT MAX(vee.id_viagem_sai_guia) "
                + "FROM viagem_sai vee, guia_viagem_sai gvee WHERE gvee.id_guia_viagem_sai = vee.id_viagem_sai_guia "
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

    public ViagemSai retornaDadosNfe(String num_nota) throws SQLException {
        ViagemSai vs = new ViagemSai();
        conectar();
        String sql = "SELECT vs.dt_sai as dt, vs.doc_viagem_sai as doc, CASE vs.tipo_cafe_sai WHEN '0' THEN 'BENEFICIADO' END as tipo_cafe, vs.qtd_sacas_sai as vol_real, vs.peso_sai as peso_real, ns.qtd_sacas_nfe_sai as vol_fiscal, ns.peso_nfe_sai as peso_fiscal, g.num_guia as num_guia "
                + "                FROM viagem_sai vs, nfe_sai ns, guia_viagem_sai gvs, guias g "
                + "                WHERE vs.num_nota = ns.num_nota AND "
                + "		      gvs.id_guia_viagem_sai = vs.id_viagem_sai_guia AND "
                + "		      gvs.id_guia = g.id_guia AND "
                + "                     vs.num_nota = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setString(1, num_nota);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            vs.setDoc_viagem_sai(this.getRs().getString("doc"));
            vs.setDt_sai(this.getRs().getDate("dt"));
            vs.setStr_tipo_cafe(this.getRs().getString("tipo_cafe"));
            vs.setQtd_sacas_sai(this.getRs().getDouble("vol_real"));
            vs.setPeso_sai(this.getRs().getDouble("peso_real"));
            vs.setVol_fiscal(this.getRs().getDouble("vol_fiscal"));
            vs.setPeso_fiscal(this.getRs().getDouble("peso_fiscal"));
            vs.setNum_guia(this.getRs().getString("num_guia"));
        }

        desconectar();

        return vs;
    }

}
