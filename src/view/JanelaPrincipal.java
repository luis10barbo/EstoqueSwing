package view;

import view.swing.componentes.barralateral.BarraLateral;

import javax.swing.*;

public class JanelaPrincipal extends JFrame {
    public JanelaPrincipal() {
        setSize(1024, 716);
        setVisible(true);

//        setLayout(new FlowLayout());
        add(new BarraLateral(this));
        add(new JPanel());
    }
}
