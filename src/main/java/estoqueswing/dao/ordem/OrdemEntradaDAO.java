package estoqueswing.dao.ordem;

public class OrdemEntradaDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS ordemEntrada(" +
            "idOrdemEntrada INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idOrdem INTEGER," +
            "idFornecedor INTEGER," +
            "FOREIGN KEY (idFornecedor) REFERENCES fornecedores(idFornecedor)," +
            "FOREIGN KEY (idOrdem) REFERENCES ordens(idOrdem) ON DELETE CASCADE" +
            ")";
}
