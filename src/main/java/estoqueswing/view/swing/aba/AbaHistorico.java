package estoqueswing.view.swing.aba;

import estoqueswing.controller.abas.ControllerAbaHistorico;

public class AbaHistorico extends Aba {
    ControllerAbaHistorico controller = new ControllerAbaHistorico(this);
    public AbaHistorico() {
        super();
    }

    @Override
    public String getTitulo() {
        return "Historico";
    }
}
