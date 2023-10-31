package estoqueswing.model.dto;

import estoqueswing.model.Produto;
import estoqueswing.model.entidade.Entidade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProdutoDTO {
    private static final List<Produto> produtos = Arrays.asList(
            new Produto("Roupa", 1),
            new Produto("Caneta Azul", 1));

    public static Produto[] adquirirProdutos() {
        return produtos.toArray(new Produto[0]);
    }

    /**
     * Remover produto do banco de dados
     * @param produto produto a ser removido
     * @return true se produto existir e for removido, caso contrario, false
     */
    public static boolean removerProduto(Produto produto) {
        return produtos.remove(produto);
    }

    /**
     * NAO IMPLEMENTADO AINDA, NAO IRA FAZER NADA
     * @param produtoEditado produto a ser removido
     * @return true se produto existir e for removido, caso contrario, false
     */
    public static Produto editarProduto(Produto produtoEditado) {
        return produtoEditado;
    }

    /**
     * Adicionar produto ao banco de dados
     * @param novoProduto produto a ser adicionado
     * @return retorna id do produto criado
     */
    public static int adicionarProduto(Produto novoProduto) {
        produtos.add(novoProduto);
        return 0;
    }
}
