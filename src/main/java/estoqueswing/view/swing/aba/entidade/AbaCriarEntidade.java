package estoqueswing.view.swing.aba.entidade;

import estoqueswing.controller.abas.entidades.ControllerAbaCriarEntidade;
import estoqueswing.exceptions.entidade.ExcecaoCriarTransportadoraSemFrete;
import estoqueswing.exceptions.entidade.ExcecaoFreteInvalidoTransportadora;
import estoqueswing.model.Endereco;
import estoqueswing.model.telefone.Telefone;
import estoqueswing.model.constantes.ConstantesSwing;
import estoqueswing.model.entidade.*;
import estoqueswing.model.telefone.TipoTelefone;
import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.componentes.botoes.Botao;
import estoqueswing.view.swing.componentes.botoes.BotaoConfirmar;
import estoqueswing.view.swing.componentes.inputs.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbaCriarEntidade extends Aba {
    ControllerAbaCriarEntidade controller = new ControllerAbaCriarEntidade(this);
    Entidade entidade = null;
    public Botao botaoCriar = new BotaoConfirmar(getTextoConfirmar());
    public Input inputNome = new Input("Nome");
    public Input inputDescricao = new Input("Descricao");

    public Input inputCPF = new Input("CPF");
    public Input inputCNPJ = new Input("CNPJ");
    public Input inputDDD = new Input("DDD");
    public Input inputNumeroTelefone = new Input("Numero");
    public Input inputPais = new Input("Pais");
    public Input inputEstado = new Input("Estado");
    public Input inputCidade = new Input("Cidade");
    public Input inputBairro = new Input("Bairro");

    public Input inputLogradouro = new Input("Logradouro");
    public Input inputNumeroEndereco = new Input("Numero");
    public Input inputComplemento = new Input("Complemento");
    public Input inputValorFrete = new Input("Frete");
    public Input inputPrazoMedio = new Input("Prazo Medio");
    public Input inputCEP = new Input("CEP");
    public JComboBox<DisponibilidadeEntidade> cbDisponibilidade = new JComboBox<>(new DisponibilidadeEntidade[]{DisponibilidadeEntidade.Disponivel, DisponibilidadeEntidade.Indisponivel});
    public JComboBox<TipoEntidade> jcbTipoEntidade = new JComboBox<>(new TipoEntidade[]{TipoEntidade.Cliente, TipoEntidade.Transportadora, TipoEntidade.Fornecedor});
    public JComboBox<TipoTelefone> jcbTipoTelefone = new JComboBox<>(new TipoTelefone[]{TipoTelefone.Fixo, TipoTelefone.Celular});


    public AbaCriarEntidade() {
        super();
        setupPagina();
        botaoCriar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                cliqueConfirmar();
            }
        });
        jcbTipoEntidade.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                atualizarPagina();
            }
        });
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

    public String getTextoConfirmar() {
        return "Criar";
    }
    @Override
    public String getTitulo() {
        return "Criar Entidade";
    }

    public void cliqueConfirmar() {
        controller.botaoCriarEntidade(getEntidade());
    }

    public Entidade getEntidade() {
        Entidade entidadeAtual = null;
        Endereco endereco = new Endereco(inputPais.getText(), inputEstado.getText(), inputCidade.getText(), inputBairro.getText(), inputLogradouro.getText(), inputNumeroEndereco.getText(), inputComplemento.getText(), inputCEP.getText());
        Telefone telefone = new Telefone(inputNumeroTelefone.getText(), inputDDD.getText(), ((TipoTelefone) jcbTipoTelefone.getSelectedItem()));
        switch ((TipoEntidade) jcbTipoEntidade.getSelectedItem()) {
            case Cliente:
                entidadeAtual = new Cliente(inputNome.getText(), inputCPF.getText(), inputCNPJ.getText(), endereco, telefone);
                if (entidade != null && entidade instanceof Cliente) {
                    ((Cliente) entidadeAtual).setIdCliente(((Cliente) entidade).getIdCliente());
                }
                break;
            case Fornecedor:
                entidadeAtual = new Fornecedor(inputNome.getText(), inputCPF.getText(), inputCNPJ.getText(), endereco, telefone);
                if (entidade != null && entidade instanceof Fornecedor) {
                    ((Fornecedor) entidadeAtual).setIdFornecedor(((Fornecedor) entidade).getIdFornecedor());
                }
                break;
            case Transportadora:
                try {
                    entidadeAtual = new Transportadora(inputNome.getText(), inputCPF.getText(), inputCNPJ.getText(), endereco, telefone, Double.parseDouble(inputValorFrete.getText()), inputPrazoMedio.getText(), (DisponibilidadeEntidade) cbDisponibilidade.getSelectedItem());
                }catch (NumberFormatException e){
                    if (inputValorFrete.getText().isEmpty())
                        throw new ExcecaoCriarTransportadoraSemFrete();

                    throw  new ExcecaoFreteInvalidoTransportadora();
                }
                if (entidade != null && entidade instanceof Transportadora) {
                    ((Transportadora) entidadeAtual).setIdTransportadora(((Transportadora) entidade).getIdTransportadora());
                }
                break;
            default:
                return null;
        }
        if (entidade != null && entidade.getIdEntidade() != 0) entidadeAtual.setIdEntidade(entidade.getIdEntidade());
        return entidadeAtual;
    }

    private void setupPagina() {
        pagina.removeAll();

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbcPagina = new GridBagConstraints();
        gbl.layoutContainer(pagina);
        pagina.setLayout(gbl);
//        pagina.setBorder(new EmptyBorder(ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO));
//        pagina.setBackground(Color.blue);


        JPanel topoInputs = new JPanel();
        GridBagLayout painelInputsGBL = new GridBagLayout();
        topoInputs.setLayout(painelInputsGBL);
        topoInputs.setOpaque(false);


        GridBagConstraints c = new GridBagConstraints();
        c.gridheight = 3;
        c.gridy = 0;
        c.gridx = 0;

        c.anchor = GridBagConstraints.NORTHWEST;
//        c.insets = new Insets(0, ConstantesSwing.PADDING_PEQUENO,0,0);

        JPanel painelImagem = new JPanel();
        painelImagem.setPreferredSize(new Dimension(137, 137));
        painelImagem.setBackground(new Color(240, 240, 240));
        topoInputs.add(painelImagem, c);

        c.gridheight = 1;
        c.weightx = 1;
        c.gridx = 1;
        c.insets = new Insets(0,ConstantesSwing.PADDING_PEQUENO, 0, 0);

        c.fill = GridBagConstraints.HORIZONTAL;
        topoInputs.add(inputNome, c);
        c.gridy = 1;
        c.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, 0, 0);

        topoInputs.add(inputDescricao, c);

        // TERCEIRA ROW
        c.gridy = 2;
        JPanel terceiraRow = new JPanel();
        GridBagLayout gblTerceiraRow = new GridBagLayout();
        GridBagConstraints gbcTerceiraRow = new GridBagConstraints();
        gbcTerceiraRow.weightx = 1;
        gbcTerceiraRow.fill = GridBagConstraints.HORIZONTAL;
        terceiraRow.setLayout(gblTerceiraRow);
        gblTerceiraRow.layoutContainer(topoInputs);
        terceiraRow.setOpaque(false);
        terceiraRow.add(inputCPF, gbcTerceiraRow);
        gbcTerceiraRow.insets = new Insets(0, ConstantesSwing.PADDING_PEQUENO, 0, 0);
        terceiraRow.add(inputCNPJ, gbcTerceiraRow);
        terceiraRow.add(jcbTipoEntidade, gbcTerceiraRow);
        topoInputs.add(terceiraRow, c);

        JPanel inputs = new JPanel();
        inputs.setOpaque(false);
        GridBagLayout gblInputs = new GridBagLayout();
        gblInputs.layoutContainer(pagina);
        inputs.setLayout(gblInputs);
        GridBagConstraints gbcInputs = new GridBagConstraints();
        gbcInputs.weightx = 1;
        gbcInputs.fill = GridBagConstraints.BOTH;
        gbcInputs.anchor = GridBagConstraints.NORTHWEST;
        inputs.add(topoInputs, gbcInputs);

        // QUARTA ROW
        gbcInputs.gridy = 1;
        JPanel quartaRow = new JPanel();
        quartaRow.setOpaque(false);
        GridBagLayout gblQuartaRow = new GridBagLayout();
        GridBagConstraints gbcQuartaRow = new GridBagConstraints();
        gblQuartaRow.layoutContainer(inputs);
        quartaRow.setLayout(gblQuartaRow);
        gbcQuartaRow.weightx = 1;
        gbcQuartaRow.fill = GridBagConstraints.HORIZONTAL;
        gbcQuartaRow.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0, 0);
        quartaRow.add(inputDDD, gbcQuartaRow);

        gbcQuartaRow.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, 0, 0);
        quartaRow.add(inputNumeroTelefone, gbcQuartaRow);
        quartaRow.add(jcbTipoTelefone, gbcQuartaRow);
        inputs.add(quartaRow, gbcInputs);

        // QUINTA ROW
        gbcInputs.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0, 0);
        gbcInputs.gridy = 2;
        JPanel quintaRow = new JPanel();
        GridBagLayout gblQuintaRow = new GridBagLayout();
        GridBagConstraints gbcQuintaRow = new GridBagConstraints();
        gbcQuintaRow.weightx = 1;
        gbcQuintaRow.fill = GridBagConstraints.HORIZONTAL;
        gblQuintaRow.layoutContainer(inputs);
        quintaRow.setLayout(gblQuintaRow);
        quintaRow.setOpaque(false);
        quintaRow.add(inputPais, gbcQuintaRow);
        gbcQuintaRow.insets = new Insets(0, ConstantesSwing.PADDING_PEQUENO, 0, 0);
        quintaRow.add(inputEstado, gbcQuintaRow);
        quintaRow.add(inputCidade, gbcQuintaRow);
        quintaRow.add(inputBairro, gbcQuintaRow);

        gbcQuintaRow.gridy = 1;
        gbcQuintaRow.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0, 0);
        quintaRow.add(inputLogradouro, gbcQuintaRow);
        gbcQuintaRow.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, 0, 0);
        quintaRow.add(inputNumeroEndereco, gbcQuintaRow);
        quintaRow.add(inputComplemento, gbcQuintaRow);
        quintaRow.add(inputCEP, gbcQuintaRow);

        if (jcbTipoEntidade.getSelectedItem() == TipoEntidade.Transportadora) {
            gbcQuintaRow.gridy = 2;
            gbcQuintaRow.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, 0, 0);
            gbcQuintaRow.gridwidth = 2;
            quintaRow.add(inputValorFrete, gbcQuintaRow);
            gbcQuintaRow.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, 0, 0);
            quintaRow.add(cbDisponibilidade, gbcQuintaRow);
            gbcQuintaRow.gridwidth = 1;
        }


        inputs.add(quintaRow, gbcInputs);

        gbcPagina.insets = new Insets(ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO);

        gbcPagina.weightx = 1;
        gbcPagina.fill = GridBagConstraints.HORIZONTAL;
        pagina.add(inputs, gbcPagina);
        gbcPagina.gridy = 1;
        gbcPagina.weighty = 1;
        JPanel espacoVazio = new JPanel();
        espacoVazio.setOpaque(false);
        pagina.add(espacoVazio, gbcPagina);

        gbcPagina.gridy = 2;
        gbcPagina.weighty = 0;
        gbcPagina.anchor = GridBagConstraints.EAST;
        gbcPagina.fill = GridBagConstraints.NONE;
        pagina.add(botaoCriar, gbcPagina);

    }
}
