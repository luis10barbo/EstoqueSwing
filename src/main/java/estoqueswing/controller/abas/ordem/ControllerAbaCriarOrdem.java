package estoqueswing.controller.abas.ordem;

import estoqueswing.controller.abas.produto.ControllerAbaCriarProduto;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.view.swing.aba.Aba;

public class ControllerAbaCriarOrdem {
    Aba aba;
    public ControllerAbaCriarOrdem(Aba aba) {
        this.aba = aba;
    }

    public void cliqueCriarOrdem(Ordem ordem, ProdutoOrdem[] produtosOrdem) {
        aba.atualizarPagina();
    }

    public void cliqueAdicionarProdutoOrdem() {

    }


}
