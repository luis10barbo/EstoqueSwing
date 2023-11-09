package estoqueswing.controller.abas.produto;

import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.model.produto.Produto;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.produto.AbaCriarProduto;
import estoqueswing.view.swing.aba.produto.AbaEditarProduto;
import estoqueswing.view.swing.aba.produto.AbaProdutos;

public class ControllerAbaProdutos {
    AbaProdutos abaProdutos;
    public ControllerAbaProdutos(AbaProdutos abaProdutos) {
        this.abaProdutos = abaProdutos;
    }
    public void cliqueCriarProduto() {
        // TODO: implementar clique criar produto
        JanelaPrincipal.adquirir().trocarAba(new AbaCriarProduto(), false);
    }

    public void cliqueRemoverProduto(Produto produto) {
        ProdutoDAO.removerProduto(produto);
        abaProdutos.atualizarPagina();
    }

    public void cliqueEditarProduto(Produto produto) {
        // TODO: implementar clique editar produto
        JanelaPrincipal.adquirir().trocarAba(new AbaEditarProduto(produto), false);
    }
}
