package estoqueswing.view;

import estoqueswing.view.swing.componentes.aba.Aba;
import estoqueswing.view.swing.componentes.barralateral.BarraLateral;

import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {
    private Component janelaAtual;
    public JanelaPrincipal() {
        this.janelaAtual = this;

        setSize(1024, 716);
        setLayout(new GridBagLayout());
        criarBarraLateral();

        JPanel painel = new JPanel();
        trocarJanela(new Aba("Estoque"));
        setVisible(true);
    }



    public void criarBarraLateral() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.weighty = 1;
        c.weightx = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.EAST;
        add(new BarraLateral(), c);

    }

    public void trocarJanela(Component janela) {
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        if (janelaAtual != null) remove(janelaAtual);

        this.janelaAtual = janela;
        add(janela,c);
    }
}
