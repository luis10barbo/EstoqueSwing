package estoqueswing.view.swing.aba.produto;

import estoqueswing.model.produto.Produto;

public class AbaEditarProduto extends AbaCriarProduto {

    @Override
    public String textoBotaoConfirmar() {
        return "Editar";
    }

    public AbaEditarProduto(Produto produto) {
        setupInputs();
        atualizarPagina();
        this.produto = produto;
        criarProduto.setText("Editar");
    }
}
