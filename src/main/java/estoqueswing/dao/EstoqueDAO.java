package estoqueswing.dao;

public class EstoqueDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS estoques (" +
            "idEstoque INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idEndereco INTEGER," +
            "nome VARCHAR(32)," +
            "descricao TEXT," +
            "FOREIGN KEY (idEndereco) REFERENCES enderecos(idEndereco)" +
            ")";
}
