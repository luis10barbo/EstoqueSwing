package estoqueswing.controller.abas.produto;

import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.dao.produto.ProdutoFornecedorDAO;
import estoqueswing.exceptions.produto.ExcecaoValorInvalidoProdutoOrdem;
import estoqueswing.exceptions.produto.ExcecaoValorVazioProdutoOrdem;
import estoqueswing.model.produto.Produto;
import estoqueswing.model.produto.ProdutoFornecedor;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.produto.AbaCriarProduto;
import estoqueswing.view.swing.aba.produto.AbaEditarProduto;

public class ControllerAbaEditarProduto {
    private AbaEditarProduto aba;

    public ControllerAbaEditarProduto(AbaEditarProduto aba) {
        this.aba = aba;
    }

    public void cliqueEditarProduto(Produto produto, ProdutoFornecedor[] produtoFornecedores, ProdutoFornecedor[] produtoFornecedoresRemovidos) {
        for (ProdutoFornecedor produtoFornecedor: produtoFornecedores) {
            try {
                produtoFornecedor.setValorProduto(Double.parseDouble(produtoFornecedor.getValorProdutoNaoTratado()));
            } catch (NumberFormatException ex) {
                if (produtoFornecedor.getValorProdutoNaoTratado().isEmpty()) throw new ExcecaoValorVazioProdutoOrdem(produtoFornecedor);

                throw  new ExcecaoValorInvalidoProdutoOrdem(produtoFornecedor);
            }

            if (produtoFornecedor.getId() == 0) {
                ProdutoFornecedorDAO.criar(produtoFornecedor);
            } else {
                ProdutoFornecedorDAO.editar(produtoFornecedor);
            }
        }

        for (ProdutoFornecedor produtoFornecedor: produtoFornecedoresRemovidos) {
            ProdutoFornecedorDAO.remover(produtoFornecedor);
        }

        ProdutoDAO.editarProduto(produto);
        JanelaPrincipal.adquirir().voltarAba();
    }
}
