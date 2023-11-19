package estoqueswing.dao.historico;

import estoqueswing.model.historico.Historico;
import estoqueswing.model.historico.HistoricoOrdem;
import estoqueswing.model.historico.TipoHistorico;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoricoDAO {
    public static String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS historicos (" +
            "idHistorico INTEGER PRIMARY KEY AUTOINCREMENT," +
            "tipoHistorico VARCHAR(16)" +
            ")";

    public static Historico parseHistorico (ResultSet rs) throws SQLException{
        Historico historico =null;
        if (rs.getString("tipoHistorico")==TipoHistorico.Ordem.toString()){
            historico = HistoricoOrdemDAO.adiquirir(rs.getInt("idHistorico"));
        }
        return historico;
    }


}
