package estoqueswing.dao.entidades;

import estoqueswing.dao.BancoDados;
import estoqueswing.model.entidade.Cliente;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS clientes(" +
            "idCliente INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idEntidade INTEGER," +
            "FOREIGN KEY (idEntidade) REFERENCES entidades(idEntidade) ON DELETE CASCADE" +
            ")";

    public static Cliente adquirirCliente(Entidade entidade) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idCliente FROM clientes WHERE idEntidade = ?");
            stmt.setInt(1, entidade.getIdEntidade());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente(entidade.getNome(), entidade.getCpf(), entidade.getEndereco(), entidade.getTelefone());
                cliente.setIdEntidade(entidade.getIdEntidade());
                cliente.setIdCliente(rs.getInt("idCliente"));
                return cliente;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Cliente adquirirCliente(int idEntidade) {
        Entidade entidade = EntidadeDAO.adquirirEntidade(idEntidade);
        if (entidade == null) return null;

        return adquirirCliente(entidade);
    }

    public static void criarCliente(Cliente novoCliente) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO clientes (idEntidade) VALUES (?)");
            stmt.setInt(1, novoCliente.getIdEntidade());
            stmt.executeUpdate();

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null) novoCliente.setIdCliente(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void editar(Cliente entidadeEditada) {
    }

    /**
     * UTILIZAR EntidadeDAO.removerEntidade(cliente)
     * @param idCliente
     * @return
     */
    public boolean removerCliente(int idCliente) {
        Connection conexao = BancoDados.adquirirConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM clientes WHERE idCliente = ?");
            stmt.setInt(1, idCliente);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
