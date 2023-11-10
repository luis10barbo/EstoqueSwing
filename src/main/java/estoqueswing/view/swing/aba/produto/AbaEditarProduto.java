package estoqueswing.view.swing.aba.produto;

import estoqueswing.controller.abas.produto.ControllerAbaEditarProduto;
import estoqueswing.model.produto.Produto;
import estoqueswing.model.produto.ProdutoFornecedor;

import java.awt.event.MouseEvent;

public class AbaEditarProduto extends AbaCriarProduto {
    private ControllerAbaEditarProduto controller = new ControllerAbaEditarProduto(this);
    @Override
    public String textoBotaoConfirmar() {
        return "Editar";
    }

    public AbaEditarProduto(Produto produto) {
        this.produto = produto;
        atualizarInformacoesDB();
        setupInputs();
        atualizarPagina();
        criarProduto.setText("Editar");
    }

    @Override
    public void cliqueConfirmar(Produto produto, ProdutoFornecedor[] produtoFornecedores, ProdutoFornecedor[] produtoFornecedoresRemovidos) {
        controller.cliqueEditarProduto(produto, produtoFornecedores, produtoFornecedoresRemovidos);
    }

    @Override
    public String getTitulo() {
        return "Editar Produto";
    }
}
