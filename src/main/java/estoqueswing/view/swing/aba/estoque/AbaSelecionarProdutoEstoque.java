package estoqueswing.view.swing.aba.estoque;

import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.view.swing.componentes.Popup;

public class AbaSelecionarProdutoEstoque extends AbaEstoque {
    private final Popup popup;
    private ProdutoEstoque produtoSelecionado;

    public AbaSelecionarProdutoEstoque(Popup popup) {
        this.popup = popup;
    }

    @Override
    public TipoAbaEstoque getTipo() {
        return TipoAbaEstoque.Selecionar;
    }

    public ProdutoEstoque getProdutoSelecionado() {
        return produtoSelecionado;
    }
    @Override
    public void cliqueSelecionarProduto(ProdutoEstoque produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
        popup.dispose();
    }
}
