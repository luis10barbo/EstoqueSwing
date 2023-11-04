package estoqueswing.dao.produto;

public class ProdutoOrdemDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS produtoOrdem (" +
            "idProdutoOrdem INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idProduto INTEGER," +
            "quantidade INTEGER," +
            "valorProduto REAL" +
            ")";
}
