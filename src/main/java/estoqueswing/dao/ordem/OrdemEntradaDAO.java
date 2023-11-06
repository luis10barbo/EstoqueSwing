package estoqueswing.dao.ordem;

import estoqueswing.dao.Conexao;
import estoqueswing.dao.entidades.FornecedorDAO;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.ordem.OrdemEntrada;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdemEntradaDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS ordemEntrada(" +
            "idOrdemEntrada INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idOrdem INTEGER," +
            "idFornecedor INTEGER," +
            "FOREIGN KEY (idFornecedor) REFERENCES fornecedores(idFornecedor)," +
            "FOREIGN KEY (idOrdem) REFERENCES ordens(idOrdem) ON DELETE CASCADE" +
            ")";
    public static OrdemEntrada adquirir (int idOrdemEntrada) {
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idFornecedor, idOrdem from OrdemEntrada where idOrdemEntrada = ?");
            stmt.setInt(1, idOrdemEntrada);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                OrdemEntrada entrada = new OrdemEntrada();
                entrada.setFornecedor(FornecedorDAO.adquirirFornecedor(rs.getInt("idFornecedor")));
                entrada.setIdOrdem(rs.getInt("idOrdem"));
                return entrada;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static void criar(OrdemEntrada ordemEntrada){
        Connection conexao = Conexao.adquirir();
        try{
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO OrdemEntrada(idFornecedor, idOrdem) VALUES (?,?)");
            stmt.setInt(1, ordemEntrada.getFornecedor().getIdFornecedor());
            stmt.setInt(2, ordemEntrada.getIdOrdem());
            stmt.executeUpdate();
            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null) ordemEntrada.setIdOrdemEntrada(id);
        }catch (SQLException e) {
            throw new RuntimeException(e);}
    }
}
