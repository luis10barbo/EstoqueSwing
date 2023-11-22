package estoqueswing.controller.abas.ordem;

import estoqueswing.dao.ordem.OrdemDAO;
import estoqueswing.dao.produto.ProdutoEstoqueDAO;
import estoqueswing.dao.produto.ProdutoFornecedorDAO;
import estoqueswing.exceptions.ordem.ExcecaoCriarOrdemSemDestinatario;
import estoqueswing.exceptions.ordem.ExcecaoCriarOrdemSemProduto;
import estoqueswing.exceptions.ordem.ExcecaoCriarOrdemSemTransportadora;
import estoqueswing.exceptions.ordem.ExcecaoCriarOrdemVendaItensInsuficientes;
import estoqueswing.model.ordem.NaturezaOrdem;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.ordem.OrdemCompra;
import estoqueswing.model.ordem.OrdemVenda;
import estoqueswing.model.produto.Produto;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.model.produto.ProdutoFornecedor;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.entidade.AbaSelecionarCliente;
import estoqueswing.view.swing.aba.entidade.AbaSelecionarFornecedor;
import estoqueswing.view.swing.aba.entidade.AbaSelecionarTransportadora;
import estoqueswing.view.swing.aba.estoque.AbaSelecionarProdutoEstoque;
import estoqueswing.view.swing.aba.ordem.AbaCriarOrdem;
import estoqueswing.view.swing.aba.produto.AbaSelecionarFornecedorProduto;
import estoqueswing.view.swing.aba.produto.AbaSelecionarProduto;
import estoqueswing.view.swing.componentes.Popup;

public class ControllerAbaCriarOrdem {
    AbaCriarOrdem aba;
    public ControllerAbaCriarOrdem(AbaCriarOrdem aba) {
        this.aba = aba;
    }

    public void cliqueCriarOrdem(Ordem ordem, ProdutoOrdem[] produtosOrdem) {
        if (produtosOrdem.length==0){
            throw new ExcecaoCriarOrdemSemProduto();
        }
        ordem.setProdutosOrdem(produtosOrdem);
        if (ordem.getTransportadora()==null){
            throw new ExcecaoCriarOrdemSemTransportadora();
        }

        if (ordem instanceof OrdemVenda ) {
            if (((OrdemVenda) ordem).getDestinatario() == null) {
                throw new ExcecaoCriarOrdemSemDestinatario();
            }
            for (ProdutoOrdem produtoOrdem: produtosOrdem) {
                // Checar se existem produtos o suficiente para vender
                ProdutoEstoque produtoEstoque = ProdutoEstoqueDAO.adquirir(produtoOrdem.getProduto().getId());
                if (produtoEstoque != null && produtoOrdem.getQuantidade() > produtoEstoque.getQuantidade()) {
                    throw new ExcecaoCriarOrdemVendaItensInsuficientes(produtoOrdem, produtoEstoque);
                }
            }
        }

        OrdemDAO.criarOrdem(ordem);
        aba.atualizarPagina();
        JanelaPrincipal.adquirir().voltarAba();
    }

