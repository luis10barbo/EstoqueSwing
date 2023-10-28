package view.swing.componentes.barralateral;

import view.swing.cores.CorTransparente;
import view.swing.fontes.FontePrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BotaoBarraLateral extends JButton {
    private static int PADDING_BOTAO = 10;
    private static int ARREDONDAMENTO = 10;
    private final JLabel label;

    private boolean selecionado = false;
    public BotaoBarraLateral(BarraLateral parente, String texto) {
        setBorder(new EmptyBorder(PADDING_BOTAO, PADDING_BOTAO, PADDING_BOTAO, PADDING_BOTAO));
        label = new JLabel(texto);
        label.setFont(
                new FontePrincipal(Font.BOLD, 20)
        );
        setSize(BarraLateral.TAMANHO_BARRA_LATERAL, getHeight());
        add(label);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();

        GradientPaint paint;
        if (selecionado) {
            paint = new GradientPaint(0, 0, Color.WHITE, w, h, Color.WHITE);
            label.setForeground(new Color(77, 80, 136));
        }
        else {
            paint = new GradientPaint(0, 0, new CorTransparente(), w, h, new CorTransparente());
            label.setForeground(Color.WHITE);
        }
        g2.setPaint(paint);
        g2.fillRoundRect(0, 0, w, h, ARREDONDAMENTO, ARREDONDAMENTO);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(BarraLateral.TAMANHO_BARRA_LATERAL, (int) super.getMaximumSize().getHeight());
    }
}
