package estoqueswing.dao.entidades;

import estoqueswing.dao.Conexao;
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
            "disponibilidade INTEGER," +
            "FOREIGN KEY (idEntidade) REFERENCES entidades(idEntidade) ON DELETE CASCADE" +
            ")";

    public static Transportadora adquirirTransportadora(Entidade entidade) {
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idTransportadora, frete, prazoMedio, disponibilidade FROM transportadoras WHERE idEntidade = ?");
            stmt.setInt(1,entidade.getIdEntidade());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Transportadora transportadora =  new Transportadora(entidade.getNome(), entidade.getCpf(), entidade.getCnpj(), entidade.getEndereco(), entidade.getTelefone());
                transportadora.setIdTransportadora(rs.getInt("idTransportadora"));
                transportadora.setFrete(rs.getDouble("frete"));
                transportadora.setPrazoMedio(rs.getString("prazoMedio"));
                transportadora.setDisponibilidade(rs.getInt("disponibilidade") == 1);
                return transportadora;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Transportadora adquirirTransportadora(int idEntidade) {
        Entidade entidade = EntidadeDAO.adquirirEntidade(idEntidade);
        if (entidade == null) return null;
        return adquirirTransportadora(entidade);
    }

    public static void criarTransportadora(Transportadora novaTransportadora) {
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO transportadoras (idEntidade, frete, prazoMedio, disponibilidade) VALUES (?,?,?,?)");
            stmt.setInt(1, novaTransportadora.getIdEntidade());
            stmt.setDouble(2, novaTransportadora.getFrete());
            stmt.setString(3, novaTransportadora.getPrazoMedio());
            stmt.setBoolean(4, novaTransportadora.getDisponibilidade());
            stmt.executeUpdate();

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null) {
                novaTransportadora.setIdTransportadora(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
