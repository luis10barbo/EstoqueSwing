package estoqueswing.dao;

import estoqueswing.model.Endereco;

public class EnderecoDAO {

    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS enderecos(" +
            "idEndereco INTEGER PRIMARY KEY AUTOINCREMENT," +
            "CEP VARCHAR(9)," +
            "pais VARCHAR(32)," +
            "estado VARCHAR(32)," +
            "cidade VARCHAR(32)," +
            "bairro VARCHAR(32)," +
            "logradouro VARCHAR(64)," +
            "complemento VARCHAR(32)" +
            ")";

    public static Endereco adquirirEndereco(int idEndereco) {
        return null;
    }

    public static boolean removerEndereco(Endereco endereco) {
        return false;
    }

    public static Endereco editarEndereco(Endereco endereco) {
        return null;
    }

    public static Endereco criarEndereco(Endereco endereco) {
        return null;
    }
}
