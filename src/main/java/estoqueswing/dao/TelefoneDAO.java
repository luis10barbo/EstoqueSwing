package estoqueswing.dao;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;

public class TelefoneDAO {
    public static String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS telefones (" +
            "idTelefone INTEGER PRIMARY KEY AUTOINCREMENT," +
            "ddd VARCHAR(4)," +
            "numero VARCHAR(12)," +
            "tipo VARCHAR(16)" +
            ")";

    public static Telefone adquirirTelefone(int idTelefone) {
        return null;
    }

    public static boolean removerTelefone(Telefone telefone) {
        return false;
    }

    public static Telefone editarTelefone(Telefone telefone) {
        return null;
    }

    public static Telefone criarTelefone(Telefone telefone) {
        return null;
    }
}
