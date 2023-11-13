package estoqueswing.view.swing.aba.produto;

import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.produto.Produto;
import estoqueswing.view.swing.componentes.Popup;

public class AbaSelecionarProduto extends AbaProdutos {

    private final Popup popup;
    private final Fornecedor fornecedor;
    private Produto produtoSelecionado;

    public AbaSelecionarProduto(Popup popup, Fornecedor fornecedor) {
        this.popup = popup;
        this.fornecedor = fornecedor;
        setTitulo("Produtos de fornecedor " + fornecedor.getNome());
        atualizarPagina();
    }
    public AbaSelecionarProduto(Popup popup) {
        this.popup = popup;
        this.fornecedor = null;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produto) {
        this.produtoSelecionado = produto;
    }


    @Override
    public Produto[] getProdutos() {
        if (fornecedor != null)
            return ProdutoDAO.adquirirProdutos(getPesquisa(), fornecedor);
        else
            return super.getProdutos();
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
