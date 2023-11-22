package estoqueswing.controller.abas.produto;

import estoqueswing.dao.entidades.EntidadeDAO;
import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.dao.produto.ProdutoFornecedorDAO;
import estoqueswing.exceptions.produto.ExcecaoValorInvalidoProdutoOrdem;
import estoqueswing.exceptions.produto.ExcecaoValorVazioProdutoOrdem;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.produto.Produto;
import estoqueswing.model.produto.ProdutoFornecedor;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.entidade.AbaSelecionarFornecedor;
import estoqueswing.view.swing.aba.produto.AbaCriarProduto;
import estoqueswing.view.swing.componentes.Popup;

public class ControllerAbaCriarProduto {
    AbaCriarProduto aba;
    public ControllerAbaCriarProduto(AbaCriarProduto aba) {
        this.aba = aba;
    }

    public void cliqueAdicionarFornecedor() {
        Fornecedor fornecedor = abrirSelecionarFornecedor();
        if (fornecedor == null) return;
        aba.produtoFornecedores.add(new ProdutoFornecedor(fornecedor, aba.produto, 0.0));

        aba.atualizarPagina();
    }

    public void cliqueRemoverProdutoFornecedor(ProdutoFornecedor fornecedor) {

        aba.produtoFornecedores.remove(fornecedor);
        if (fornecedor.getId() != 0) aba.produtoFornecedoresRemovidos.add(fornecedor);
        aba.atualizarPagina();
        return;


    }

    public void cliqueEditarProdutoFornecedor(ProdutoFornecedor produtoFornecedor) {
        Fornecedor fornecedor = abrirSelecionarFornecedor();
        if (fornecedor == null) return;
        produtoFornecedor.setFornecedor(fornecedor);
        aba.atualizarPagina();
    }

    public void cliqueCriarProduto(Produto produto, ProdutoFornecedor[] produtosFornecedor) {
        ProdutoDAO.adicionarProduto(produto);
        for (ProdutoFornecedor produtoFornecedor:produtosFornecedor) {
            try {
                produtoFornecedor.setValorProduto(Double.parseDouble(produtoFornecedor.getValorProdutoNaoTratado()));
            } catch (NumberFormatException ex) {
                if (produtoFornecedor.getValorProdutoNaoTratado().isEmpty()) throw new ExcecaoValorVazioProdutoOrdem(produtoFornecedor);

                throw  new ExcecaoValorInvalidoProdutoOrdem(produtoFornecedor);
            }

            if (produtoFornecedor.getFornecedor().getIdFornecedor()==0){
                EntidadeDAO.adicionarEntidade(produtoFornecedor.getFornecedor());
            }
            ProdutoFornecedorDAO.criar(produtoFornecedor);
        }
        JanelaPrincipal.adquirir().voltarAba();
    }

    public Fornecedor abrirSelecionarFornecedor() {
        Popup popup = JanelaPrincipal.adquirir().criarPopup();
        AbaSelecionarFornecedor abaSelecionarFornecedor = new AbaSelecionarFornecedor(popup);
        popup.adicionarAba(abaSelecionarFornecedor).mostrar();

        return abaSelecionarFornecedor.getEntidadeSelecionada();
    }
}
