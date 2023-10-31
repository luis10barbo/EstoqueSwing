package estoqueswing.view.swing.componentes.aba;

import estoqueswing.controller.abas.ControllerAbaProdutos;

public class AbaProdutos extends Aba {
    ControllerAbaProdutos controller = new ControllerAbaProdutos(this);
    public AbaProdutos() {
        super("Produtos");
    }
}
