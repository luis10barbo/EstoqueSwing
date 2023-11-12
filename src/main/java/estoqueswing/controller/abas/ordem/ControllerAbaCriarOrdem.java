package estoqueswing.controller.abas.ordem;

import estoqueswing.dao.ordem.OrdemDAO;
import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.dao.produto.ProdutoOrdemDAO;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.entidade.AbaSelecionarCliente;
import estoqueswing.view.swing.aba.entidade.AbaSelecionarFornecedor;
import estoqueswing.view.swing.aba.entidade.AbaSelecionarTransportadora;
import estoqueswing.view.swing.aba.estoque.AbaSelecionarEstoque;
import estoqueswing.view.swing.aba.ordem.AbaCriarOrdem;
import estoqueswing.view.swing.componentes.Popup;

public class ControllerAbaCriarOrdem {
    AbaCriarOrdem aba;
    public ControllerAbaCriarOrdem(AbaCriarOrdem aba) {
        this.aba = aba;
    }

    public void cliqueCriarOrdem(Ordem ordem, ProdutoOrdem[] produtosOrdem) {
        OrdemDAO.criarOrdem(ordem);
        for (ProdutoOrdem produtoOrdem:produtosOrdem) {
            if(produtoOrdem.getProduto().getId()==0){
                ProdutoDAO.adicionarProduto(produtoOrdem.getProduto());
            }
            ProdutoOrdemDAO.criar(produtoOrdem);
        }
        aba.atualizarPagina();
    }

    public void cliqueAdicionarProdutoOrdem() {

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
        aba.setDestinatario(abaSelecionarCliente.getEntidadeSelecionada());
        aba.atualizarPagina();
    }

    public void cliqueSelecionarFornecedor() {
        Popup popup = JanelaPrincipal.adquirir().criarPopup();
        AbaSelecionarFornecedor abaSelecionarFornecedor = new AbaSelecionarFornecedor(popup);
        popup.adicionarAba(abaSelecionarFornecedor).mostrar();
        aba.setFornecedor(abaSelecionarFornecedor.getEntidadeSelecionada());
        aba.atualizarPagina();
    }

}
