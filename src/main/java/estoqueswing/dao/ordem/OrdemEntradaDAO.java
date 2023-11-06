package estoqueswing.dao.ordem;

public class OrdemEntradaDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS ordemEntrada(" +
            "idOrdemEntrada INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idFornecedor INTEGER," +
            "FOREIGN KEY (idFornecedor) REFERENCES fornecedores(idFornecedor)" +
            ")";
}
