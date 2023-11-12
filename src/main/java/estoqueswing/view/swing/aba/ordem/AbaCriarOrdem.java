package estoqueswing.view.swing.aba.ordem;

import estoqueswing.controller.abas.ordem.ControllerAbaCriarOrdem;
import estoqueswing.dao.EstoqueDAO;
import estoqueswing.model.Estoque;
import estoqueswing.model.constantes.ConstantesSwing;
import estoqueswing.model.entidade.Cliente;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.entidade.Transportadora;
import estoqueswing.model.ordem.NaturezaOrdem;
import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.componentes.botoes.BotaoEditar;
import estoqueswing.view.swing.componentes.inputs.Input;
import estoqueswing.view.swing.cores.CorCinza;
import estoqueswing.view.swing.fontes.FontePrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbaCriarOrdem extends Aba {
    private Estoque estoque = EstoqueDAO.adquirir(1);
    public ControllerAbaCriarOrdem controller = new ControllerAbaCriarOrdem(this);
    public BotaoEditar adicionarProduto = new BotaoEditar("Adicionar Produto");
    public BotaoEditar selecionarTransportadora = new BotaoEditar("Selecionar");
    public BotaoEditar selecionarEstoque = new BotaoEditar("Selecionar");
    public BotaoEditar selecionarDestinatario = new BotaoEditar("Selecionar");
    public BotaoEditar selecionarFornecedor = new BotaoEditar("Selecionar");

    private Transportadora transportadora;
    private JComboBox<NaturezaOrdem> cbNaturezaOrdem = new JComboBox<>(new NaturezaOrdem[]{NaturezaOrdem.Entrada, NaturezaOrdem.Saida});
    private Input inputObservacoes = new Input("Observacao...");
    private Cliente destinatario;
    private Fornecedor fornecedor;
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }
    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }
    public void setDestinatario(Cliente destinatario) { this.destinatario = destinatario; }
    public AbaCriarOrdem() {

        super();
        setupPagina();
        if (estoque == null) {
            estoque = new Estoque("Estoque Principal", "", null);
            EstoqueDAO.criar(estoque);
        }
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

        cbNaturezaOrdem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
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
        painelInputs.add(inputObservacoes, gbcCabecalho);

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

        if (cbNaturezaOrdem.getSelectedItem() == NaturezaOrdem.Saida) {
            // Painel Cliente
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
            if (destinatario != null) {
                FontePrincipal fonte = new FontePrincipal(Font.PLAIN, 16);
                gbcCliente.weightx = 1;
                gbcCliente.anchor = GridBagConstraints.WEST;
                JLabel nomeTransportadora = new JLabel("Destinatario: " + destinatario.getNome());
                nomeTransportadora.setFont(fonte);
                nomeTransportadora.setForeground(new CorCinza());
                painelCliente.add(nomeTransportadora, gbcCliente);

                gbcCliente.gridy ++;
                gbcCliente.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0, 0);
            }
            gbcCliente.weighty = 1;
            gbcCliente.weightx = 1;
            gbcCliente.fill = GridBagConstraints.HORIZONTAL;
            gbcCliente.anchor = GridBagConstraints.SOUTH;
            painelCliente.add(selecionarDestinatario, gbcCliente);
            painelCabecalho.add(painelCliente, gbcCabecalho);

        } else if (cbNaturezaOrdem.getSelectedItem() == NaturezaOrdem.Entrada) {
            // Painel Fornecedor
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

            if (fornecedor != null) {
                FontePrincipal fonte = new FontePrincipal(Font.PLAIN, 16);
                gbcFornecedor.weightx = 1;
                gbcFornecedor.anchor = GridBagConstraints.WEST;
                JLabel nomeTransportadora = new JLabel(fornecedor.getNome());
                nomeTransportadora.setFont(fonte);
                nomeTransportadora.setForeground(new CorCinza());
                painelFornecedor.add(nomeTransportadora, gbcFornecedor);

                gbcFornecedor.gridy ++;
                gbcFornecedor.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0, 0);
            }

            gbcFornecedor.weighty = 1;
            gbcFornecedor.weightx = 1;
            gbcFornecedor.fill = GridBagConstraints.HORIZONTAL;
            gbcFornecedor.anchor = GridBagConstraints.SOUTH;
            painelFornecedor.add(selecionarFornecedor, gbcFornecedor);
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

        JLabel labelTransportadora = new JLabel("Transportadora");
        labelTransportadora.setFont(fonteTitulo);
        painelTransportadora.add(labelTransportadora);

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

            gbcTransportadora.gridy ++;
            JLabel prazoTransportadora = new JLabel("Prazo: " + transportadora.getPrazoMedio());
            prazoTransportadora.setFont(fonte);
            prazoTransportadora.setForeground(new CorCinza());
            painelTransportadora.add(prazoTransportadora, gbcTransportadora);

            gbcTransportadora.gridy ++;
            JLabel freteTransportadora = new JLabel(String.valueOf("Frete: " + transportadora.getFrete()));
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
        painelCabecalho.add(painelTransportadora, gbcCabecalho);

        gbcPagina.gridy = 0;
        gbcPagina.weightx = 1;
        gbcPagina.fill = GridBagConstraints.HORIZONTAL;
        pagina.add(painelCabecalho, gbcPagina);

        JPanel espacador = new JPanel();
        espacador.setBackground(Color.white);
        gbcPagina.gridheight = 0;
        gbcPagina.gridy++;
        gbcPagina.weighty = 1;
        pagina.add(espacador, gbcPagina);
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
