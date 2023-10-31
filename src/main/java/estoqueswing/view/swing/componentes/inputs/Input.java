package estoqueswing.view.swing.componentes.inputs;

import estoqueswing.view.swing.cores.CorTransparente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Input extends JTextField {
//    public Input() {
//        super("Teste", 3);
//        setBorder(null);
//        setOpaque(false);
////        setSize(dimensao);
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        int w = getWidth();
//        int h = getHeight();
//
//        Color corFundo = new Color(230, 230, 230);
//        GradientPaint paint = new GradientPaint(0, 0, corFundo, w, h, corFundo);
//        g2.setPaint(paint);
//        g2.fillRoundRect(0, 0, w, h, 10, 10);
//
//    }
    private Shape shape;
    private final int arredondamento = 5;
    public Input() {
        super(30);
        setOpaque(false); // As suggested by @AVD in comment.
        setBorder(new EmptyBorder(0, 10, 0, 10));
    }
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color cor = new Color(230, 230, 230);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(new GradientPaint(0, 0, cor, getWidth(), getHeight(), cor));

        g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, arredondamento, arredondamento);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(new CorTransparente());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arredondamento, arredondamento);
    }
}
