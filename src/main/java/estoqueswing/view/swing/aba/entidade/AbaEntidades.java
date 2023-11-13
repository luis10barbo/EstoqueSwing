package estoqueswing.view.swing.aba.entidade;


import estoqueswing.controller.abas.entidades.ControllerAbaEntidades;
import estoqueswing.model.constantes.ConstantesSwing;
import estoqueswing.dao.entidades.EntidadeDAO;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.model.entidade.TipoEntidade;
import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.componentes.Scroll;
import estoqueswing.view.swing.componentes.botoes.*;
import estoqueswing.view.swing.componentes.inputs.Input;
import estoqueswing.view.swing.fontes.FontePrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbaEntidades extends Aba {
    private BotaoNeutro botaoPesquisar;

    public enum TipoAbaEntidade {
        Normal,
        Selecionar
    }
    ControllerAbaEntidades controller = new ControllerAbaEntidades(this);
    public Botao botaoCriar = new BotaoConfirmar("Criar");
    private Input inputPesquisa = new Input("Pesquisar");
    Entidade[] entidades = getEntidades();
    private JPanel tabela;
    private Scroll scrollTabela;
    private final JComboBox<TipoEntidade> selectTipoEntidade = new JComboBox<>(new TipoEntidade[]{TipoEntidade.Nenhum, TipoEntidade.Cliente, TipoEntidade.Transportadora, TipoEntidade.Fornecedor});

    public TipoAbaEntidade getTipoAba() {
        return TipoAbaEntidade.Normal;
    }

    public void cliqueSelecionarEntidade(Entidade entidade) {}

    public Entidade[] getEntidades() {
        return EntidadeDAO.adquirirEntidades(getPesquisa(), selectTipoEntidade != null ? (TipoEntidade) selectTipoEntidade.getSelectedItem() : TipoEntidade.Nenhum);
    }

    @Override
    public void atualizarPagina() {
        this.entidades = getEntidades();
        criarTabelaPagina();
        revalidate();
        repaint();
    }

    @Override
    public String getTitulo() {
        return "Entidades";
    }

    public AbaEntidades() {

        super();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;

        if (getTipoAba() == TipoAbaEntidade.Normal) {
            cabecalho.add(botaoCriar);
            botaoCriar.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    controller.cliqueCriarEntidade();
                }
            });
        }

        selectTipoEntidade.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarPagina();
            }
        });

        setupPagina();
    }

    private void setupPagina() {
        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(this);
        pagina.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;

        c.weighty = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(0, ConstantesSwing.PADDING_PEQUENO, 0, 0);
        JPanel painelPesquisa = new JPanel(new GridBagLayout());
        painelPesquisa.setOpaque(false);

        inputPesquisa = new Input("Pesquisar");
        painelPesquisa.add(inputPesquisa, c);

        pagina.add(painelPesquisa, c);
        c.gridx = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(0, ConstantesSwing.PADDING_PEQUENO, 0,  ConstantesSwing.PADDING_PEQUENO);
        botaoPesquisar = new BotaoNeutro("Pesquisar");
        botaoPesquisar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliquePesquisar(getPesquisa());
            }


        });

        c.fill = GridBagConstraints.HORIZONTAL;
        painelPesquisa.add(botaoPesquisar, c);
        c.gridx ++;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, ConstantesSwing.PADDING_PEQUENO, 0, ConstantesSwing.PADDING_MEDIO);
        painelPesquisa.add(selectTipoEntidade,c);

        criarTabelaPagina();
    }

    private void criarTabelaPagina() {
        if (tabela != null && scrollTabela != null) {
            pagina.remove(scrollTabela);
        }

        GridBagLayout gbl = new GridBagLayout();
        tabela = new JPanel();
        tabela.setOpaque(false);
        gbl.layoutContainer(tabela);
        tabela.setLayout(gbl);

        if (entidades != null) {
            for (int i = 0; i < entidades.length; i++) {
                Entidade entidade = entidades[i];
                setupEntidadeColunaTabela(tabela, entidade, i + 1);
            }

            GridBagConstraints cEspacoVazio = new GridBagConstraints();
            cEspacoVazio.weighty = 1;
            cEspacoVazio.fill = GridBagConstraints.BOTH;
            cEspacoVazio.gridy = entidades.length + 1;
            JPanel espacoVazio = new JPanel();
            espacoVazio.setBackground(Color.white);
            tabela.add(espacoVazio, cEspacoVazio);
        }
        //



        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0,0,0);

        scrollTabela = new Scroll(tabela);
        scrollTabela.setOpaque(false);
        scrollTabela.getViewport().setOpaque(false);
        pagina.add(scrollTabela, c);
    }

    private void setupEntidadeColunaTabela(JPanel tabela, Entidade entidade, int i) {


        JPanel produtoPainel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(produtoPainel);
        produtoPainel.setLayout(gbl);
        produtoPainel.setBackground(Color.WHITE);
//        produtoPainel.setBorder(new MatteBorder(1, 0, 0, 0, new Color(240, 240, 240)));
        produtoPainel.setBorder(new EmptyBorder(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_MEDIO));

        FontePrincipal fonte = new FontePrincipal(Font.PLAIN, 16);
        GridBagConstraints c = new GridBagConstraints();
//        c.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO);
        c.gridheight = 4;
        c.insets = new Insets(0, 0, 0, ConstantesSwing.PADDING_PEQUENO);
        JPanel imagem = new JPanel();
        imagem.setBackground(new Color(240, 240, 240));
        imagem.setPreferredSize(new Dimension(140, 140));
        produtoPainel.add(imagem, c);

        c.gridx = 1;
        c.weightx = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        JLabel nome = new JLabel(entidade.getNome());
        nome.setFont(new FontePrincipal(Font.BOLD, 20));
        produtoPainel.add(nome, c);

        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        JLabel indentificadorEntidade = new JLabel(entidade.getCpf());
        nome.setFont(fonte);
        produtoPainel.add(indentificadorEntidade, c);

        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        JLabel tipoEntidade = new JLabel(entidade.getTipo().toString());
        nome.setFont(fonte);
        produtoPainel.add(tipoEntidade, c);

        if (getTipoAba() == TipoAbaEntidade.Normal) {
            c.gridy = 3;
            c.gridx = 2;
            c.weightx = 1;
            c.weighty = 0;
            c.gridheight = 1;
            c.anchor = GridBagConstraints.NORTHEAST;
            BotaoEditar botaoEditar = new BotaoEditar("Editar");
            botaoEditar.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    controller.cliqueEditarEntidade(entidade);
                }
            });
            produtoPainel.add(botaoEditar, c);

            c.gridy = 3;
            c.gridx = 3;
            c.weightx = 0;
            c.weighty = 0;
            c.gridheight = 1;
            c.insets = new Insets(0, 0, 0 , 0);
            c.anchor = GridBagConstraints.NORTHWEST;
            BotaoRemover botaoRemover = new BotaoRemover("Remover");
            botaoRemover.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    controller.cliqueApagarEntidade(entidade);
                }
            });
            produtoPainel.add(botaoRemover, c);
        } else {
            c.gridy = 3;
            c.gridx = 3;
            c.weightx = 1;
            c.weighty = 0;
            c.gridheight = 1;
            c.anchor = GridBagConstraints.NORTHEAST;
            BotaoEditar botaoEditar = new BotaoEditar("Selecionar");
            botaoEditar.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    cliqueSelecionarEntidade(entidade);
                }
            });
            produtoPainel.add(botaoEditar, c);

        }

        GridBagConstraints cProduto = new GridBagConstraints();
        cProduto.weightx = 1;
        cProduto.gridy = i;

        cProduto.fill = GridBagConstraints.HORIZONTAL;
        cProduto.anchor = GridBagConstraints.NORTHWEST;
        tabela.add(produtoPainel, cProduto);
    }

    private void setupNomeColunasTabela(JPanel tabela) {
        JPanel painelNomes = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(painelNomes);
        painelNomes.setLayout(gbl);

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
        cNomes.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, ConstantesSwing.PADDING_PEQUENO, 0);
        tabela.add(painelNomes, cNomes);
    }

    public String getPesquisa() {
        if (inputPesquisa == null) return "";
        return inputPesquisa.getText();
    }
}
