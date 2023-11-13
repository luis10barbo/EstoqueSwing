package estoqueswing.dao.ordem;

import estoqueswing.dao.Conexao;
import estoqueswing.dao.entidades.FornecedorDAO;
import estoqueswing.dao.produto.ProdutoEstoqueDAO;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.ordem.OrdemEntrada;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdemEntradaDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS ordensEntrada(" +
            "idOrdemEntrada INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idOrdem INTEGER," +
            "idFornecedor INTEGER," +
            "FOREIGN KEY (idFornecedor) REFERENCES fornecedores(idFornecedor)," +
            "FOREIGN KEY (idOrdem) REFERENCES ordens(idOrdem) ON DELETE CASCADE" +
            ")";

    public static OrdemEntrada adquirir (Ordem ordem) {
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idOrdemEntrada, idFornecedor, idOrdem from OrdensEntrada where idOrdem = ?");
            stmt.setInt(1, ordem.getIdOrdem());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                OrdemEntrada entrada = new OrdemEntrada();
                entrada.setIdOrdemEntrada(rs.getInt("idOrdemEntrada"));
                entrada.setFornecedor(FornecedorDAO.adquirirFornecedor(rs.getInt("idFornecedor")));
                entrada.setIdOrdem(rs.getInt("idOrdem"));
                entrada.setNatureza(ordem.getNatureza());
                entrada.setTransportadora(ordem.getTransportadora());
                entrada.setDataHora(ordem.getDataHora());
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
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO OrdensEntrada(idFornecedor, idOrdem) VALUES (?,?)");
            stmt.setInt(1, ordemEntrada.getFornecedor().getIdFornecedor());
            stmt.setInt(2, ordemEntrada.getIdOrdem());
            stmt.executeUpdate();

            for (ProdutoOrdem produtoOrdem: ordemEntrada.getProdutosOrdem()) {
                ProdutoEstoque produtoEstoqueAtual = ProdutoEstoqueDAO.adquirir(produtoOrdem.getProduto().getId(), ordemEntrada.getEstoque().getIdEstoque());
            }

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null) ordemEntrada.setIdOrdemEntrada(id);
        }catch (SQLException e) {
            throw new RuntimeException(e);}
    }
}
