package estoqueswing.view.swing.aba.estoque;

import estoqueswing.model.Estoque;
import estoqueswing.view.swing.componentes.Popup;

public class AbaSelecionarEstoque extends AbaEstoque {

    private final Popup popup;
    private Estoque estoqueSelecionado;
    public AbaSelecionarEstoque(Popup popup) {
        this.popup = popup;
    }
    @Override
    public TipoAbaEstoque getTipo() {
        return TipoAbaEstoque.Selecionar;
    }

    public Estoque getEstoqueSelecionado() {
        return estoqueSelecionado;
    }

    @Override
    public void cliqueSelecionarEstoque(Estoque estoque) {
        this.estoqueSelecionado = estoque;
    }
}
