package estoqueswing.dao.ordem;

import estoqueswing.dao.BancoDados;
import estoqueswing.dao.entidades.FornecedorDAO;
import estoqueswing.dao.produto.ProdutoEstoqueDAO;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.ordem.OrdemCompra;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdemCompraDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS ordensEntrada(" +
            "idOrdemEntrada INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idOrdem INTEGER," +
            "idFornecedor INTEGER," +
            "FOREIGN KEY (idFornecedor) REFERENCES fornecedores(idFornecedor) ON DELETE CASCADE," +
            "FOREIGN KEY (idOrdem) REFERENCES ordens(idOrdem) ON DELETE CASCADE" +
            ")";

    public static OrdemCompra adquirir (int idOrdem) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idOrdemEntrada, idFornecedor, idOrdem from OrdensEntrada where idOrdem = ?");
            stmt.setInt(1, idOrdem);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                OrdemCompra entrada = new OrdemCompra();
                entrada.setIdOrdemEntrada(rs.getInt("idOrdemEntrada"));
                entrada.setFornecedor(FornecedorDAO.adquirirFornecedor(rs.getInt("idFornecedor")));
                entrada.setIdOrdem(rs.getInt("idOrdem"));
                return entrada;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static void criar(OrdemCompra ordemCompra){
        Connection conexao = BancoDados.adquirirConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO OrdensEntrada(idFornecedor, idOrdem) VALUES (?,?)");
            stmt.setInt(1, ordemCompra.getFornecedor().getIdFornecedor());
            stmt.setInt(2, ordemCompra.getIdOrdem());
            stmt.executeUpdate();

            for (ProdutoOrdem produtoOrdem: ordemCompra.getProdutosOrdem()) {
                ProdutoEstoque produtoEstoqueAtual = ProdutoEstoqueDAO.adquirir(produtoOrdem.getProduto().getId(), ordemCompra.getEstoque().getIdEstoque());
            }

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null) ordemCompra.setIdOrdemEntrada(id);
        }catch (SQLException e) {
            throw new RuntimeException(e);}
    }
}
