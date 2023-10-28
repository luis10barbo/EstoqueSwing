package model.swing.componentes.barralateral;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BarraLateral extends JPanel {
    public static int TAMANHO_BARRA_LATERAL = 256;
//    private final GridBagConstraints gbc;
//    private final GridBagLayout gbl;

    public BarraLateral(JFrame parent) {
        setSize(TAMANHO_BARRA_LATERAL, parent.getHeight());
        setBorder(new EmptyBorder(35, 10, 35, 10));

//        gbc = new GridBagConstraints();
//        gbc.fill = GridBagConstraints.VERTICAL;
//        gbl = new GridBagLayout();
//        gbl.layoutContainer(this);
//        setLayout(gbl);

        setOpaque(false);
        criarTitulo();
        criarBotoes();
    }

    private void criarBotoes() {

        BotaoBarraLateral botaoBarraLateral = new BotaoBarraLateral(this, "Estoque");
//        gbl.setConstraints(botaoBarraLateral, gbc);
        add(botaoBarraLateral);

    }

    private void criarTitulo() {
        JLabel titulo = new JLabel("Estoque Swing");
        Font font = new Font(null, Font.BOLD, 24);
        titulo.setFont(font);
        titulo.setForeground(Color.WHITE);
        add(titulo);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth(), h = getHeight();

        Color cor1 = new Color(154, 127, 230);
        Color cor2 = new Color(103, 131, 208);

        GradientPaint gp = new GradientPaint(0, 0, cor1, w, h, cor2);
        g2.setPaint(gp);
        g2.fillRect(0,0, w,h);
    }
}
