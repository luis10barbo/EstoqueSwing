package estoqueswing.dao.ordem;

public class OrdemSaidaDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS ordensSaida (" +
            "idOrdemSaida INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idOrdem INTEGER," +
            "idDestinatario INTEGER," +
            "FOREIGN KEY (idDestinatario) REFERENCES clientes(idDestinatario)," +
            "FOREIGN KEY (idOrdem) REFERENCES ordens(idOrdem) ON DELETE CASCADE" +

            ")";
}
