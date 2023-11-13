package estoqueswing.view.swing.aba.entidade;

import estoqueswing.dao.entidades.EntidadeDAO;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.model.entidade.TipoEntidade;
import estoqueswing.model.entidade.Transportadora;
import estoqueswing.view.swing.componentes.Popup;

public class AbaSelecionarTransportadora extends AbaSelecionarEntidade<Transportadora> {
    public AbaSelecionarTransportadora(Popup popup) {
        super(Transportadora.class, popup);
    }

    @Override
    public Entidade[] getEntidades() {
        return EntidadeDAO.adquirirEntidades(getPesquisa(), TipoEntidade.Transportadora);
    }

    @Override
    public String getTitulo() {
        return "Selecionar Transportadora";
    }
}
