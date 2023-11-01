package estoqueswing.view.swing.componentes.aba;

import estoqueswing.controller.abas.ControllerAbaCategorias;

public class AbaCategorias extends Aba {
    ControllerAbaCategorias controller = new ControllerAbaCategorias(this);
    public AbaCategorias() {
        super("Categorias");
    }
}
