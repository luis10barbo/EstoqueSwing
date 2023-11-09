package estoqueswing.controller.abas.ordem;

import estoqueswing.controller.abas.produto.ControllerAbaCriarProduto;
import estoqueswing.dao.ordem.OrdemDAO;
import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.dao.produto.ProdutoOrdemDAO;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.view.swing.aba.Aba;

public class ControllerAbaCriarOrdem {
    Aba aba;
    public ControllerAbaCriarOrdem(Aba aba) {
        this.aba = aba;
    }

    public void cliqueCriarOrdem(Ordem ordem, ProdutoOrdem[] produtosOrdem) {
        OrdemDAO.criarOrdem(ordem);
        for (ProdutoOrdem produtoOrdem:produtosOrdem) {
            if(produtoOrdem.getProduto().getId()==0){
                ProdutoDAO.adicionarProduto(produtoOrdem.getProduto());
            }
            ProdutoOrdemDAO.criar(produtoOrdem);
        }
        aba.atualizarPagina();
    }

    public void cliqueAdicionarProdutoOrdem() {

    }


}
