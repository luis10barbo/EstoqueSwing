package estoqueswing.dao.historico;

import estoqueswing.dao.BancoDados;
import estoqueswing.dao.ordem.OrdemDAO;
import estoqueswing.model.historico.HistoricoOrdem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoricoOrdemDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS historicosOrdem (" +
            "idHistoricoOrdem INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idHistorico INTEGER," +
            "idOrdem INTEGER," +
            "FOREIGN KEY (idHistorico) REFERENCES historicos(idHistorico)," +
            "FOREIGN KEY (idOrdem) REFERENCES ordens(idOrdem)" +
            ")";

    public static HistoricoOrdem adiquirir(int idHistorico){
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idHistoricoOrdem,idHistorico,idOrdem FROM hisstoricoOrdem WHERE idHistoricoOrdem = ?");
            stmt.setInt(1,idHistorico);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return parseHistoricoOrdem(rs);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }
    private static HistoricoOrdem parseHistoricoOrdem(ResultSet rs) throws SQLException {
        HistoricoOrdem historicoOrdem = new HistoricoOrdem(OrdemDAO.adquirirOrdem(rs.getInt("idOrdem")));
        return historicoOrdem;
    }
}
