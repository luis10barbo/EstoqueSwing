package estoqueswing.exceptions.produto;

import estoqueswing.exceptions.ExcecaoBase;
import estoqueswing.model.produto.ProdutoFornecedor;

public class ExcecaoValorInvalidoProdutoOrdem extends ExcecaoBase {
    public ExcecaoValorInvalidoProdutoOrdem(ProdutoFornecedor produtoFornecedor) {
        super("Valor de compra invalido para fornecedor " + produtoFornecedor.getFornecedor().getNome());
    }
}
