package estoqueswing.view.swing.componentes.aba;

import estoqueswing.controller.abas.ControllerAbaEstoque;
import estoqueswing.model.Produto;
import estoqueswing.model.dto.ProdutoDTO;
import estoqueswing.view.swing.componentes.botoes.*;
import estoqueswing.view.swing.componentes.inputs.Input;
import estoqueswing.view.swing.cores.CorTransparente;
import estoqueswing.view.swing.fontes.FontePrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbaEstoque extends Aba {
    private final ControllerAbaEstoque controllerAbaEstoque = new ControllerAbaEstoque(this);
    private static final int PADDING_PAGINA = 20;
    Produto[] produtos = ProdutoDTO.adquirirProdutos();
    public Botao botaoCriar = new BotaoConfirmar("Criar");
    private Input inputPesquisa;

    public AbaEstoque() {

        super("Estoque");

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        botaoCriar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controllerAbaEstoque.cliqueBotaoCriarProduto();
            }
        });
        cabecalho.add(botaoCriar);
        criarPagina();
    }

    public void setProdutosPagina(Produto[] produtos) {
        this.produtos = produtos;
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
                controllerAbaEstoque.cliquePesquisar(getPesquisa());
            }

        });
        painelPesquisa.add(pesquisar, c);

        criarTabelaPagina();
    }

    private String getPesquisa() {
        return inputPesquisa.getText();
    }

    private void criarTabelaPagina() {
        GridBagLayout gbl = new GridBagLayout();
        JPanel tabela = new JPanel();
        gbl.layoutContainer(tabela);
        tabela.setBackground(new CorTransparente());
        tabela.setLayout(gbl);

        // Tabela
        setupNomeColunasTabela(tabela);
        for (int i = 0; i < produtos.length; i++) {
            Produto produto = produtos[i];
            System.out.println(produto);
            setupProdutoColunaTabela(tabela, produto, i + 1);
        }
        //

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(PADDING_PAGINA, 0,0,0);
        pagina.add(tabela, c);
    }
    private void setupProdutoColunaTabela(JPanel tabela, Produto produto, int index) {
        JPanel produtoPainel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(produtoPainel);
        produtoPainel.setLayout(gbl);
        produtoPainel.setBorder(new MatteBorder(1, 0, 0, 0, new Color(240, 240, 240)));
        produtoPainel.setBackground(new CorTransparente());

        FontePrincipal fonte = new FontePrincipal(Font.PLAIN, 16);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(PADDING_PAGINA, 0, PADDING_PAGINA, 0);

        c.weightx = 1;
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel nomeLabel = new JLabel(produto.getNome());
        nomeLabel.setFont(fonte);
        produtoPainel.add(nomeLabel, c);

        c.gridx = 2;
        JLabel quantidade = new JLabel(String.valueOf(produto.getQuantidade()));
        quantidade.setFont(fonte);
        produtoPainel.add(quantidade, c);

        c.gridx = 3;
        JLabel lucro = new JLabel("R$ 60");
        lucro.setFont(fonte);
        produtoPainel.add(lucro, c);

        c.anchor = GridBagConstraints.EAST;
        c.gridx = 4;
        c.fill = GridBagConstraints.NONE;
        BotaoEditar botaoEditar = new BotaoEditar("Editar");
        botaoEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controllerAbaEstoque.cliqueEditarProduto(produto);
            }
        });
        produtoPainel.add(botaoEditar, c);

        c.gridx = 5;
        BotaoRemover botaoRemover = new BotaoRemover("Remover");
        botaoRemover.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controllerAbaEstoque.cliqueApagarProduto(produto);
            }
        });
        produtoPainel.add(botaoRemover, c);

        GridBagConstraints cProduto = new GridBagConstraints();
        cProduto.weightx = 1;
//        cProduto.weighty = 1;
        cProduto.gridy = index;
        System.out.println(index);

        cProduto.fill = GridBagConstraints.HORIZONTAL;
        cProduto.anchor = GridBagConstraints.NORTHWEST;
        tabela.add(produtoPainel, cProduto);
    }
    private void setupNomeColunasTabela(JPanel tabela) {
        JPanel painelNomes = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(painelNomes);
        painelNomes.setLayout(gbl);
        painelNomes.setBackground(new CorTransparente());

        FontePrincipal fontePrincipal = new FontePrincipal(Font.PLAIN, 16);
        GridBagConstraints cItem = new GridBagConstraints();
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.weightx = 1;
        cItem.weighty = 1;

        cItem.gridx = 1;
        JLabel labelNome = new JLabel("Nome");
        labelNome.setFont(fontePrincipal);
        painelNomes.add(labelNome, cItem);

        cItem.gridx = 2;
        JLabel labelQuantidade = new JLabel("Quantidade");
        labelQuantidade.setFont(fontePrincipal);
        painelNomes.add(labelQuantidade, cItem);

        cItem.gridx = 3;
        JLabel labelLucro = new JLabel("Lucro");
        labelLucro.setFont(fontePrincipal);
        painelNomes.add(labelLucro, cItem);

        GridBagConstraints cNomes = new GridBagConstraints();
        cNomes.weightx = 1;
        cNomes.fill = GridBagConstraints.HORIZONTAL;
        cNomes.insets = new Insets(PADDING_PAGINA, 0, PADDING_PAGINA, 0);
        tabela.add(painelNomes, cNomes);
    }
}
