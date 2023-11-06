package estoqueswing.dao.ordem;

public class OrdemSaidaDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS ordensSaida (" +
            "idOrdemSaida INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idDestinatario INTEGER," +
            "FOREIGN KEY (idDestinatario) REFERENCES clientes(idDestinatario)" +
            ")";
}