    public void cliqueAdicionarProdutoOrdem() {
        if (aba.cbNaturezaOrdem.getSelectedItem() == NaturezaOrdem.Compra) adicionarProdutoEntrada();
        else if (aba.cbNaturezaOrdem.getSelectedItem() == NaturezaOrdem.Venda) adicionarProdutoSaida();
    }
    private void adicionarProdutoSaida() {
        Popup popup = JanelaPrincipal.adquirir().criarPopup();
        AbaSelecionarProdutoEstoque abaSelecionarProduto = new AbaSelecionarProdutoEstoque(popup);
        popup.adicionarAba(abaSelecionarProduto).mostrar();
        ProdutoEstoque produtoSelecionado = abaSelecionarProduto.getProdutoSelecionado();
        if (produtoSelecionado == null) {
            return;
        }

        aba.produtosOrdem.add(new ProdutoOrdem(produtoSelecionado.getProduto(), aba.ordem, produtoSelecionado.getValorVenda(), 1));
        aba.atualizarPagina();
    }
    private void adicionarProdutoEntrada() {
        if (!(aba.getOrdem() instanceof OrdemCompra)) return;
        OrdemCompra ordemCompra = (OrdemCompra) aba.getOrdem();

        Produto produtoSelecionado = null;
        if (ordemCompra.getFornecedor() != null) {
            Popup popup = JanelaPrincipal.adquirir().criarPopup();
            AbaSelecionarProduto abaSelecionarProduto = new AbaSelecionarProduto(popup, ordemCompra.getFornecedor());
            popup.adicionarAba(abaSelecionarProduto).mostrar();
            produtoSelecionado = abaSelecionarProduto.getProdutoSelecionado();
            if (produtoSelecionado == null) {
                return;
            }
            ProdutoFornecedor produtoFornecedor = ProdutoFornecedorDAO.adquirir(produtoSelecionado, ordemCompra.getFornecedor());
            if (produtoFornecedor == null) return;

            aba.produtosOrdem.add(new ProdutoOrdem(produtoSelecionado, aba.getOrdem(), produtoFornecedor.getValorProduto(), 1));
            aba.atualizarPagina();
            return;
        }

        Popup popup = JanelaPrincipal.adquirir().criarPopup();
        AbaSelecionarProduto abaSelecionarProduto = new AbaSelecionarProduto(popup);
        popup.adicionarAba(abaSelecionarProduto).mostrar();
        produtoSelecionado = abaSelecionarProduto.getProdutoSelecionado();
        if (produtoSelecionado == null) {
            return;
        }
        Popup popupProdutoFornecedor = JanelaPrincipal.adquirir().criarPopup();
        AbaSelecionarFornecedorProduto abaSelecionarFornecedorProduto = new AbaSelecionarFornecedorProduto(popupProdutoFornecedor, produtoSelecionado);
        popupProdutoFornecedor.adicionarAba(abaSelecionarFornecedorProduto).mostrar();
        ProdutoFornecedor produtoFornecedorSelecionado = abaSelecionarFornecedorProduto.getProdutoFornecedorSelecionado();
        if (produtoFornecedorSelecionado == null) {
            return;
        }

        ordemCompra.setFornecedor(produtoFornecedorSelecionado.getFornecedor());
        aba.produtosOrdem.add(new ProdutoOrdem(produtoSelecionado, aba.getOrdem(), produtoFornecedorSelecionado.getValorProduto(), 0));
        aba.atualizarPagina();
    }


//    public void cliqueSelecionarEstoque() {
//        Popup popup = JanelaPrincipal.adquirir().criarPopup();
//        AbaSelecionarEstoque abaSelecionarEstoque = new AbaSelecionarEstoque(popup);
//        popup.adicionarAba(abaSelecionarEstoque).mostrar();
//        aba.setEstoque(abaSelecionarEstoque.getEstoqueSelecionado());
//        aba.atualizarPagina();
//    }

    public void cliqueSelecionarTransportadora() {
        Popup popup = JanelaPrincipal.adquirir().criarPopup();
        AbaSelecionarTransportadora abaSelecionarTransportadora = new AbaSelecionarTransportadora(popup);
        popup.adicionarAba(abaSelecionarTransportadora).mostrar();
        aba.setTransportadora(abaSelecionarTransportadora.getEntidadeSelecionada());
        aba.atualizarPagina();
    }

    public void cliqueCriarOrdem(Ordem ordem) {}

    public void cliqueSelecionarDestinatario() {
        Popup popup = JanelaPrincipal.adquirir().criarPopup();
        AbaSelecionarCliente abaSelecionarCliente = new AbaSelecionarCliente(popup);
        popup.adicionarAba(abaSelecionarCliente).mostrar();
        if (aba.getOrdem() instanceof OrdemVenda) {
            ((OrdemVenda) aba.getOrdem()).setDestinatario(abaSelecionarCliente.getEntidadeSelecionada());
        }
        aba.atualizarPagina();
    }

    public void cliqueSelecionarFornecedor() {
        Popup popup = JanelaPrincipal.adquirir().criarPopup();
        AbaSelecionarFornecedor abaSelecionarFornecedor = new AbaSelecionarFornecedor(popup);
        popup.adicionarAba(abaSelecionarFornecedor).mostrar();

        if (aba.getOrdem() instanceof OrdemCompra && abaSelecionarFornecedor.getEntidadeSelecionada()!=null) {
            ((OrdemCompra) aba.getOrdem()).setFornecedor(abaSelecionarFornecedor.getEntidadeSelecionada());
        }
        aba.atualizarPagina();
    }

    public void cliqueRemoverProdutoOrdem(ProdutoOrdem produtoOrdem) {
        aba.produtosOrdem.remove(produtoOrdem);
        if (produtoOrdem.getId() != 0) {
            aba.produtosOrdemRemovidos.add(produtoOrdem);
        }

        if (aba.produtosOrdem.isEmpty() && aba.getOrdem() instanceof OrdemCompra) {
            ((OrdemCompra) aba.getOrdem()).setFornecedor(null);
        }
        aba.atualizarPagina();
    }

    public void cliqueTrocarNatureza(NaturezaOrdem novaNatureza) {
        Ordem novaOrdem = null;
        if (novaNatureza == NaturezaOrdem.Compra) {
            OrdemCompra novaOrdemCompra = new OrdemCompra();
            novaOrdem = novaOrdemCompra;
        } else if (novaNatureza == NaturezaOrdem.Venda) {
            OrdemVenda novaOrdemVenda = new OrdemVenda();
            novaOrdem = novaOrdemVenda;
        }
        assert novaOrdem != null;
        novaOrdem.setTransportadora(aba.getOrdem().getTransportadora());
        novaOrdem.setEstoque(aba.getEstoque());

        aba.setOrdem(novaOrdem);
        aba.atualizarPagina();
    }
}
