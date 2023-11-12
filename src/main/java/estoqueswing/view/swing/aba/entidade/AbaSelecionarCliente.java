package estoqueswing.view.swing.aba.entidade;

import estoqueswing.dao.entidades.EntidadeDAO;
import estoqueswing.model.entidade.Cliente;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.model.entidade.TipoEntidade;
import estoqueswing.view.swing.componentes.Popup;

public class AbaSelecionarCliente extends AbaSelecionarEntidade<Cliente> {
    public AbaSelecionarCliente( Popup popup) {
        super(Cliente.class, popup);
    }

    @Override
    public Entidade[] getEntidades() {
        return EntidadeDAO.adquirirEntidades(getPesquisa(), TipoEntidade.Cliente);
    }

    @Override
    public String getTitulo() {
        return "Selecionar Cliente";
    }
}
