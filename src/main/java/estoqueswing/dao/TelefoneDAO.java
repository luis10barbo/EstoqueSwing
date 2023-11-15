package estoqueswing.dao;

import estoqueswing.model.telefone.Telefone;
import estoqueswing.model.telefone.TipoTelefone;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelefoneDAO {
    public static String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS telefones (" +
            "idTelefone INTEGER PRIMARY KEY AUTOINCREMENT," +
            "ddd VARCHAR(4)," +
            "numero VARCHAR(12)," +
            "tipo VARCHAR(16)" +
            ")";

    public static Telefone adquirirTelefone(int idTelefone) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT ddd, numero, tipo FROM telefones WHERE idTelefone = ?");
            stmt.setInt(1, idTelefone);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setIdTelefone(idTelefone);
                telefone.setDdd(rs.getString("ddd"));
                telefone.setNumero(rs.getString("numero"));
                telefone.setTipo(TipoTelefone.parse(rs.getString("tipo")));


                Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
                if (id != null) telefone.setIdTelefone(id);
                return telefone;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static boolean removerTelefone(Telefone telefone) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM telefones WHERE idTelefone = ?");
            stmt.setInt(1, telefone.getIdTelefone());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Telefone editarTelefone(Telefone telefone) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("UPDATE telefones SET ddd = ?, numero = ?, tipo = ? WHERE idTelefone = ?");
            stmt.setString(1, telefone.getDdd());
            stmt.setString(2, telefone.getNumero());
            stmt.setString(3, telefone.getTipo().toString());
            stmt.setInt(4, telefone.getIdTelefone());
            stmt.executeUpdate();

            return telefone;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Telefone criarTelefone(Telefone telefone) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO telefones (ddd, numero, tipo) VALUES (?, ?, ?)");
            stmt.setString(1, telefone.getDdd());
            stmt.setString(2, telefone.getNumero());
            stmt.setString(3, telefone.getTipo().toString());
            stmt.executeUpdate();

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null) telefone.setIdTelefone(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
