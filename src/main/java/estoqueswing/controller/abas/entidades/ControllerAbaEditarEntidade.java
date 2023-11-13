package estoqueswing.controller.abas.entidades;

import estoqueswing.controller.abas.ordem.ControllerAbaCriarOrdem;
import estoqueswing.dao.entidades.EntidadeDAO;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.aba.entidade.AbaEditarEntidade;

public class ControllerAbaEditarEntidade extends ControllerAbaCriarEntidade {
    public ControllerAbaEditarEntidade(AbaEditarEntidade aba) {
        super(aba);
    }

    public void botaoEditarEntidade(Entidade entidade) {
        EntidadeDAO.editarEntidade(entidade);
        JanelaPrincipal.adquirir().voltarAba();
    }
}
