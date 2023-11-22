package estoqueswing.controller.abas.ordem;

import estoqueswing.dao.ordem.OrdemDAO;
import estoqueswing.dao.produto.ProdutoOrdemDAO;
import estoqueswing.exceptions.ordem.ExcecaoEditarOrdemFinalizada;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.aba.ordem.AbaEditarOrdem;

import java.util.ArrayList;

public class ControllerAbaEditarOrdem {
    AbaEditarOrdem aba;
    public ControllerAbaEditarOrdem(AbaEditarOrdem aba) {
        this.aba = aba;
    }

    public void cliqueEditarOrdem(Ordem ordem, ArrayList<ProdutoOrdem> produtosOrdem, ArrayList<ProdutoOrdem> produtosRemovidos) {
        if (ordem.isFinalizada()) throw new ExcecaoEditarOrdemFinalizada();

        for (ProdutoOrdem produto: produtosRemovidos) {
            ProdutoOrdemDAO.remover(produto);
        }

        for (ProdutoOrdem produtoOrdem: produtosOrdem) {
            if (produtoOrdem.getId() == 0) {
                ProdutoOrdemDAO.criar(produtoOrdem);
                continue;
            }
            ProdutoOrdemDAO.editar(produtoOrdem);
        }

        OrdemDAO.editarOrdem(ordem);
        JanelaPrincipal.adquirir().voltarAba();
    }
}
