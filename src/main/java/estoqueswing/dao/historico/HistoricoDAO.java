package estoqueswing.dao.historico;

public class HistoricoDAO {
    public static String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS historicos (" +
            "idHistorico INTEGER PRIMARY KEY AUTOINCREMENT," +
            "tipoHistorico VARCHAR(16)" +
            ")";
}
