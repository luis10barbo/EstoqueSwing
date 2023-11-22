package estoqueswing.exceptions.produto;

import estoqueswing.exceptions.ExcecaoBase;
import estoqueswing.model.produto.ProdutoFornecedor;

public class ExcecaoValorVazioProdutoOrdem extends ExcecaoBase {
    public ExcecaoValorVazioProdutoOrdem(ProdutoFornecedor produtoFornecedor) {
        super("Valor de compra do produto pelo fornecedor " + produtoFornecedor.getFornecedor().getNome() + " nao pode ser vazio!");
    }
}
