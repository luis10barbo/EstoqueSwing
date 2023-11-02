package estoqueswing.view.swing;

import estoqueswing.model.constantes.ConstantesSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Scroll extends JScrollPane {
    public Scroll(Component view) {
        super(view);
        setBorder(null);

    }
}
