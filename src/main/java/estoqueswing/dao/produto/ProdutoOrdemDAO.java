package estoqueswing.dao.produto;

public class ProdutoOrdemDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS produtoOrdem (" +
            "idProdutoOrdem INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idProduto INTEGER," +
            "idOrdem INTEGER," +
            "quantidade INTEGER," +
            "valorProduto REAL," +
            "FOREIGN KEY (idProduto) REFERENCES produtos(idProduto)," +
            "FOREIGN KEY (idOrdem) REFERENCES ordens(idOrdem) ON DELETE CASCADE" +
            ")";
}
