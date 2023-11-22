package estoqueswing.view.swing.aba.estoque;

import estoqueswing.controller.abas.ControllerAbaEstoque;
import estoqueswing.dao.produto.ProdutoEstoqueDAO;
import estoqueswing.model.constantes.ConstantesSwing;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.componentes.Scroll;
import estoqueswing.view.swing.componentes.botoes.*;
import estoqueswing.view.swing.componentes.inputs.Input;
import estoqueswing.view.swing.fontes.FontePrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbaEstoque extends Aba {

    public enum TipoAbaEstoque {
        Normal,
        Selecionar
    }

    public TipoAbaEstoque getTipo() {
        return TipoAbaEstoque.Normal;
    }

    public void cliqueSelecionarProduto(ProdutoEstoque produtoEstoque) {}

    private final ControllerAbaEstoque controller = new ControllerAbaEstoque(this);
    private static final int PADDING_PAGINA = 20;
    ProdutoEstoque[] produtosEstoque = null;
    public Botao botaoCriar = new BotaoConfirmar("Ordens Estoque");
    private Input inputPesquisa;
    private JPanel tabela;
    private Scroll scrollTabela;

    public AbaEstoque() {

        super();
        atualizarPagina();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        botaoCriar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueBotaoOrdensEstoque();
            }
        });

        if (getTipo() == TipoAbaEstoque.Normal) cabecalho.add(botaoCriar);

        criarPagina();
    }

    public void setProdutosPagina(ProdutoEstoque[] produtosEstoque) {
        this.produtosEstoque = produtosEstoque;
        criarTabelaPagina();
    }

    @Override
    public void atualizarPagina() {
        produtosEstoque = ProdutoEstoqueDAO.adquirir(getPesquisa());
        criarTabelaPagina();
        revalidate();
        repaint();
    }

    @Override
    public void atualizarInformacoesDB() {

    }

    private void criarPagina() {
        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(this);
        pagina.setLayout(gbl);
        pagina.setBorder(new EmptyBorder(new Insets(PADDING_PAGINA, PADDING_PAGINA, PADDING_PAGINA, PADDING_PAGINA)));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;

        c.weighty = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.NORTH;
        JPanel painelPesquisa = new JPanel(new GridBagLayout());
        painelPesquisa.setOpaque(false);

        inputPesquisa = new Input("Pesquisar");
        painelPesquisa.add(inputPesquisa, c);

        pagina.add(painelPesquisa, c);

        c.gridx = 1;
        c.weightx = 0;
        c.insets = new Insets(0, 10, 0, 0);
        Botao pesquisar = new BotaoNeutro("Pesquisar");
        pesquisar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliquePesquisar(getPesquisa());
            }

        });
        painelPesquisa.add(pesquisar, c);

        criarTabelaPagina();
    }

    @Override
    public String getTitulo() {
        return "Estoque";
    }

    private String getPesquisa() {
        if (inputPesquisa == null) return null;
        return inputPesquisa.getText();
    }

    private void criarTabelaPagina() {
        if (tabela != null && scrollTabela != null) {
//            scrollTabela.remove(tabela);
            pagina.remove(scrollTabela);
        }

        GridBagLayout gbl = new GridBagLayout();
        tabela = new JPanel();
        gbl.layoutContainer(tabela);
        tabela.setBackground(Color.white);
        tabela.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();
        // Tabela
        c.gridy ++;
        setupNomeColunasTabela(tabela);

        c.gridy ++;
        setupTotal(tabela, c.gridy);

        for (ProdutoEstoque produtoEstoque: produtosEstoque) {
            c.gridy ++;
            setupProdutoColunaTabela(tabela, produtoEstoque, c.gridy);
        }

        c.gridy ++;
        GridBagConstraints cEspacoVazio = new GridBagConstraints();
        cEspacoVazio.weighty = 1;
        cEspacoVazio.gridy = c.gridy;
        JPanel espacoVazio = new JPanel();
        espacoVazio.setBackground(Color.white);
        tabela.add(espacoVazio, cEspacoVazio);


        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(PADDING_PAGINA, 0,0,0);

        scrollTabela = new Scroll(tabela);
        scrollTabela.setOpaque(false);
        pagina.add(scrollTabela, c);
    }

    private void setupTotal(JPanel tabela, int y) {
        GridBagConstraints gbcTotal = new GridBagConstraints();
        FontePrincipal fonte = new FontePrincipal(Font.PLAIN);
        Color corTexto = new Color(134, 134, 134);
        gbcTotal.gridy = y;
        gbcTotal.anchor = GridBagConstraints.WEST;
        gbcTotal.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, ConstantesSwing.PADDING_PEQUENO, 0);

        double totalGasto = 0.0;
        double totalGanho = 0.0;
        double totalReceitaLiquida = 0.0;
        for (ProdutoEstoque produtoEstoque: produtosEstoque) {
            totalGasto += produtoEstoque.getValorGasto();
            totalGanho += produtoEstoque.getValorGanho();
            totalReceitaLiquida = totalGanho - totalGasto;
        }

        gbcTotal.gridx = 0;
        JLabel labelTotal = new JLabel("Total");
        labelTotal.setFont(fonte);
        labelTotal.setForeground(corTexto);
        tabela.add(labelTotal, gbcTotal);

        gbcTotal.gridx = 2;
        JLabel labelTotalGanho = new JLabel("R$ " + totalGanho);
        labelTotalGanho.setFont(fonte);
        labelTotalGanho.setForeground(corTexto);
        tabela.add(labelTotalGanho, gbcTotal);

        gbcTotal.gridx ++;
        JLabel labelTotalGasto = new JLabel("R$ " + totalGasto);
        labelTotalGasto.setFont(fonte);
        labelTotalGasto.setForeground(corTexto);
        tabela.add(labelTotalGasto, gbcTotal);


        gbcTotal.gridx ++;
        JLabel labelReceitaLiquida = new JLabel("R$ " + totalReceitaLiquida);
        labelReceitaLiquida.setFont(fonte);
        labelReceitaLiquida.setForeground(corTexto);
        tabela.add(labelReceitaLiquida, gbcTotal);

    }

    private void setupProdutoColunaTabela(JPanel tabela, ProdutoEstoque produtoEstoque, int index) {

        FontePrincipal fonte = new FontePrincipal(Font.PLAIN, 16);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO);

        c.weightx = 1;
        c.gridx = 0;
        c.gridy = index;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel nomeLabel = new JLabel(produtoEstoque.getProduto().getNome());
        nomeLabel.setFont(fonte);
        tabela.add(nomeLabel, c);

        c.gridx ++;
        JLabel quantidade = new JLabel(String.valueOf(produtoEstoque.getQuantidade()));
        quantidade.setFont(fonte);
        tabela.add(quantidade, c);

        c.gridx ++;
        JLabel labelGanho = new JLabel("R$ " + produtoEstoque.getValorGanho());
        labelGanho.setFont(fonte);
        tabela.add(labelGanho, c);

        c.gridx ++;
        JLabel labelGasto = new JLabel("R$ " + produtoEstoque.getValorGasto());
        labelGasto.setFont(fonte);
        tabela.add(labelGasto, c);

        c.gridx ++;
        double lucro = (produtoEstoque.getValorGanho() - produtoEstoque.getValorGasto());
        JLabel labelLucro = new JLabel("R$ " + lucro);
        labelLucro.setFont(fonte);
        if (lucro < 0) labelLucro.setForeground(new Color(194, 1, 1));
        else if (lucro > 0) labelLucro.setForeground(new Color(4, 140, 0));
        tabela.add(labelLucro, c);

        c.gridx ++;
        JLabel labelValorVenda = new JLabel("R$ " + (produtoEstoque.getValorVenda()));
        labelValorVenda.setFont(fonte);
        tabela.add(labelValorVenda, c);

        if (getTipo() == TipoAbaEstoque.Normal) {
            c.insets = new Insets(0,0,0,0);
            c.anchor = GridBagConstraints.EAST;
            c.gridx ++;
            c.fill = GridBagConstraints.NONE;
            BotaoEditar botaoEditar = new BotaoEditar("Editar");
            botaoEditar.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    controller.cliqueEditarProduto(produtoEstoque);
                }
            });
            tabela.add(botaoEditar, c);

            c.gridx ++;
            c.weightx = 0;
            c.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, 0);
            BotaoRemover botaoRemover = new BotaoRemover("Remover");
            botaoRemover.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    controller.cliqueApagarProduto(produtoEstoque);
                }
            });
            tabela.add(botaoRemover, c);
        } else if (getTipo() == TipoAbaEstoque.Selecionar) {
            c.gridx ++;
            BotaoEditar botaoSelecionar = new BotaoEditar("Selecionar");
            botaoSelecionar.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    cliqueSelecionarProduto(produtoEstoque);
                }
            });
            tabela.add(botaoSelecionar, c);
        }
    }
    private void setupNomeColunasTabela(JPanel tabela) {
        FontePrincipal fontePrincipal = new FontePrincipal(Font.PLAIN, 16);
        GridBagConstraints cItem = new GridBagConstraints();
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO);
        cItem.weightx = 1;
        cItem.weighty = 0;

        cItem.gridx = 0;
        JLabel labelNome = new JLabel("Nome");
        labelNome.setFont(fontePrincipal);
        tabela.add(labelNome, cItem);

        cItem.gridx ++;
        JLabel labelQuantidade = new JLabel("Quantidade");
        labelQuantidade.setFont(fontePrincipal);
        tabela.add(labelQuantidade, cItem);

        cItem.gridx ++;
        JLabel labelGanho = new JLabel("Ganho Bruto");
        labelGanho.setFont(fontePrincipal);
        tabela.add(labelGanho, cItem);

        cItem.gridx ++;
        JLabel labelGasto = new JLabel("Gasto Bruto");
        labelGasto.setFont(fontePrincipal);
        tabela.add(labelGasto, cItem);

        cItem.gridx ++;
        JLabel labelLucro = new JLabel("Receita Liquida");
        labelLucro.setFont(fontePrincipal);
        tabela.add(labelLucro, cItem);

        cItem.gridx ++;
        JLabel labelValorVenda = new JLabel("Valor Venda");
        labelValorVenda.setFont(fontePrincipal);
        tabela.add(labelValorVenda, cItem);
    }
}
