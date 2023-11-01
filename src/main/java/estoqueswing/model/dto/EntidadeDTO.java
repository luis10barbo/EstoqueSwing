package estoqueswing.model.dto;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;
import estoqueswing.model.entidade.Cliente;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.entidade.Transportadora;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntidadeDTO {
    public static Endereco endereco = new Endereco("Brasil", "Recife", "APTO 123", "Teste", "Rua legal 123", "12312312", "Pernambuco");
    public static List<Entidade> entidades = Arrays.asList(new Cliente("Luis", "123.123.123-12", endereco, new Telefone()),
            new Fornecedor("Jorge", "123.123.124-13", endereco, new Telefone()),
            new Transportadora("algo Logistica", "321.321.321-12", endereco, new Telefone()));
    public static Entidade[] getEntidades() {
        return entidades.toArray(new Entidade[0]);
    }
}
