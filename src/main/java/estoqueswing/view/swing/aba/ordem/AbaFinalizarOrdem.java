package estoqueswing.view.swing.aba.ordem;

import estoqueswing.controller.abas.ordem.ControllerAbaFinalizarOrdem;
import estoqueswing.exceptions.ordem.ExcecaoMargemLucroInvalidaOrdem;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.componentes.Popup;
import estoqueswing.view.swing.componentes.botoes.BotaoConfirmar;
import estoqueswing.view.swing.componentes.inputs.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbaFinalizarOrdem extends Aba {
    Input inputMargemLucro = new Input("Margem de Lucro");
    BotaoConfirmar confirmar = new BotaoConfirmar("Finalizar");
    ControllerAbaFinalizarOrdem controller = new ControllerAbaFinalizarOrdem(this);
    private Ordem ordem;

    public AbaFinalizarOrdem(Ordem ordem) {
        this.ordem = ordem;
        confirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                try {
                    controller.cliqueFinalizarOrdem(Integer.parseInt(inputMargemLucro.getText()));
                } catch (NumberFormatException ex) {
                    throw new ExcecaoMargemLucroInvalidaOrdem();
                }
            }
        });
        setupPagina();
    }

    @Override
    public String getTitulo() {
        return "Finalizar Ordem";
    }

    public void setupPagina() {
        GridBagConstraints gbcPagina = new GridBagConstraints();
        setLayout(new GridBagLayout());

        gbcPagina.fill = GridBagConstraints.HORIZONTAL;
        pagina.add(inputMargemLucro, gbcPagina);

        pagina.add(confirmar, gbcPagina);
    }

    public Ordem getOrdem() {
        return ordem;
    }
}
