package estoqueswing.view.swing.aba.ordem;

import estoqueswing.controller.abas.ordem.ControllerAbaOrdens;
import estoqueswing.dao.ordem.OrdemDAO;
import estoqueswing.model.constantes.ConstantesSwing;
import estoqueswing.model.ordem.NaturezaOrdem;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.componentes.Scroll;
import estoqueswing.view.swing.componentes.botoes.BotaoConfirmar;
import estoqueswing.view.swing.componentes.botoes.BotaoEditar;
import estoqueswing.view.swing.componentes.botoes.BotaoRemover;
import estoqueswing.view.swing.fontes.FontePrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbaOrdens extends Aba {
    ControllerAbaOrdens controller = new ControllerAbaOrdens(this);
    Ordem[] ordens ;
    JComboBox<NaturezaOrdem> cbNaturezaOrdem = new JComboBox<>(new NaturezaOrdem[]{NaturezaOrdem.Nenhum, NaturezaOrdem.Compra, NaturezaOrdem.Venda});
    JCheckBox checkMostrarFinalizadas = new JCheckBox("Mostrar finalizadas");
    public AbaOrdens() {
        cbNaturezaOrdem.setSelectedItem(0);
        ordens = adquirirOrdens();
        setupPagina();
        BotaoConfirmar botaoCriarOrdem = new BotaoConfirmar("Criar Ordem");
        botaoCriarOrdem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueCriarOrdem();
            }
        });
        cbNaturezaOrdem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                atualizarPagina();
            }
        });
        checkMostrarFinalizadas.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                atualizarPagina();
            }
        });
        checkMostrarFinalizadas.setOpaque(false);
        cabecalho.add(botaoCriarOrdem);
    }

    public Ordem[] adquirirOrdens() {
        return OrdemDAO.adquirirOrdens((NaturezaOrdem) cbNaturezaOrdem.getSelectedItem(), checkMostrarFinalizadas.isSelected());
    }

    @Override
    public void atualizarPagina() {
        ordens = adquirirOrdens();
        setupPagina();
        revalidate();
        repaint();
    }

    private void setupPagina() {
        pagina.removeAll();

        GridBagConstraints gbcPagina = new GridBagConstraints();
        pagina.setLayout(new GridBagLayout());

        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setOpaque(false);

        gbcPagina.weightx = 1;
        gbcPagina.gridx = 0;
        gbcPagina.insets = new Insets(0, 0, 0, ConstantesSwing.PADDING_MENOR);
        painelCabecalho.add(checkMostrarFinalizadas, gbcPagina);

        gbcPagina.gridx = 1;
        gbcPagina.gridy = 0;
        gbcPagina.weightx = 0;
        gbcPagina.anchor = GridBagConstraints.EAST;
        gbcPagina.insets = new Insets(0, 0, 0, ConstantesSwing.PADDING_PEQUENO );
        painelCabecalho.add(cbNaturezaOrdem, gbcPagina);

        gbcPagina.gridx = 0;
        pagina.add(painelCabecalho, gbcPagina);

        gbcPagina.gridy ++;
        gbcPagina.weightx = 1;
        gbcPagina.fill = GridBagConstraints.HORIZONTAL;
        JPanel painelOrdens = new JPanel(new GridBagLayout());
        painelOrdens.setOpaque(false);
        for (Ordem ordem: ordens) {
            gbcPagina.gridy ++;
            gbcPagina.insets = new Insets(ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_PEQUENO);
            painelOrdens.add(criarOrdemPagina(ordem), gbcPagina);
        }

        gbcPagina.gridy ++;
        gbcPagina.weighty = 1;
        JPanel espacador = new JPanel();
        espacador.setOpaque(false);
        painelOrdens.add(espacador, gbcPagina);

        gbcPagina.weighty = 1;
        gbcPagina.fill = GridBagConstraints.BOTH;
        Scroll scroll = new Scroll(painelOrdens);
        pagina.add(scroll, gbcPagina);


        gbcPagina.weighty = 0;
    }

    private JPanel criarOrdemPagina(Ordem ordem) {
        FontePrincipal fonte = new FontePrincipal(Font.PLAIN);
        JPanel painelOrdem = new JPanel(new GridBagLayout());
        painelOrdem.setOpaque(false);
        GridBagConstraints gbcOrdem = new GridBagConstraints();
        gbcOrdem.gridx = 0;
        gbcOrdem.gridy = 0;
        gbcOrdem.weightx = 1;
        gbcOrdem.gridwidth = 6;
        gbcOrdem.anchor = GridBagConstraints.WEST;

        JLabel labelTitulo = new JLabel("Ordem " + ordem.getIdOrdem() + "(" + ordem.getNatureza().toString() + ")");
        labelTitulo.setFont(new FontePrincipal(Font.PLAIN, 20));
        painelOrdem.add(labelTitulo, gbcOrdem);

        gbcOrdem.gridy ++;
        gbcOrdem.gridwidth = 1;
        gbcOrdem.weightx = 0;
        for (ProdutoOrdem produtoOrdem: ordem.getProdutosOrdem()) {
            gbcOrdem.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0, 0);
            gbcOrdem.gridx = 0;
            gbcOrdem.gridy ++;
            JLabel labelNomeProduto = new JLabel(produtoOrdem.getProduto().getNome());
            labelNomeProduto.setFont(fonte);
            painelOrdem.add(labelNomeProduto, gbcOrdem);

            gbcOrdem.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, 0, 0);
            gbcOrdem.gridx ++;
            JLabel labelQuantidade = new JLabel("Quantidade: " + produtoOrdem.getQuantidade());
            labelQuantidade.setFont(fonte);
            painelOrdem.add(labelQuantidade, gbcOrdem);

            gbcOrdem.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, 0, 0);
            gbcOrdem.gridx ++;
            JLabel labelValorProduto = new JLabel("Valor: " + produtoOrdem.getValorProduto());
            labelValorProduto.setFont(fonte);
            painelOrdem.add(labelValorProduto, gbcOrdem);
        }
        gbcOrdem.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, 0, 0);
        gbcOrdem.anchor = GridBagConstraints.EAST;
        gbcOrdem.weightx = 1;
        gbcOrdem.gridy ++;

        if (!ordem.isFinalizada()) {
            gbcOrdem.gridx ++;
            BotaoConfirmar botaoFinalizarOrdem = new BotaoConfirmar("Finalizar");
            botaoFinalizarOrdem.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    controller.cliqueFinalizarOrdem(ordem);
                }
            });
            painelOrdem.add(botaoFinalizarOrdem, gbcOrdem);
            gbcOrdem.weightx = 0;

        }
        gbcOrdem.gridx ++;
        BotaoRemover botaoRemoverOrdem = new BotaoRemover("Remover");
        botaoRemoverOrdem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueRemoverOrdem(ordem);
            }
        });
        painelOrdem.add(botaoRemoverOrdem, gbcOrdem);

        if (!ordem.isFinalizada()) {
            gbcOrdem.weightx = 0;
            gbcOrdem.gridx ++;
            BotaoEditar botaoEditarOrdem = new BotaoEditar("Editar");
            botaoEditarOrdem.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    controller.cliqueEditarOrdem(ordem);
                }
            });
            painelOrdem.add(botaoEditarOrdem, gbcOrdem);
        }


        gbcOrdem.anchor = GridBagConstraints.WEST;
        gbcOrdem.insets = new Insets(0 , 0, 0,0);
        gbcOrdem.gridx = 0;
        gbcOrdem.gridwidth = 3;
        double resultadoLiquido = 0;
        for (ProdutoOrdem produtoOrdem : ordem.getProdutosOrdem()) {
            resultadoLiquido = (produtoOrdem.getValorProduto() * produtoOrdem.getQuantidade());
        }
        // modificador de resultado dependente da operacao
        // compra vai retirar do dinheiro
        // venda vai adicionar ao dinheiro
        resultadoLiquido *= ordem.getNatureza() == NaturezaOrdem.Compra ? -1 : 1;
        JLabel labelFinalizado = new JLabel("Finalizado: " + (ordem.isFinalizada() ? "Sim" : "Nao") + " | Resultado Liquido: R$ " + resultadoLiquido);
        labelFinalizado.setFont(fonte);
        painelOrdem.add(labelFinalizado, gbcOrdem);

        return painelOrdem;
    }

    @Override
    public String getTitulo() {
        return "Ordens";
    }
}
