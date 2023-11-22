package estoqueswing.dao.entidades;

import estoqueswing.dao.BancoDados;
import estoqueswing.model.entidade.DisponibilidadeEntidade;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.model.entidade.Transportadora;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransportadoraDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS transportadoras (" +
            "idTransportadora INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idEntidade INTEGER," +
            "frete REAL," +
            "prazoMedio varchar(32)," +
            "disponibilidade varchar(16)," +
            "FOREIGN KEY (idEntidade) REFERENCES entidades(idEntidade) ON DELETE CASCADE" +
            ")";

    public static Transportadora adquirirTransportadora(Entidade entidade) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idTransportadora, frete, prazoMedio, disponibilidade FROM transportadoras WHERE idEntidade = ?");
            stmt.setInt(1,entidade.getIdEntidade());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Transportadora transportadora =  new Transportadora(entidade.getNome(), entidade.getCpf(), entidade.getCnpj(), entidade.getEndereco(), entidade.getTelefone());
                transportadora.setIdEntidade(entidade.getIdEntidade());
                transportadora.setIdTransportadora(rs.getInt("idTransportadora"));
                transportadora.setFrete(rs.getDouble("frete"));
                transportadora.setPrazoMedio(rs.getString("prazoMedio"));
                transportadora.setDisponibilidade(DisponibilidadeEntidade.parse(rs.getString("disponibilidade")));
                return transportadora;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Transportadora adquirirTransportadora(int idTransportadora) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idEntidade FROM transportadoras WHERE idTransportadora = ?");
            stmt.setInt(1, idTransportadora);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idEntidade = rs.getInt("idEntidade");
                return (Transportadora) EntidadeDAO.adquirirEntidade(idEntidade);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void criarTransportadora(Transportadora novaTransportadora) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO transportadoras (idEntidade, frete, prazoMedio, disponibilidade) VALUES (?,?,?,?)");
            stmt.setInt(1, novaTransportadora.getIdEntidade());
            stmt.setDouble(2, novaTransportadora.getFrete());
            stmt.setString(3, novaTransportadora.getPrazoMedio());
            stmt.setString(4, novaTransportadora.getDisponibilidade().toString());
            stmt.executeUpdate();

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null) {
                novaTransportadora.setIdTransportadora(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void editar(Transportadora transportadoraEditada) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("UPDATE transportadoras SET frete = ?, prazoMedio = ?, disponibilidade = ? WHERE idTransportadora = ?");
            stmt.setDouble(1, transportadoraEditada.getFrete());
            stmt.setString(2, transportadoraEditada.getPrazoMedio());
            stmt.setString(3, transportadoraEditada.getDisponibilidade().toString());
            stmt.setInt(4, transportadoraEditada.getIdTransportadora());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
