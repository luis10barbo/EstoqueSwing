package estoqueswing.view.swing.componentes.inputs;

import estoqueswing.view.swing.fontes.FontePrincipal;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InputArea extends JTextArea {
    private int arredondamento = 5;
    public InputArea(String dica) {
//        super(40);
        setOpaque(false); // As suggested by @AVD in comment.
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setFont(new FontePrincipal(Font.PLAIN, 16));
//        setMargin(new Insets(0,0,0,0));

        PromptSupport.setPrompt(dica, this);
        PromptSupport.setForeground(new Color(60, 60, 60), this);
        setLineWrap(true);
    }
    public void setArredondamento(int arredondamento) {
        this.arredondamento = arredondamento;
        revalidate();
        repaint();
    }
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color cor = new Color(240, 240, 240);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(new GradientPaint(0, 0, cor, getWidth(), getHeight(), cor));

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arredondamento, arredondamento);
        super.paintComponent(g);
    }
}
