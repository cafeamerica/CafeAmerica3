/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.saidas;

import dao.DAOModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.saidas.Resumo;

/**
 *
 * @author Rafael
 */
public class DAOResumo extends DAOModel {

    public DAOResumo() {

    }

    public List<Resumo> resumoGeral(int id_guia) throws SQLException {
        List<Resumo> list = new ArrayList<Resumo>();
        conectar();
        String sql = "SELECT s.desc_serv as desc, vs.qtd_sacas_sai as sacas, vs.peso_sai  as peso "
                + "FROM guias g, guia_viagem_sai gvs, viagem_sai vs, tipo_cafe_viagem tc, servicos s "
                + "WHERE g.id_guia = gvs.id_guia AND "
                + "      vs.id_viagem_sai_guia = gvs.id_guia_viagem_sai AND "
                + "      s.id_serv = tc.tipo_cafe  AND"
                + "      tc.id_guia = g.id_guia AND"
                + "      tc.tipo_viagem = 2 AND"
                + "      tc.id_viagem = gvs.id_viagem AND "
                + "      g.id_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));

        this.getPs().setInt(1, id_guia);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            Resumo r = new Resumo();

            r.setDesc_serv_sai(this.getRs().getString("desc"));
            r.setQtd_sacas_sai(this.getRs().getDouble("sacas"));
            r.setPeso_sai(this.getRs().getDouble("peso"));

            list.add(r);
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return list;
    }

    public List<Resumo> diferencaEmb(int id_guia) throws SQLException {
        List<Resumo> list = new ArrayList<Resumo>();
        conectar();
        String sql = "SELECT CASE ceev.id_tipo_emb WHEN 1 THEN 'ENTRADA EM BAG' WHEN 3 THEN 'ENTRADA EM SACARIA' ELSE 'NULO' END as tipo, CASE ceev.id_tipo_emb WHEN 1 THEN SUM(ceev.qtd_emb) WHEN 3 THEN SUM(ceev.qtd_emb) ELSE 0 END as qtd ,CASE ceev.id_tipo_emb WHEN 1 THEN SUM(ceev.qtd_emb)*5 WHEN 3 THEN SUM(ceev.qtd_emb)*0.5 ELSE 0 END as kilos "
                + "FROM guias g, ctrl_emb_viagem_ent ceev "
                + "WHERE g.id_guia = ceev.id_guia AND "
                + "      g.id_guia = ? "
                + "GROUP BY ceev.id_tipo_emb "
                + "	UNION ALL "
                + "SELECT CASE ceev.id_tipo_emb WHEN 1 THEN 'SAIDA EM BAG' WHEN 3 THEN 'SAIDA EM SACARIA' ELSE 'NULO' END as tipo, CASE ceev.id_tipo_emb WHEN 1 THEN SUM(ceev.qtd_emb) WHEN 3 THEN SUM(ceev.qtd_emb) ELSE 0 END as qtd ,CASE ceev.id_tipo_emb WHEN 1 THEN SUM(ceev.qtd_emb)*5 WHEN 3 THEN SUM(ceev.qtd_emb)*0.5 ELSE 0 END as kilos "
                + "FROM guias g, ctrl_emb_viagem_sai ceev "
                + "WHERE g.id_guia = ceev.id_guia AND "
                + "      g.id_guia = ? "
                + "GROUP BY ceev.id_tipo_emb ";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);
        this.getPs().setInt(2, id_guia);

        this.setRs(this.getPs().executeQuery());

        while (this.getRs().next()) {
            Resumo r = new Resumo();

            r.setTipo_op(this.getRs().getString("tipo"));
            r.setQtd(this.getRs().getDouble("qtd"));
            r.setKilos(this.getRs().getDouble("kilos"));

            list.add(r);
        }

        this.getPs().close();
        this.getRs().close();
        this.getCon().close();

        return list;
    }

    public double quebra(int id_guia) throws SQLException {
        double quebra = 0;
        conectar();
        String sql = "SELECT (SELECT SUM(ve.peso_ent) FROM viagem_ent ve, guia_viagem_ent gve WHERE ve.id_viagem_ent_guia = gve.id_guia_viagem_ent AND gve.id_guia = g.id_guia)-(SELECT COALESCE(SUM(vs.peso_sai),0) FROM viagem_sai vs, guia_viagem_sai gvs WHERE vs.id_viagem_sai_guia = gvs.id_guia_viagem_sai AND gvs.id_guia = g.id_guia) as quebra "
                + "FROM guias g "
                + "WHERE g.id_guia = ?";

        this.setPs(this.getCon().prepareStatement(sql));
        this.getPs().setInt(1, id_guia);
        
        this.setRs(this.getPs().executeQuery());
        
        while(this.getRs().next()){
            quebra = this.getRs().getDouble("quebra");              
        }
        
        this.getPs().close();
        this.getCon().close();
        this.getRs().close();
        
        return quebra;
    }
}
