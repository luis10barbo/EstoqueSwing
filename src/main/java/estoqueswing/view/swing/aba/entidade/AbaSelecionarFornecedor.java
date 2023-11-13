package estoqueswing.view.swing.aba.entidade;

import estoqueswing.dao.entidades.EntidadeDAO;
import estoqueswing.dao.entidades.FornecedorDAO;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.entidade.TipoEntidade;
import estoqueswing.view.swing.componentes.Popup;

import javax.swing.*;

public class AbaSelecionarFornecedor extends AbaSelecionarEntidade<Fornecedor> {
    public AbaSelecionarFornecedor(Popup popup) {
        super(Fornecedor.class, popup);
    }
    @Override
    public Entidade[] getEntidades() {
        return EntidadeDAO.adquirirEntidades(getPesquisa(),TipoEntidade.Fornecedor);
    }

    @Override
    public String getTitulo() {
        return "Selecionar Fornecedor";
    }
}
