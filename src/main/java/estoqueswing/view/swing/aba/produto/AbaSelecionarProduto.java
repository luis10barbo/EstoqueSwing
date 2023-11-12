package estoqueswing.view.swing.aba.produto;

import estoqueswing.model.produto.Produto;
import estoqueswing.view.swing.componentes.Popup;

public class AbaSelecionarProduto extends AbaProdutos {

    private final Popup popup;
    private Produto produtoSelecionado;
    public AbaSelecionarProduto(Popup popup) {
        this.popup = popup;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produto) {
        this.produtoSelecionado = produto;
    }

    @Override
    public void cliqueSelecionarProduto(Produto produto) {
        setProdutoSelecionado(produto);
        popup.dispose();
    }

    @Override
    public TipoAbaProdutos getTipo() {
        return TipoAbaProdutos.Selecionar;
    }
}
