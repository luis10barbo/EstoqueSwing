package estoqueswing.view.swing.aba.estoque;

import estoqueswing.controller.abas.ControllerAbaEstoque;
import estoqueswing.dao.produto.ProdutoEstoqueDAO;
import estoqueswing.model.Estoque;
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
    ProdutoEstoque[] produtoEstoques = null;
    public Botao botaoCriar = new BotaoConfirmar("Criar Ordem");
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
                controller.cliqueBotaoCriarOrdem();
            }
        });

        if (getTipo() == TipoAbaEstoque.Normal) cabecalho.add(botaoCriar);

        criarPagina();
    }

    public void setProdutosPagina(ProdutoEstoque[] produtosEstoque) {
        this.produtoEstoques = produtosEstoque;
        criarTabelaPagina();
    }

    @Override
    public void atualizarPagina() {
        produtoEstoques = ProdutoEstoqueDAO.adquirir(getPesquisa());
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

        // Tabela
        setupNomeColunasTabela(tabela);
        for (int i = 0; i < produtoEstoques.length; i++) {
            ProdutoEstoque produtoEstoque = produtoEstoques[i];
            setupProdutoColunaTabela(tabela, produtoEstoque, i + 1);

        }

        GridBagConstraints cEspacoVazio = new GridBagConstraints();
        cEspacoVazio.weighty = 1;
        cEspacoVazio.gridy = produtoEstoques.length + 1;
        JPanel espacoVazio = new JPanel();
        espacoVazio.setBackground(Color.white);
        tabela.add(espacoVazio, cEspacoVazio);


        GridBagConstraints c = new GridBagConstraints();
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
    private void setupProdutoColunaTabela(JPanel tabela, ProdutoEstoque produtoEstoque, int index) {

        FontePrincipal fonte = new FontePrincipal(Font.PLAIN, 16);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(PADDING_PAGINA, ConstantesSwing.PADDING_PEQUENO, PADDING_PAGINA, ConstantesSwing.PADDING_PEQUENO);

        c.weightx = 1;
        c.gridx = 1;
        c.gridy = index;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel nomeLabel = new JLabel(produtoEstoque.getProduto().getNome());
        nomeLabel.setFont(fonte);
        tabela.add(nomeLabel, c);

        c.gridx = 2;
        JLabel quantidade = new JLabel(String.valueOf(produtoEstoque.getQuantidade()));
        quantidade.setFont(fonte);
        tabela.add(quantidade, c);

        c.gridx = 3;
        JLabel labelGanho = new JLabel("R$ " + produtoEstoque.getValorGanho());
        labelGanho.setFont(fonte);
        tabela.add(labelGanho, c);

        c.gridx = 4;
        JLabel labelGasto = new JLabel("R$ " + produtoEstoque.getValorGasto());
        labelGasto.setFont(fonte);
        tabela.add(labelGasto, c);

        c.gridx = 5;
        double lucro = (produtoEstoque.getValorGanho() - produtoEstoque.getValorGasto());
        JLabel labelLucro = new JLabel("R$ " + lucro);
        labelLucro.setFont(fonte);
        if (lucro < 0) labelLucro.setForeground(new Color(194, 1, 1));
        else if (lucro > 0) labelLucro.setForeground(new Color(4, 140, 0));
        tabela.add(labelLucro, c);

        c.gridx = 6;
        JLabel labelValorVenda = new JLabel("R$ " + (produtoEstoque.getValorVenda()));
        labelValorVenda.setFont(fonte);
        tabela.add(labelValorVenda, c);

        if (getTipo() == TipoAbaEstoque.Normal) {
            c.insets = new Insets(0,0,0,0);
            c.anchor = GridBagConstraints.EAST;
            c.gridx = 7;
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

            c.gridx = 8;
            c.weightx = 0;
            c.insets = new Insets(0, ConstantesSwing.PADDING_PEQUENO, 0, 0);
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
            c.gridx = 7;
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
        cItem.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO);
        cItem.weightx = 1;
        cItem.weighty = 0;

        cItem.gridx = 1;
        JLabel labelNome = new JLabel("Nome");
        labelNome.setFont(fontePrincipal);
        tabela.add(labelNome, cItem);

        cItem.gridx = 2;
        JLabel labelQuantidade = new JLabel("Quantidade");
        labelQuantidade.setFont(fontePrincipal);
        tabela.add(labelQuantidade, cItem);

        cItem.gridx = 3;
        JLabel labelGanho = new JLabel("Ganho Bruto");
        labelGanho.setFont(fontePrincipal);
        tabela.add(labelGanho, cItem);

        cItem.gridx = 4;
        JLabel labelGasto = new JLabel("Gasto Bruto");
        labelGasto.setFont(fontePrincipal);
        tabela.add(labelGasto, cItem);

        cItem.gridx = 5;
        JLabel labelLucro = new JLabel("Receita Liquida");
        labelLucro.setFont(fontePrincipal);
        tabela.add(labelLucro, cItem);

        cItem.gridx = 6;
        JLabel labelValorVenda = new JLabel("Valor Venda");
        labelValorVenda.setFont(fontePrincipal);
        tabela.add(labelValorVenda, cItem);
    }
}
