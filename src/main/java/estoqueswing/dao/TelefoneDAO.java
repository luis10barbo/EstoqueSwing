package estoqueswing.dao;

public class TelefoneDAO {
    public static String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS telefones (" +
            "idTelefone INTEGER PRIMARY KEY AUTOINCREMENT," +
            "ddd VARCHAR(4)," +
            "numero VARCHAR(12)," +
            "tipo VARCHAR(16)" +
            ")";
}
