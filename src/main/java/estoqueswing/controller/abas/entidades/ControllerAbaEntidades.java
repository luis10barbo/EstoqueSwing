package estoqueswing.controller.abas.entidades;

import estoqueswing.model.entidade.Entidade;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.entidade.AbaCriarEntidade;
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

    }

    public void cliqueEditarProduto(Entidade entidade) {

    }

    public void cliqueApagarProduto(Entidade entidade) {

    }
}
