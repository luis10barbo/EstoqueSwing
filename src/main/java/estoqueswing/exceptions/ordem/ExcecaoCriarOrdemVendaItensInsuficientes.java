package estoqueswing.exceptions.ordem;

import estoqueswing.exceptions.ExcecaoBase;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.model.produto.ProdutoOrdem;

public class ExcecaoCriarOrdemVendaItensInsuficientes extends ExcecaoBase {
    public ExcecaoCriarOrdemVendaItensInsuficientes(ProdutoOrdem produtoOrdem, ProdutoEstoque produtoEstoque) {
        super("O numero de " + produtoEstoque.getProduto().getNome() + " no Estoque e insuficiente para realizar essa venda. Voce tentou vender " + produtoOrdem.getQuantidade() + ", Estoque tem " + produtoEstoque.getQuantidade() + "!");
    }
}
