/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entradas;

import dao.DAOModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.entradas.NfeModel;

/**
 *
 * @author Rafael
 */
public class DAONfeModel extends DAOModel {

    public DAONfeModel() {

    }

    public List<NfeModel> listaDadosPendentes() throws SQLException {
        conectar();
        List<NfeModel> list = new ArrayList<NfeModel>();

        String sql = "SELECT id_nfe_ent as id, 'E' as tipo, g.num_guia as num_guia, g.nosso_num_guia as nosso_num, ve.doc_viagem_ent as doc_viagem, ve.nome_motor_ent as motorista, ve.peso_ent as peso, ve.dt_ent as dt "
                + "FROM nfe_ent ne, guia_viagem_ent gve, viagem_ent ve, guias g "
                + "WHERE ne.id_nfe_ent = ve.id_viagem_ent_guia AND "
                + "      ve.id_viagem_ent_guia = gve.id_guia_viagem_ent AND "
                + "      gve.id_guia = g.id_guia AND "
                + "      ne.num_nota LIKE '%PENDENTE%' "
                + "     UNION ALL "
                + "SELECT id_nfe_sai as id, 'S' as tipo, g.num_guia as num_guia, g.nosso_num_guia as nosso_num, vs.doc_viagem_sai as doc_viagem, vs.nome_motor_sai as motorista, vs.peso_sai as peso, vs.dt_sai as dt "
                + "FROM nfe_sai ns, guia_viagem_sai gvs, viagem_sai vs, guias g "
                + "WHERE ns.id_nfe_sai = vs.id_viagem_sai_guia AND "
                + "      vs.id_viagem_sai_guia = gvs.id_guia_viagem_sai AND "
                + "      gvs.id_guia = g.id_guia AND "
                + "      ns.num_nota LIKE '%PENDENTE%' ";

        this.setPs(this.getCon().prepareStatement(sql));
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            NfeModel nm = new NfeModel();

            nm.setDoc_viagem(this.getRs().getString("doc_viagem"));
            nm.setId(this.getRs().getInt("id"));
            nm.setMotorista(this.getRs().getString("motorista"));
            nm.setNosso_num(this.getRs().getString("nosso_num"));
            nm.setNum_guia(this.getRs().getString("num_guia"));
            nm.setPeso(this.getRs().getDouble("peso"));
            nm.setTipo(this.getRs().getString("tipo"));
            nm.setDt(this.getRs().getDate("dt"));

            list.add(nm);
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return list;
    }

    public List<NfeModel> listaDadosConferencia() throws SQLException {
        conectar();
        List<NfeModel> list = new ArrayList<NfeModel>();

        String sql = "SELECT id_nfe_ent as id, 'E' as tipo, g.num_guia as num_guia, g.nosso_num_guia as nosso_num, ve.doc_viagem_ent as doc_viagem, ve.nome_motor_ent as motorista, ve.peso_ent as peso, ve.dt_ent as dt, ve.qtd_sacas_ent as sacas_real, ne.num_nota as num_nota "
                + "FROM nfe_ent ne, guia_viagem_ent gve, viagem_ent ve, guias g "
                + "WHERE ne.id_nfe_ent = ve.id_viagem_ent_guia AND "
                + "      ve.id_viagem_ent_guia = gve.id_guia_viagem_ent AND "
                + "      gve.id_guia = g.id_guia AND "
                + "      ne.status_nfe = 0 AND "
                + "      ne.num_nota NOT LIKE '%PENDENTE%'"
                + "     UNION ALL "
                + "SELECT id_nfe_sai as id, 'S' as tipo, g.num_guia as num_guia, g.nosso_num_guia as nosso_num, vs.doc_viagem_sai as doc_viagem, vs.nome_motor_sai as motorista, vs.peso_sai as peso, vs.dt_sai as dt, vs.qtd_sacas_sai as sacas_real, ns.num_nota as num_nota "
                + "FROM nfe_sai ns, guia_viagem_sai gvs, viagem_sai vs, guias g "
                + "WHERE ns.id_nfe_sai = vs.id_viagem_sai_guia AND "
                + "      vs.id_viagem_sai_guia = gvs.id_guia_viagem_sai AND "
                + "      gvs.id_guia = g.id_guia AND "
                + "      ns.status_nfe = 0 AND "
                + "      ns.num_nota NOT LIKE '%PENDENTE%'";

        this.setPs(this.getCon().prepareStatement(sql));
        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            NfeModel nm = new NfeModel();

            nm.setDoc_viagem(this.getRs().getString("doc_viagem"));
            nm.setId(this.getRs().getInt("id"));
            nm.setMotorista(this.getRs().getString("motorista"));
            nm.setNosso_num(this.getRs().getString("nosso_num"));
            nm.setNum_guia(this.getRs().getString("num_guia"));
            nm.setPeso(this.getRs().getDouble("peso"));
            nm.setTipo(this.getRs().getString("tipo"));
            nm.setDt(this.getRs().getDate("dt"));
            nm.setQtd_sacas_real(this.getRs().getInt("sacas_real"));
            nm.setNum_nota(this.getRs().getString("num_nota"));
            list.add(nm);
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return list;
    }

}
