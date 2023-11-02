package estoqueswing.dao;

public class OrdemDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS ordens (" +
            "idOrdem INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idDestinatario INTEGER," +
            "idRemetente INTEGER," +
            "natureza VARCHAR(32)," +
            "valorProduto REAL," +
            "quantidadeProduto INTEGER DEFAULT 1," +
            "datetime VARCHAR(32)," +
            "FOREIGN KEY (idDestinatario) REFERENCES entidades(idEntidade)," +
            "FOREIGN KEY (idRemetente) REFERENCES entidades(idEntidade)" +
            ")";
}
