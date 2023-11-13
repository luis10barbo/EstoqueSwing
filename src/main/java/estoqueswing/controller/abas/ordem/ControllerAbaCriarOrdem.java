package estoqueswing.controller.abas.ordem;

import estoqueswing.dao.ordem.OrdemDAO;
import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.dao.produto.ProdutoFornecedorDAO;
import estoqueswing.dao.produto.ProdutoOrdemDAO;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.ordem.NaturezaOrdem;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.ordem.OrdemEntrada;
import estoqueswing.model.ordem.OrdemSaida;
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
        ordem.setProdutosOrdem(produtosOrdem);
        OrdemDAO.criarOrdem(ordem);
        aba.atualizarPagina();
    }

    public void cliqueAdicionarProdutoOrdem() {
        if (aba.cbNaturezaOrdem.getSelectedItem() == NaturezaOrdem.Entrada) adicionarProdutoEntrada();
        else if (aba.cbNaturezaOrdem.getSelectedItem() == NaturezaOrdem.Saida) adicionarProdutoSaida();
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
        if (!(aba.getOrdem() instanceof OrdemEntrada)) return;
        OrdemEntrada ordemEntrada = (OrdemEntrada) aba.getOrdem();

        Produto produtoSelecionado = null;
        if (ordemEntrada.getFornecedor() != null) {
            Popup popup = JanelaPrincipal.adquirir().criarPopup();
            AbaSelecionarProduto abaSelecionarProduto = new AbaSelecionarProduto(popup, ordemEntrada.getFornecedor());
            popup.adicionarAba(abaSelecionarProduto).mostrar();
            produtoSelecionado = abaSelecionarProduto.getProdutoSelecionado();
            if (produtoSelecionado == null) {
                return;
            }
            ProdutoFornecedor produtoFornecedor = ProdutoFornecedorDAO.adquirir(produtoSelecionado, ordemEntrada.getFornecedor());
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

        ordemEntrada.setFornecedor(produtoFornecedorSelecionado.getFornecedor());
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
        if (aba.getOrdem() instanceof  OrdemSaida) {
            ((OrdemSaida) aba.getOrdem()).setDestinatario(abaSelecionarCliente.getEntidadeSelecionada());
        }
        aba.atualizarPagina();
    }

    public void cliqueSelecionarFornecedor() {
        Popup popup = JanelaPrincipal.adquirir().criarPopup();
        AbaSelecionarFornecedor abaSelecionarFornecedor = new AbaSelecionarFornecedor(popup);
        popup.adicionarAba(abaSelecionarFornecedor).mostrar();

        if (aba.getOrdem() instanceof OrdemEntrada) {
            ((OrdemEntrada) aba.getOrdem()).setFornecedor(abaSelecionarFornecedor.getEntidadeSelecionada());
        }
        aba.atualizarPagina();
    }

    public void cliqueRemoverProdutoOrdem(ProdutoOrdem produtoOrdem) {
        aba.produtosOrdem.remove(produtoOrdem);
        if (aba.produtosOrdem.isEmpty() && aba.getOrdem() instanceof OrdemEntrada) {
            ((OrdemEntrada) aba.getOrdem()).setFornecedor(null);
        }
        aba.atualizarPagina();
    }

    public void cliqueTrocarNatureza(NaturezaOrdem novaNatureza) {
        Ordem novaOrdem = null;
        if (novaNatureza == NaturezaOrdem.Entrada) {
            OrdemEntrada novaOrdemEntrada = new OrdemEntrada();
            novaOrdem = novaOrdemEntrada;
        } else if (novaNatureza == NaturezaOrdem.Saida) {
            OrdemSaida novaOrdemSaida = new OrdemSaida();
            novaOrdem = novaOrdemSaida;
        }
        assert novaOrdem != null;
        novaOrdem.setTransportadora(aba.getOrdem().getTransportadora());
        novaOrdem.setEstoque(aba.getEstoque());

        aba.setOrdem(novaOrdem);
        aba.atualizarPagina();
    }
}
