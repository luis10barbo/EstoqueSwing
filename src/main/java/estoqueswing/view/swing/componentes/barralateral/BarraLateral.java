package estoqueswing.view.swing.componentes.barralateral;

import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.componentes.aba.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BarraLateral extends JPanel {
    private static final int ESPACO_ENTRE_BOTOES = 10;
    public static int TAMANHO_BARRA_LATERAL = 256;
    private final GridBagConstraints gbc;
    private final GridBagLayout gbl;

    private BotaoBarraLateral[] botoes;
    private JPanel painelBotoes;

    public BarraLateral() {
        setSize(TAMANHO_BARRA_LATERAL, 0);
        setBorder(new EmptyBorder(35, 35, 35, 35));

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbl = new GridBagLayout();
        gbl.layoutContainer(this);
        setLayout(gbl);



        setOpaque(false);
        criarTitulo();
        criarPainelBotoes();
        criarBotoes();
    }

    private void criarPainelBotoes() {
        painelBotoes = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(painelBotoes);
        painelBotoes.setLayout(gbl);
        painelBotoes.setOpaque(false);
    }

    private void criarBotoes() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(ESPACO_ENTRE_BOTOES, 0, 0, 0);

        BotaoBarraLateral botaoEstoque = adicionarBotao("Estoque", 1, new AbaEstoque());
        BotaoBarraLateral botaoProdutos = adicionarBotao("Produtos", 2, new AbaProdutos());
        BotaoBarraLateral botaoEntidades = adicionarBotao("Entidades", 3, new AbaEntidades());
        BotaoBarraLateral botaoHistorico = adicionarBotao("Historico", 4, new AbaHistorico());
        BotaoBarraLateral botaoEstatisticas = adicionarBotao("Estatisticas", 5, new AbaEstatisticas());
        BotaoBarraLateral botaoCategorias = adicionarBotao("Categorias", 6, new AbaCategorias());


        c.gridy = 1;
        c.anchor = GridBagConstraints.SOUTH;
        c.insets = new Insets(0, 0,0,0);

        add(painelBotoes, c);
        botoes = new BotaoBarraLateral[]{
                botaoEstoque,
                botaoProdutos,
                botaoEntidades,
                botaoHistorico,
                botaoEstatisticas,
                botaoCategorias
        };
        botoes[0].setSelecionado(true);
    }

    private BotaoBarraLateral adicionarBotao(String texto, int posY) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = posY;
        c.weighty = 1;
        c.weightx = 1;
        c.insets = new Insets(ESPACO_ENTRE_BOTOES, 0, 0, 0);

        BotaoBarraLateral botaoProdutos = new BotaoBarraLateral(this ,texto);
        painelBotoes.add(botaoProdutos, c);
        return botaoProdutos;
    }

    private BotaoBarraLateral adicionarBotao(String texto, int posY, Aba aba) {
        BotaoBarraLateral botaoBarraLateral = adicionarBotao(texto, posY);
        botaoBarraLateral.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                JanelaPrincipal.janelaPrincipal().trocarAba(aba);
            }
        });
        return botaoBarraLateral;
    }

    public void resetarBotoesSelecionados() {
        for (BotaoBarraLateral botao : botoes) {
            botao.setSelecionado(false);
        }
    }

    private void criarTitulo() {
        JLabel titulo = new JLabel("Estoque Swing");
        Font font = new Font(null, Font.BOLD, 24);
        titulo.setFont(font);
        titulo.setForeground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 1;
        c.weighty = 1;
        add(titulo, c);

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
