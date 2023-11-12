package estoqueswing.view.swing.componentes;

import javax.swing.*;
import java.awt.*;

public class Popup extends JDialog {
    public Popup(JFrame dono, Dimension tamanho) {
        super(dono, "", true);
        setPreferredSize(tamanho);
    }
    public Popup adicionarAba(JComponent view) {
        getContentPane().add(view);
        return this;
    }

    public Popup mostrar() {
        pack();
        setVisible(true);
        return this;
    }
}
