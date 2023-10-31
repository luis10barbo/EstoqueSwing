package estoqueswing.view.swing.componentes.aba;

import estoqueswing.controller.abas.ControllerAbaEntidades;

public class AbaEntidades extends Aba {
    ControllerAbaEntidades controller = new ControllerAbaEntidades(this);
    public AbaEntidades() {
        super("Entidades");
    }
}
