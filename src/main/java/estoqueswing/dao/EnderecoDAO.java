package estoqueswing.dao;

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
}
