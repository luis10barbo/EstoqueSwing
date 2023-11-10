package estoqueswing.view.swing.aba.entidade;

import estoqueswing.dao.entidades.EntidadeDAO;
import estoqueswing.dao.entidades.FornecedorDAO;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.entidade.TipoEntidade;

import javax.swing.*;

public class AbaSelecionarFornecedor extends AbaEntidades {
    private final JDialog dialogo;
    Fornecedor fornecedorSelecionado;

    public AbaSelecionarFornecedor(JDialog dialogo) {
        this.dialogo = dialogo;
    }
    @Override
    public TipoAbaEntidade getTipoAba() {
        return TipoAbaEntidade.Selecionar;
    }

    @Override
    public void setEntidadeSelecionada(Fornecedor fornecedorSelecionado) {
        this.fornecedorSelecionado = fornecedorSelecionado;
        dialogo.dispose();

    }

    public Fornecedor getFornecedor() {
        return fornecedorSelecionado;
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
