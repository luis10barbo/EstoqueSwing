package estoqueswing.controller.abas.ordem;

import estoqueswing.dao.ordem.OrdemDAO;
import estoqueswing.model.ordem.NaturezaOrdem;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.ordem.AbaCriarOrdem;
import estoqueswing.view.swing.aba.ordem.AbaEditarOrdem;
import estoqueswing.view.swing.aba.ordem.AbaFinalizarOrdem;
import estoqueswing.view.swing.aba.ordem.AbaOrdens;

public class ControllerAbaOrdens {
    AbaOrdens aba;
    public ControllerAbaOrdens(AbaOrdens aba) {
        this.aba = aba;
    }

    public void cliqueRemoverOrdem(Ordem ordem) {
        OrdemDAO.removerOrdem(ordem);
        aba.atualizarPagina();
    }

    public void cliqueEditarOrdem(Ordem ordem) {
        JanelaPrincipal.adquirir().trocarAba(new AbaEditarOrdem(ordem), false);
        aba.atualizarPagina();
    }

    public void cliqueFinalizarOrdem(Ordem ordem) {
        if (ordem.getNatureza() == NaturezaOrdem.Venda) {
            OrdemDAO.finalizarOrdem(ordem);
            aba.atualizarPagina();
            return;
        }
        JanelaPrincipal.adquirir().trocarAba(new AbaFinalizarOrdem(ordem), false);
    }

    public void cliqueCriarOrdem() {
        JanelaPrincipal.adquirir().trocarAba(new AbaCriarOrdem(), false);
    }
}
