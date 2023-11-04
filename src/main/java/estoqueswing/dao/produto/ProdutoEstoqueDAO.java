package estoqueswing.dao.produto;

public class ProdutoEstoqueDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS produtosEstoque (" +
            "idProdutoEstoque INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idEstoque INTEGER," +
            "idProduto INTEGER," +
            "valorGasto REAL DEFAULT 0," +
            "valorGanho REAL DEFAULT 0," +
            "valorVenda REAL," +
            "quantidade INTEGER," +
            "FOREIGN KEY (idEstoque) REFERENCES estoques(idEstoque) ON DELETE CASCADE," +
            "FOREIGN KEY (idProduto) REFERENCES produtos(idProduto)" +
            ")";
}
