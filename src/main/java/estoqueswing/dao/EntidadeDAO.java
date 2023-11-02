package estoqueswing.dao;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;
import estoqueswing.model.entidade.Cliente;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.entidade.Transportadora;

import java.util.Arrays;
import java.util.List;

public class EntidadeDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS entidades (" +
            "idEntidade INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idTelefone INTEGER," +
            "idEndereco INTEGER," +
            "nome VARCHAR(64)," +
            "cpf VARCHAR(14)," +
            "cnpj VARCHAR(18)," +
            "tipo VARCHAR(16)," +
            "FOREIGN KEY (idTelefone) REFERENCES telefones(idTelefone)," +
            "FOREIGN KEY (idEndereco) REFERENCES enderecos(idEndereco)" +
            ")";
    /**
     * Adquirir entidade Banco de Dados
     * @param pesquisa texto de pesquisa, utilizar "" para pesquisar nada
     * @return array entidades
     */
    public static Entidade[] adquirirEntidades(String pesquisa) {
        return null;
    }
    /**
     * Remover entidade Banco de Dados
     * @param entidade produto a ser removido
     * @return true se produto existir e for removido, caso contrario, false
     */
    public static boolean removerEntidade(Entidade entidade) {
        return false;
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
        return 0;
    }
}
