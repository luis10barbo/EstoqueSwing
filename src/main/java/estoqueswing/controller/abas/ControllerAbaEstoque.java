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
        ProdutoDAO.removerProduto(produto);
        abaEstoque.atualizarProdutosPagina();

    }
    public void cliqueEditarProduto(Produto produto) {
        // TODO: funcionalidade clique editar produto
        System.out.println("Editar produto " + produto);
        abaEstoque.atualizarProdutosPagina();
    }

    public void cliquePesquisar(String pesquisa) {
        // TODO: funcionalidade clique botao pesquisar
//        System.out.println("Pesquisando " + pesquisa);
//        ProdutoDAO.adquirirProdutos(pesquisa);
          abaEstoque.atualizarProdutosPagina();
    }

    public void cliqueBotaoCriarProduto() {
        // TODO: funcionalidade clique botao criar produto
        System.out.println("Criar produto");
        ProdutoDAO.adicionarProduto(new Produto("Caneta Vermelha", "Caneta utilizada para zerar provas", 1, 34.50));
        abaEstoque.atualizarProdutosPagina();
    }
}
