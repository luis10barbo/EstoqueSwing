package estoqueswing.controller.abas.entidades;

import estoqueswing.dao.entidades.EntidadeDAO;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.aba.entidade.AbaCriarEntidade;
import estoqueswing.view.swing.aba.entidade.AbaEditarEntidade;
import estoqueswing.view.swing.aba.entidade.AbaEntidades;

public class ControllerAbaEntidades {
    AbaEntidades abaEntidades;
    public ControllerAbaEntidades(AbaEntidades abaEntidades) {
        this.abaEntidades = abaEntidades;
    }

    public void cliqueCriarEntidade() {
        JanelaPrincipal.adquirir().trocarAba(new AbaCriarEntidade(), false);
    }

    public void cliquePesquisar(String pesquisa) {
        abaEntidades.atualizarPagina();
    }

    public void cliqueEditarEntidade(Entidade entidade) {
        JanelaPrincipal.adquirir().trocarAba(new AbaEditarEntidade(entidade), false);

    }

    public void cliqueApagarEntidade(Entidade entidade) {
        EntidadeDAO.removerEntidade(entidade);
        abaEntidades.atualizarPagina();
    }
}
