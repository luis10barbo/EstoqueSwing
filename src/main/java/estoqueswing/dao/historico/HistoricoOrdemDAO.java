package estoqueswing.dao.historico;

public class HistoricoOrdemDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS historicosOrdem (" +
            "idHistoricoOrdem INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idHistorico INTEGER," +
            "idOrdem INTEGER," +
            "FOREIGN KEY (idHistorico) REFERENCES historicos(idHistorico)," +
            "FOREIGN KEY (idOrdem) REFERENCES ordens(idOrdem)" +
            ")";
}
