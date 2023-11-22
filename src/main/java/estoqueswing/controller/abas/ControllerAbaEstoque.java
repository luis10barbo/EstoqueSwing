package estoqueswing.controller.abas;

import estoqueswing.dao.produto.ProdutoEstoqueDAO;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.estoque.AbaEstoque;
import estoqueswing.view.swing.aba.ordem.AbaCriarOrdem;
import estoqueswing.view.swing.aba.ordem.AbaOrdens;
import estoqueswing.view.swing.aba.produto.AbaEditarProdutoEstoque;
import estoqueswing.view.swing.componentes.Popup;

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
        Popup popup = JanelaPrincipal.adquirir().criarPopup();
        popup.adicionarAba(new AbaEditarProdutoEstoque(popup, produto)).mostrar();
        abaEstoque.atualizarPagina();
    }

    public void cliquePesquisar(String pesquisa) {
        // TODO: funcionalidade clique botao pesquisar
//        System.out.println("Pesquisando " + pesquisa);
//        ProdutoDAO.adquirirProdutos(pesquisa);
          abaEstoque.atualizarPagina();
    }

    public void cliqueBotaoOrdensEstoque() {
        // TODO: funcionalidade clique botao criar produto
        JanelaPrincipal.adquirir().trocarAba(new AbaOrdens(), false);
    }
}
