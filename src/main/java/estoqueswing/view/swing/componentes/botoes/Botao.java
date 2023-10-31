package estoqueswing.view.swing.componentes.botoes;

import estoqueswing.view.swing.componentes.barralateral.BarraLateral;
import estoqueswing.view.swing.cores.CorTransparente;
import estoqueswing.view.swing.fontes.FontePrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Botao extends JButton {
    private static int PADDING_BOTAO = 10;
    private int arredondamento = 20;
    public final JLabel label;

    private boolean selecionado = false;
    private boolean hover = false;

    private boolean manterPressionado = false;


    public Botao(String texto, boolean manterPressionado, int arredondamento) {
        this.manterPressionado = manterPressionado;
        this.arredondamento = arredondamento;

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
                aoClicar(e);
                setSelecionado(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (!manterPressionado) setSelecionado(false);
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
    public void aoClicar(MouseEvent e) {

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
            Color corFundoSelecionado = getCorFundoSelecionado();
            paint = new GradientPaint(0, 0, corFundoSelecionado, w, h, corFundoSelecionado);
            label.setForeground(getCorTextoSelecionado());
        }
        else {
            label.setForeground(getCorTexto());

            if (hover) {
                Color corFundoHover = getCorFundoHover();
                paint = new GradientPaint(0, 0, corFundoHover, w, h, corFundoHover);
            } else {
                Color corFundo = getCorFundo();
                paint = new GradientPaint(0,0, corFundo, w, h, corFundo);
            }
        }
        g2.setPaint(paint);
        g2.fillRoundRect(0, 0, w, h, arredondamento, arredondamento);
    }


    @Override
    public Dimension getMaximumSize() {
        return new Dimension(BarraLateral.TAMANHO_BARRA_LATERAL, (int) super.getMaximumSize().getHeight());
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
        repaint();
    }

    public Color getCorTextoSelecionado() {
        return new Color(77, 80, 136);

    }


    public Color getCorFundoHover() {
        return new Color(100, 80, 225, 122);
    }

    public Color getCorTexto() {
        return Color.WHITE;
    }

    public Color getCorFundoSelecionado() {
        return Color.WHITE;
    }

    public Color getCorFundo() {
        return new CorTransparente();
    }
}
