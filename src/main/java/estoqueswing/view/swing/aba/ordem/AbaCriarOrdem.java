package estoqueswing.view.swing.aba.ordem;

import estoqueswing.controller.abas.ordem.ControllerAbaCriarOrdem;
import estoqueswing.dao.EstoqueDAO;
import estoqueswing.model.Estoque;
import estoqueswing.model.constantes.ConstantesSwing;
import estoqueswing.model.entidade.Transportadora;
import estoqueswing.model.ordem.NaturezaOrdem;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.ordem.OrdemCompra;
import estoqueswing.model.ordem.OrdemVenda;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.componentes.Scroll;
import estoqueswing.view.swing.componentes.botoes.BotaoConfirmar;
import estoqueswing.view.swing.componentes.botoes.BotaoEditar;
import estoqueswing.view.swing.componentes.botoes.BotaoRemover;
import estoqueswing.view.swing.componentes.inputs.Input;
import estoqueswing.view.swing.componentes.inputs.InputArea;
import estoqueswing.view.swing.cores.CorCinza;
import estoqueswing.view.swing.fontes.FontePrincipal;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AbaCriarOrdem extends Aba {
    public Ordem ordem = new OrdemCompra(null, null, null, "");
    private Estoque estoque = EstoqueDAO.adquirir(1);
    private ControllerAbaCriarOrdem controller = new ControllerAbaCriarOrdem(this);
    public BotaoEditar adicionarProduto = new BotaoEditar("Adicionar Produto");
    public BotaoEditar selecionarTransportadora = new BotaoEditar("Selecionar");
    public BotaoEditar selecionarEstoque = new BotaoEditar("Selecionar");
    public BotaoEditar selecionarDestinatario = new BotaoEditar("Selecionar");
    public BotaoEditar selecionarFornecedor = new BotaoEditar("Selecionar");

    public ArrayList<ProdutoOrdem> produtosOrdemRemovidos = new ArrayList<>();

    private Transportadora transportadora;
    public JComboBox<NaturezaOrdem> cbNaturezaOrdem = new JComboBox<>(new NaturezaOrdem[]{NaturezaOrdem.Compra, NaturezaOrdem.Venda});
    private InputArea inputObservacoes = new InputArea("Observacao...");
    public ArrayList<ProdutoOrdem> produtosOrdem = new ArrayList<>();
    public void setOrdem(Ordem ordem) {this.ordem = ordem;}
    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
        getOrdem().setTransportadora(transportadora);
        getOrdem().setFrete(getOrdem().getTransportadora().getFrete());
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public AbaCriarOrdem(Ordem ordem) {
        this.ordem = ordem;
        produtosOrdem.addAll(Arrays.asList(ordem.getProdutosOrdem()));
        cbNaturezaOrdem.setSelectedItem(ordem.getNatureza());
        setTransportadora(ordem.getTransportadora());
        setupListeners();
        setupPagina();

    }
    public AbaCriarOrdem() {
        super();
        setupPagina();
        if (estoque == null) {
            estoque = new Estoque("Estoque Principal", "", null);
            EstoqueDAO.criar(estoque);
        }
        setupListeners();
    }

    private void setupListeners() {
        selecionarTransportadora.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueSelecionarTransportadora();
            }
        });

        selecionarDestinatario.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueSelecionarDestinatario();
            }
        });

        selecionarFornecedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueSelecionarFornecedor();
            }
        });

        adicionarProduto.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueAdicionarProdutoOrdem();
            }
        });

        cbNaturezaOrdem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                controller.cliqueTrocarNatureza((NaturezaOrdem) cbNaturezaOrdem.getSelectedItem());
                atualizarPagina();
            }
        });
    }

    private void setupPagina() {
        pagina.removeAll();

        GridBagLayout gblPagina = new GridBagLayout();
        GridBagConstraints gbcPagina = new GridBagConstraints();
        gblPagina.layoutContainer(pagina);
        pagina.setLayout(gblPagina);

        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setOpaque(false);
        GridBagLayout gblCabecalho = new GridBagLayout();
        GridBagConstraints gbcCabecalho = new GridBagConstraints();
        gblCabecalho.layoutContainer(painelCabecalho);
        painelCabecalho.setLayout(gblCabecalho);

        gbcCabecalho.gridx = 0;
        gbcCabecalho.weightx = 1;
        gbcCabecalho.fill = GridBagConstraints.HORIZONTAL;


        JPanel painelInputs = new JPanel();
        GridBagLayout gblInputs = new GridBagLayout();
        painelInputs.setLayout(gblInputs);
        painelInputs.setOpaque(false);
        gbcCabecalho.insets = new Insets(0, ConstantesSwing.PADDING_PEQUENO, 0, 0);
        painelInputs.add(cbNaturezaOrdem, gbcCabecalho);

        gbcCabecalho.gridy = 1;
        gbcCabecalho.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, 0, 0);
        gbcCabecalho.weighty = 1;
        gbcCabecalho.fill = GridBagConstraints.BOTH;
        inputObservacoes.setPreferredSize(new Dimension(0, 100));
        painelInputs.add(new Scroll(inputObservacoes), gbcCabecalho);

        gbcCabecalho.anchor = GridBagConstraints.NORTH;
        gbcCabecalho.insets = new Insets(0,0,0,0);
        painelCabecalho.add(painelInputs, gbcCabecalho);

        gbcCabecalho.gridheight = 2;
        gbcCabecalho.gridx ++;
        gbcCabecalho.gridy = 0;
        gbcCabecalho.weightx = 0;
        gbcCabecalho.weighty = 1;
        gbcCabecalho.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecalho.anchor = GridBagConstraints.NORTH;
        gbcCabecalho.insets = new Insets(0,ConstantesSwing.PADDING_PEQUENO,0,0);
        FontePrincipal fonteTitulo = new FontePrincipal(Font.PLAIN, 16);

        if (cbNaturezaOrdem.getSelectedItem() == NaturezaOrdem.Venda) {
            // Painel Cliente
            OrdemVenda ordemVenda = (OrdemVenda) ordem;

            JPanel painelCliente = new JPanel();
            painelCliente.setBorder(new EmptyBorder(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO));
            GridBagLayout gblCliente = new GridBagLayout();
            GridBagConstraints gbcCliente = new GridBagConstraints();
            gblCliente.layoutContainer(painelCliente);
            painelCliente.setLayout(gblCliente);
            gbcCliente.gridy = 0;

            JLabel labelDestinatario = new JLabel("Destinatario");
            gbcCliente.weightx = 1;
            gbcCliente.anchor = GridBagConstraints.WEST;
            labelDestinatario.setFont(fonteTitulo);
            painelCliente.add(labelDestinatario, gbcCliente);

            gbcCliente.gridy ++;
            gbcCliente.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0,0);
            if (ordemVenda.getDestinatario() != null) {

                FontePrincipal fonte = new FontePrincipal(Font.PLAIN, 16);
                gbcCliente.weightx = 1;
                gbcCliente.anchor = GridBagConstraints.WEST;
                JLabel nomeDestinatario = new JLabel(ordemVenda.getDestinatario().getNome());
                nomeDestinatario.setFont(fonte);
                nomeDestinatario.setForeground(new CorCinza());
                painelCliente.add(nomeDestinatario, gbcCliente);

                gbcCliente.gridy ++;
                gbcCliente.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0, 0);

            }
            gbcCliente.weightx = 1;
            gbcCliente.fill = GridBagConstraints.HORIZONTAL;
            painelCliente.add(selecionarDestinatario, gbcCliente);

            gbcCabecalho.weighty = 1;
            gbcCabecalho.fill = GridBagConstraints.HORIZONTAL;
            painelCabecalho.add(painelCliente, gbcCabecalho);

        } else if (cbNaturezaOrdem.getSelectedItem() == NaturezaOrdem.Compra) {
            // Painel Fornecedor
            OrdemCompra ordemCompra = (OrdemCompra) ordem;

            JPanel painelFornecedor = new JPanel();
            painelFornecedor.setBorder(new EmptyBorder(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO));
            GridBagLayout gblFornecedor = new GridBagLayout();
            GridBagConstraints gbcFornecedor = new GridBagConstraints();
            gblFornecedor.layoutContainer(painelFornecedor);
            painelFornecedor.setLayout(gblFornecedor);
            gbcFornecedor.gridy = 0;

            gbcFornecedor.weightx = 1;
            gbcFornecedor.anchor = GridBagConstraints.WEST;
            JLabel labelFornecedor = new JLabel("Fornecedor");
            labelFornecedor.setFont(fonteTitulo);
            painelFornecedor.add(labelFornecedor, gbcFornecedor);

            gbcFornecedor.gridy ++;
            gbcFornecedor.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0,0);

            if (ordemCompra.getFornecedor() != null) {
                FontePrincipal fonte = new FontePrincipal(Font.PLAIN, 16);
                gbcFornecedor.weightx = 1;
                gbcFornecedor.anchor = GridBagConstraints.WEST;
                JLabel nomeTransportadora = new JLabel(ordemCompra.getFornecedor().getNome());
                nomeTransportadora.setFont(fonte);
                nomeTransportadora.setForeground(new CorCinza());
                painelFornecedor.add(nomeTransportadora, gbcFornecedor);

                gbcFornecedor.gridy ++;
                gbcFornecedor.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0, 0);
            }

            gbcFornecedor.weightx = 1;
            gbcFornecedor.fill = GridBagConstraints.HORIZONTAL;
            painelFornecedor.add(selecionarFornecedor, gbcFornecedor);

            gbcCabecalho.weighty = 1;
            gbcCabecalho.fill = GridBagConstraints.HORIZONTAL;
            painelCabecalho.add(painelFornecedor, gbcCabecalho);
        }

        gbcCabecalho.gridx ++;
        // Painel Transportadora
        JPanel painelTransportadora = new JPanel();
        painelTransportadora.setBorder(new EmptyBorder(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO));
        GridBagLayout gblTransportadora = new GridBagLayout();
        GridBagConstraints gbcTransportadora = new GridBagConstraints();
        gblTransportadora.layoutContainer(painelTransportadora);
        painelTransportadora.setLayout(gblTransportadora);
        gbcTransportadora.gridy = 0;

        gbcTransportadora.weightx = 1;
        gbcTransportadora.anchor = GridBagConstraints.WEST;
        JLabel labelTransportadora = new JLabel("Transportadora");
        labelTransportadora.setFont(fonteTitulo);
        painelTransportadora.add(labelTransportadora, gbcTransportadora);

        gbcTransportadora.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0, 0);
        gbcTransportadora.gridy ++;
        if (transportadora != null) {
            FontePrincipal fonte = new FontePrincipal(Font.PLAIN, 16);
            gbcTransportadora.weightx = 1;
            gbcTransportadora.anchor = GridBagConstraints.WEST;
            JLabel nomeTransportadora = new JLabel(transportadora.getNome());
            nomeTransportadora.setFont(fonte);
            nomeTransportadora.setForeground(new CorCinza());
            painelTransportadora.add(nomeTransportadora, gbcTransportadora);

            if (!transportadora.getPrazoMedio().isEmpty()) {
                gbcTransportadora.gridy ++;
                JLabel prazoTransportadora = new JLabel("Prazo: " + transportadora.getPrazoMedio());
                prazoTransportadora.setFont(fonte);
                prazoTransportadora.setForeground(new CorCinza());
                painelTransportadora.add(prazoTransportadora, gbcTransportadora);
            }

            gbcTransportadora.gridy ++;
            JLabel freteTransportadora = new JLabel(String.valueOf("Frete: R$" + transportadora.getFrete()));
            freteTransportadora.setFont(fonte);
            freteTransportadora.setForeground(new CorCinza());
            painelTransportadora.add(freteTransportadora, gbcTransportadora);

            gbcTransportadora.gridy ++;
            gbcTransportadora.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0, 0);
        }

        gbcTransportadora.weightx = 1;
        gbcTransportadora.fill = GridBagConstraints.HORIZONTAL;
        painelTransportadora.add(selecionarTransportadora,gbcTransportadora);

        gbcCabecalho.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecalho.weightx = 0;
        gbcCabecalho.anchor = GridBagConstraints.NORTH;
        gbcCabecalho.insets = new Insets(0, ConstantesSwing.PADDING_PEQUENO, 0, ConstantesSwing.PADDING_PEQUENO);

        gbcCabecalho.weighty = 1;
        gbcCabecalho.fill = GridBagConstraints.HORIZONTAL;
        painelCabecalho.add(painelTransportadora, gbcCabecalho);

        gbcPagina.gridy = 0;
        gbcPagina.weightx = 1;
        gbcPagina.fill = GridBagConstraints.HORIZONTAL;
        pagina.add(painelCabecalho, gbcPagina);

        gbcPagina.gridy ++;
        gbcPagina.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0, 0);
        pagina.add(setupTabelaProdutos(), gbcPagina);

        JPanel espacador = new JPanel();
        espacador.setBackground(Color.white);
        gbcPagina.gridheight = 1;
        gbcPagina.gridy++;
        gbcPagina.weighty = 1;
        pagina.add(espacador, gbcPagina);

        JPanel footer = new JPanel();
        footer.setOpaque(false);

        double total = 0.0;
        for (ProdutoOrdem produtoOrdem:produtosOrdem) {
            total += produtoOrdem.getValorProduto() * produtoOrdem.getQuantidade();
        }
        total += ordem.getFrete();

        gbcPagina.gridy ++;
        gbcPagina.weightx = 1;
        JLabel labelTotal = new JLabel("Total: " + total);
        labelTotal.setFont(new FontePrincipal(Font.PLAIN));
        footer.add(labelTotal, gbcPagina);

        BotaoConfirmar criar = new BotaoConfirmar(textoBotaoConfirmar());
        criar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                cliqueBotaoConfirmar();
            }
        });

        gbcPagina.weightx = 0;
        gbcPagina.weighty = 0;
        gbcPagina.fill = GridBagConstraints.NONE;
        gbcPagina.anchor = GridBagConstraints.EAST;
        gbcPagina.insets = new Insets(0, 0, ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO);
        footer.add(criar, gbcPagina);
        pagina.add(footer, gbcPagina);
    }

    public void cliqueBotaoConfirmar() {
        controller.cliqueCriarOrdem(getOrdem(), produtosOrdem.toArray(new ProdutoOrdem[0]));
    }

    @NotNull
    public String textoBotaoConfirmar() {
        return "Criar";
    }

    public Ordem getOrdem() {
        ordem.setNatureza((NaturezaOrdem) cbNaturezaOrdem.getSelectedItem());
        ordem.setTransportadora(transportadora);
        ordem.setEstoque(estoque);
        return ordem;
    }

    private JPanel setupTabelaProdutos() {
        JPanel painelProdutos = new JPanel();
        painelProdutos.setBorder(new EmptyBorder(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO));
        painelProdutos.setOpaque(false);
        GridBagLayout gblProdutos = new GridBagLayout();
        GridBagConstraints gbcProdutos = new GridBagConstraints();
        gblProdutos.layoutContainer(painelProdutos);
        painelProdutos.setLayout(gblProdutos);

        FontePrincipal fonte = new FontePrincipal(16);
        gbcProdutos.weightx = 1;
        gbcProdutos.fill = GridBagConstraints.HORIZONTAL;
        gbcProdutos.gridy = 0;
        JLabel nome = new JLabel("Nome");
        nome.setFont(fonte);
        painelProdutos.add(nome, gbcProdutos);
        JLabel quantidade = new JLabel("Quantidade");
        quantidade.setFont(fonte);
        painelProdutos.add(quantidade, gbcProdutos);
        JLabel valorProduto = new JLabel("Valor");
        valorProduto.setFont(fonte);
        painelProdutos.add(valorProduto, gbcProdutos);

        for (int i = 0; i < produtosOrdem.size(); i++) {
            ProdutoOrdem produtoOrdem = produtosOrdem.get(i);
            setupProdutoFornecedor(produtoOrdem, painelProdutos, i + 1);
        }
        gbcProdutos.anchor = GridBagConstraints.EAST;
        gbcProdutos.fill = GridBagConstraints.NONE;
        painelProdutos.add(adicionarProduto, gbcProdutos);
        return painelProdutos;
    }

    public void setupProdutoFornecedor(ProdutoOrdem produtoOrdem, JPanel painelProdutos, int y) {
        FontePrincipal fonte = new FontePrincipal(16);

        GridBagConstraints gbcProduto = new GridBagConstraints();
        gbcProduto.gridy = y;
        gbcProduto.weighty = 1;
        gbcProduto.fill = GridBagConstraints.BOTH;
        gbcProduto.anchor = GridBagConstraints.WEST;
        gbcProduto.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0,ConstantesSwing.PADDING_PEQUENO );
        JLabel nome = new JLabel(produtoOrdem.getProduto().getNome());
        nome.setFont(fonte);
        painelProdutos.add(nome, gbcProduto);

        Input quantidade = new Input("Quantidade");
        quantidade.setPreferredSize(new Dimension(30, 0));
        quantidade.setText(String.valueOf(produtoOrdem.getQuantidade()));
        painelProdutos.add(quantidade, gbcProduto);
        quantidade.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleUpdate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleUpdate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleUpdate();
            }

            public void handleUpdate() {
                if (quantidade.getText().replace(" ", "").isEmpty()) {
                    produtoOrdem.setQuantidade(0);
                }
                try {
                    produtoOrdem.setQuantidade(Integer.parseInt(quantidade.getText()));
                } catch (NumberFormatException ignored) {

                }

            }
        });
        quantidade.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                atualizarPagina();
            }
        });
        JLabel valor = new JLabel(String.valueOf(produtoOrdem.getValorProduto()));
        valor.setFont(fonte);
        painelProdutos.add(valor, gbcProduto);
        gbcProduto.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0,0);

        gbcProduto.weightx = 1;
        gbcProduto.anchor = GridBagConstraints.EAST;
        gbcProduto.fill = GridBagConstraints.NONE;
        BotaoRemover botaoRemover = new BotaoRemover("Remover");
        botaoRemover.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueRemoverProdutoOrdem(produtoOrdem);
            }
        });
        painelProdutos.add(botaoRemover, gbcProduto);
    }

    @Override
    public void atualizarPagina() {
        setupPagina();
        revalidate();
        repaint();
    }

    @Override
    public void atualizarInformacoesDB() {

    }

    @Override
    public String getTitulo() {
        return "Criar Ordem";
    }


}
