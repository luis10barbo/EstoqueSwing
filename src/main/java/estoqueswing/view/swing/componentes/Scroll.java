package estoqueswing.view.swing.componentes;

import javax.swing.*;
import java.awt.*;

public class Scroll extends JScrollPane {
    public Scroll(Component view) {
        super(view);
        setBorder(null);
        getViewport().setOpaque(false);
        setOpaque(false);
    }
}
