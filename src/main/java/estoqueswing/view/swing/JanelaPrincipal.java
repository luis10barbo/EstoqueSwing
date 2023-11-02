package estoqueswing.view.swing;

import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.aba.AbaEstoque;
import estoqueswing.view.swing.componentes.barralateral.BarraLateral;

import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {
    private Component abaAtual;
    private static JanelaPrincipal janelaPrincipal;
    public JanelaPrincipal() {
        janelaPrincipal = this;

        setSize(1024, 716);
        setLayout(new GridBagLayout());
        criarBarraLateral();

        trocarAba(new AbaEstoque());
        setVisible(true);
    }

    public static JanelaPrincipal janelaPrincipal() {
        return janelaPrincipal;
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

    public void trocarAba(Aba aba) {
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        if (abaAtual != null) remove(abaAtual);

        this.abaAtual = aba;
        add(aba,c);
        revalidate();
        repaint();
    }
}
