package estoqueswing.controller.abas;

import estoqueswing.model.Produto;
import estoqueswing.dao.ProdutoDAO;
import estoqueswing.view.swing.aba.AbaEstoque;

public class ControllerAbaEstoque {
    AbaEstoque abaEstoque;
    public ControllerAbaEstoque(AbaEstoque abaEstoque) {
        this.abaEstoque = abaEstoque;
    }

    public void cliqueApagarProduto(Produto produto) {
        // TODO: funcionalidade clique apagar produto
        System.out.println("Apagar produto " + produto);
        ProdutoDAO.removerProduto(produto);
    }
    public void cliqueEditarProduto(Produto produto) {
        // TODO: funcionalidade clique editar produto
        System.out.println("Editar produto " + produto);

    }

    public void cliquePesquisar(String pesquisa) {
        // TODO: funcionalidade clique botao pesquisar
//        System.out.println("Pesquisando " + pesquisa);
        ProdutoDAO.adquirirProdutos(pesquisa);
    }

    public void cliqueBotaoCriarProduto() {
        // TODO: funcionalidade clique botao criar produto
        System.out.println("Criar produto");
    }
}
