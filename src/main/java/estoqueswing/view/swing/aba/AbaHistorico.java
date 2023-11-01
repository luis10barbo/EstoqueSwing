package estoqueswing.view.swing.componentes.aba;

import estoqueswing.controller.abas.ControllerAbaHistorico;

public class AbaHistorico extends Aba {
    ControllerAbaHistorico controller = new ControllerAbaHistorico(this);
    public AbaHistorico() {
        super("Historico");
    }
}
