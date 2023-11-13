package estoqueswing.controller.abas.produto;

import estoqueswing.dao.produto.ProdutoEstoqueDAO;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.view.swing.aba.produto.AbaEditarProdutoEstoque;

public class ControllerAbaEditarProdutoEstoque {
    private final AbaEditarProdutoEstoque aba;

    public ControllerAbaEditarProdutoEstoque(AbaEditarProdutoEstoque aba) {
        this.aba = aba;
    }
    public void cliqueEditar(ProdutoEstoque produtoEstoque) {
        ProdutoEstoqueDAO.editar(produtoEstoque);
        if (aba.getPopup() != null) aba.getPopup().dispose();
    }
}
