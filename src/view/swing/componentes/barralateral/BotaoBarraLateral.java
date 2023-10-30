package view.swing.componentes.barralateral;

import view.swing.cores.CorTransparente;
import view.swing.fontes.FontePrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BotaoBarraLateral extends JButton {
    private static int PADDING_BOTAO = 10;
    private static int ARREDONDAMENTO = 20;
    private final JLabel label;

    private boolean selecionado = false;
    private boolean hover = true;
    private static final Color COR_HOVER = new Color(100, 80, 225, 122);
    private static final Color COR_TEXTO = new Color(77, 80, 136);

    public BotaoBarraLateral(BarraLateral barraLateral, String texto) {
        setBorder(new EmptyBorder(PADDING_BOTAO, PADDING_BOTAO, PADDING_BOTAO, PADDING_BOTAO));
        setLayout(new GridLayout());
        label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(
                new FontePrincipal(Font.BOLD, 16)
        );

        setSize(BarraLateral.TAMANHO_BARRA_LATERAL, getHeight());
        add(label);
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                barraLateral.resetarBotoesSelecionados();
                setSelecionado(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setHover(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setHover(false);
            }
        });


    }

    public void setHover(boolean hover) {
        this.hover = hover;
        repaint();
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
            label.setForeground(COR_TEXTO);
        }
        else {
            paint = new GradientPaint(0, 0, new CorTransparente(), w, h, new CorTransparente());
            label.setForeground(Color.WHITE);

            if (hover) {
                paint = new GradientPaint(0, 0, COR_HOVER, w, h, COR_HOVER);
            }
        }
        g2.setPaint(paint);
        g2.fillRoundRect(0, 0, w, h, ARREDONDAMENTO, ARREDONDAMENTO);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(BarraLateral.TAMANHO_BARRA_LATERAL, (int) super.getMaximumSize().getHeight());
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
        repaint();
    }
}
