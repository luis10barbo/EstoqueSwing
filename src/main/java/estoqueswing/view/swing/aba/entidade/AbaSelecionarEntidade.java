package estoqueswing.view.swing.aba.entidade;

import estoqueswing.model.entidade.Entidade;
import estoqueswing.view.swing.componentes.Popup;

import javax.swing.*;

public abstract class AbaSelecionarEntidade<TipoEntidadeSelecionada extends Entidade> extends AbaEntidades {
    private final JDialog popup;
    private final Class<TipoEntidadeSelecionada> clazz;
    TipoEntidadeSelecionada entidadeSelecionada;
    public AbaSelecionarEntidade(Class<TipoEntidadeSelecionada> clazz, Popup popup) {
        this.clazz = clazz;
        this.popup = popup;
    }
    @Override
    public TipoAbaEntidade getTipoAba() {
        return TipoAbaEntidade.Selecionar;
    }

    public TipoEntidadeSelecionada getEntidadeSelecionada() {
        return this.entidadeSelecionada;
    }

    public void setEntidadeSelecionada(TipoEntidadeSelecionada entidade) {
        this.entidadeSelecionada = entidade;
    }

    @Override
    public void cliqueSelecionarEntidade(Entidade entidade) {
        super.cliqueSelecionarEntidade(entidade);
        if (clazz.isInstance(entidade)) {
            setEntidadeSelecionada(clazz.cast(entidade));
        }
        popup.dispose();
    }

    @Override
    public abstract Entidade[] getEntidades();

    @Override
    public String getTitulo() {
        return "Selecionar Entidade";
    }
}
