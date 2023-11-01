package estoqueswing.model.dto;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;
import estoqueswing.model.entidade.Cliente;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.entidade.Transportadora;

import java.util.Arrays;
import java.util.List;

public class EntidadeDTO {
    public static Endereco endereco = new Endereco("Brasil", "Recife", "APTO 123", "Teste", "Rua legal 123", "12312312", "Pernambuco");
    public static List<Entidade> entidades = Arrays.asList(
            new Cliente("Luis", "123.123.123-12", endereco, new Telefone()),
            new Fornecedor("Jorge", "123.123.124-13", endereco, new Telefone()),
            new Transportadora("algo Logistica", "321.321.321-12", endereco, new Telefone()),
            new Transportadora("algo Logistica", "321.321.321-12", endereco, new Telefone()),
            new Transportadora("algo Logistica", "321.321.321-12", endereco, new Telefone()),
            new Transportadora("algo Logistica", "321.321.321-12", endereco, new Telefone()),
            new Transportadora("algo Logistica", "321.321.321-12", endereco, new Telefone()),
            new Transportadora("algo Logistica", "321.321.321-12", endereco, new Telefone())
    );
    /**
     * Adquirir entidade Banco de Dados
     * @param pesquisa texto de pesquisa, utilizar "" para pesquisar nada
     * @return array entidades
     */
    public static Entidade[] adquirirEntidades(String pesquisa) {
        return entidades.toArray(new Entidade[0]);
    }
    /**
     * Remover entidade Banco de Dados
     * @param entidade produto a ser removido
     * @return true se produto existir e for removido, caso contrario, false
     */
    public static boolean removerEntidade(Entidade entidade) {
        return entidades.remove(entidade);
    }
    /**
     * TODO: implementar
     * @param entidadeEditada produto a ser removido
     * @return produto editado
     */
    public static Entidade editarEntidade(Entidade entidadeEditada) {
        return entidadeEditada;
    }
    /**
     * Adicionar entidade
     * @param novaEntidade produto a ser adicionado
     * @return id produto adicionado
     */
    public static int adicionarEntidade(Entidade novaEntidade) {
        entidades.add(novaEntidade);
        return 0;
    }
}
