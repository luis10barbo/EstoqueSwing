package estoqueswing.controller.abas;

import estoqueswing.dao.EstoqueDAO;
import estoqueswing.dao.produto.ProdutoEstoqueDAO;
import estoqueswing.model.produto.Produto;
import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.estoque.AbaEstoque;
import estoqueswing.view.swing.aba.ordem.AbaCriarOrdem;

public class ControllerAbaEstoque {
    AbaEstoque abaEstoque;
    public ControllerAbaEstoque(AbaEstoque abaEstoque) {
        this.abaEstoque = abaEstoque;
    }

    public void cliqueApagarProduto(ProdutoEstoque produto) {
        ProdutoEstoqueDAO.remover(produto);
        abaEstoque.atualizarPagina();
    }
    public void cliqueEditarProduto(ProdutoEstoque produto) {
        // TODO: funcionalidade clique editar produto
        abaEstoque.atualizarPagina();
    }

    public void cliquePesquisar(String pesquisa) {
        // TODO: funcionalidade clique botao pesquisar
//        System.out.println("Pesquisando " + pesquisa);
//        ProdutoDAO.adquirirProdutos(pesquisa);
          abaEstoque.atualizarPagina();
    }

    public void cliqueBotaoCriarOrdem() {
        // TODO: funcionalidade clique botao criar produto
        JanelaPrincipal.adquirir().trocarAba(new AbaCriarOrdem(), false);
    }
}
