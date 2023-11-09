package estoqueswing.controller.abas.entidades;

import estoqueswing.dao.entidades.EntidadeDAO;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.entidade.AbaCriarEntidade;

public class ControllerAbaCriarEntidade {
    private AbaCriarEntidade aba;

    public ControllerAbaCriarEntidade(AbaCriarEntidade aba) {
        this.aba = aba;
    }

    public void botaoCriarEntidade(Entidade entidade) {

        EntidadeDAO.adicionarEntidade(entidade);
        JanelaPrincipal.adquirir().voltarAba();


    }

    public void botaoEditarEntidade(Entidade entidade) {
        EntidadeDAO.editarEntidade(entidade);
        JanelaPrincipal.adquirir().voltarAba();
    }
}
