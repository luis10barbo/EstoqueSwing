package estoqueswing.controller.abas.ordem;

import estoqueswing.dao.ordem.OrdemDAO;
import estoqueswing.dao.produto.ProdutoOrdemDAO;
import estoqueswing.model.ordem.NaturezaOrdem;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.ordem.AbaFinalizarOrdem;

public class ControllerAbaFinalizarOrdem {
    private final AbaFinalizarOrdem aba;

    public ControllerAbaFinalizarOrdem(AbaFinalizarOrdem aba) {
        this.aba = aba;
    }

    public void cliqueFinalizarOrdem(int margemLucro) {
        if (aba.getOrdem().getNatureza() == NaturezaOrdem.Compra) {
            for (ProdutoOrdem produtoOrdem: aba.getOrdem().getProdutosOrdem()) {
                produtoOrdem.setValorProduto(produtoOrdem.getValorProduto() * ((margemLucro/100.0) + 1));
                ProdutoOrdemDAO.editar(produtoOrdem);
            }
        }
        OrdemDAO.finalizarOrdem(aba.getOrdem());
        JanelaPrincipal.adquirir().voltarAba();
    }
}
