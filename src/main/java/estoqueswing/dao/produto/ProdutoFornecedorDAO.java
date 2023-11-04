package estoqueswing.dao.produto;

public class ProdutoFornecedorDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS produtosFornecedor (" +
            "idProdutoFornecedora INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idFornecedor INTEGER," +
            "idProduto INTEGER," +
            "valorProduto REAL," +
            "FOREIGN KEY (idFornecedor) REFERENCES fornecedores(idFornecedor) ON DELETE CASCADE," +
            "FOREIGN KEY (idProduto) REFERENCES produtos(idProduto)" +
            ")";
}
