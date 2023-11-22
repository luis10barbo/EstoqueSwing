package estoqueswing.dao.ordem;

import estoqueswing.dao.BancoDados;
import estoqueswing.dao.EstoqueDAO;
import estoqueswing.dao.entidades.TransportadoraDAO;
import estoqueswing.dao.produto.ProdutoEstoqueDAO;
import estoqueswing.dao.produto.ProdutoOrdemDAO;
import estoqueswing.exceptions.ordem.ExcecaoEditarOrdemFinalizada;
import estoqueswing.model.ordem.NaturezaOrdem;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.ordem.OrdemCompra;
import estoqueswing.model.ordem.OrdemVenda;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.utils.UtilsSQLITE;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdemDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS ordens (" +
            "idOrdem INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idTransportadora INTEGER," +
            "idEstoque INTEGER," +
            "frete REAL," +
            "natureza VARCHAR(32)," +
            "datetime VARCHAR(32)," +
            "finalizada INTEGER DEFAULT 0," +
            "FOREIGN KEY (idEstoque) REFERENCES estoques(idEstoque) ON DELETE CASCADE" +
            ")";

    public static Ordem adquirirOrdem(int idOrdem) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idOrdem, idTransportadora, idEstoque, natureza, datetime, frete, finalizada FROM ordens WHERE idOrdem = ?");
            stmt.setInt(1, idOrdem);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return parseOrdem(rs);
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Nullable
    private static Ordem parseOrdem(ResultSet rs) throws SQLException {
        String natureza = rs.getString("natureza");
        int idOrdem = rs.getInt("idOrdem");
        Ordem ordem = null;
        if (natureza.equals(NaturezaOrdem.Compra.toString())){
            ordem = OrdemCompraDAO.adquirir(idOrdem);
        } else if(natureza.equals(NaturezaOrdem.Venda.toString())){
            ordem = OrdemVendaDAO.adquirir(idOrdem);
        }

        assert ordem != null;

        ordem.setIdOrdem(idOrdem);
        ordem.setNatureza(NaturezaOrdem.parse(natureza));
        ordem.setTransportadora(TransportadoraDAO.adquirirTransportadora(rs.getInt("idTransportadora")));
        ordem.setDataHora(rs.getString("datetime"));
        ordem.setEstoque(EstoqueDAO.adquirir(rs.getInt("idEstoque")));
        ordem.setFrete(rs.getDouble("frete"));
        ordem.setFinalizada(rs.getInt("finalizada") == 1);
        ordem.setProdutosOrdem(ProdutoOrdemDAO.adquirir(idOrdem));
        return ordem;
    }

    public static Ordem[] adquirirOrdens(boolean mostrarFinalizadas) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt;
            if (mostrarFinalizadas) {
                stmt = conexao.prepareStatement("SELECT idOrdem, idTransportadora, idEstoque, natureza, datetime, frete, finalizada FROM ordens ORDER BY idOrdem DESC");
            } else {
                stmt = conexao.prepareStatement("SELECT idOrdem, idTransportadora, idEstoque, natureza, datetime, frete, finalizada FROM ordens WHERE finalizada = ? ORDER BY idOrdem DESC");
                stmt.setBoolean(1, mostrarFinalizadas);
            }

            ResultSet rs = stmt.executeQuery();

            ArrayList<Ordem> ordens = new ArrayList<>();
            while (rs.next()){
                ordens.add(parseOrdem(rs));
            }
            return ordens.toArray(new Ordem[0]);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static Ordem[] adquirirOrdens(NaturezaOrdem naturezaOrdem, boolean mostrarFinalizadas) {
        if (naturezaOrdem == NaturezaOrdem.Nenhum) return adquirirOrdens(mostrarFinalizadas);

        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt;
            if (mostrarFinalizadas) {
                stmt = conexao.prepareStatement("SELECT idOrdem, idTransportadora, idEstoque, natureza, datetime, frete, finalizada FROM ordens WHERE natureza = ? ORDER BY idOrdem DESC");
            } else {
                stmt = conexao.prepareStatement("SELECT idOrdem, idTransportadora, idEstoque, natureza, datetime, frete, finalizada FROM ordens WHERE natureza = ?, finalizada = ? ORDER BY idOrdem DESC");
                stmt.setBoolean(2, mostrarFinalizadas);
            }
            stmt.setString(1, naturezaOrdem.toString());
            ResultSet rs = stmt.executeQuery();

            ArrayList<Ordem> ordens = new ArrayList<>();
            while (rs.next()){
                ordens.add(parseOrdem(rs));
            }
            return ordens.toArray(new Ordem[0]);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static boolean removerOrdem(Ordem ordem) {
        Connection conexao = BancoDados.adquirirConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM ordens WHERE idOrdem = ?");
            stmt.setInt(1,ordem.getIdOrdem());
            return stmt.executeUpdate()>0;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static Ordem editarOrdem(Ordem ordem) {
        if (ordem.isFinalizada()) throw new ExcecaoEditarOrdemFinalizada();

        Connection conexao = BancoDados.adquirirConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("UPDATE ordens SET natureza = ?," +
                    "datetime = ?, frete = ? WHERE idOrdem = ?");
//            stmt.setString(1,ordem.getDestinatario());
//            stmt.setString(2,ordem.getRemetente());
            stmt.setString(1,ordem.getNatureza().toString());
            stmt.setString(2,ordem.getDataHora());
            stmt.setDouble(3, ordem.getFrete());
            stmt.setInt(4,ordem.getIdOrdem());
            stmt.executeUpdate();
            return ordem;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void finalizarOrdem(Ordem ordem) {
        if (ordem.isFinalizada()) throw new ExcecaoEditarOrdemFinalizada();

        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("UPDATE ordens SET finalizada = ? WHERE idOrdem = ?");
            stmt.setInt(1, 1);
            stmt.setInt(2, ordem.getIdOrdem());
            stmt.executeUpdate();
        } catch (SQLException ignored) {

        }

        for (ProdutoOrdem produtoOrdem: ordem.getProdutosOrdem()) {
            // Alterar / adicionar produtos estoque
            ProdutoEstoque produtoEstoque = ProdutoEstoqueDAO.adquirir(produtoOrdem.getProduto().getId(), ordem.getEstoque().getIdEstoque());
            if (produtoEstoque == null) {
                ProdutoEstoqueDAO.adicionar(new ProdutoEstoque(ordem, produtoOrdem, 0));
            } else {
                if (ordem instanceof OrdemCompra) {
                    double novoValorGasto = produtoEstoque.getValorGasto() + (produtoOrdem.getValorProduto() * produtoOrdem.getQuantidade());
                    produtoEstoque.setValorGasto(novoValorGasto);
                    int novaQuantidade = produtoEstoque.getQuantidade() + produtoOrdem.getQuantidade();
                    produtoEstoque.setQuantidade(novaQuantidade);
                } else if (ordem instanceof OrdemVenda) {
                    double novoValorGanho = produtoEstoque.getValorGanho() + (produtoOrdem.getValorProduto() * produtoOrdem.getQuantidade());
                    produtoEstoque.setValorGanho(novoValorGanho);
                    int novaQuantidade = produtoEstoque.getQuantidade() - produtoOrdem.getQuantidade();
                    produtoEstoque.setQuantidade(novaQuantidade);
                };
                ProdutoEstoqueDAO.editar(produtoEstoque);
            }

            // adicionar frete
            produtoEstoque = ProdutoEstoqueDAO.adquirir(produtoOrdem.getProduto().getId(), ordem.getEstoque().getIdEstoque());
            if (produtoEstoque != null) {
                produtoEstoque.setValorGasto(produtoEstoque.getValorGasto() + ordem.getFrete());
                if (ordem instanceof OrdemVenda && ((OrdemVenda) ordem).isClientePagouFrete()){
                    produtoEstoque.setValorGanho(produtoEstoque.getValorGanho()+ ordem.getFrete());
                }
                ProdutoEstoqueDAO.editar(produtoEstoque);
            }

        };
    }

    public static void criarOrdem(Ordem ordem) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO ordens (idTransportadora, idEstoque, natureza, datetime, frete) VALUES (?,?,?,?,?)");
            stmt.setInt(1,ordem.getTransportadora().getIdTransportadora());
            stmt.setInt(2,ordem.getEstoque().getIdEstoque());
            stmt.setString(3, ordem.getNatureza().toString());
            stmt.setString(4,ordem.getDataHora());
            stmt.setDouble(5, ordem.getFrete());

            stmt.executeUpdate();
            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null){
                ordem.setIdOrdem(id);
            }

            for (ProdutoOrdem produtoOrdem: ordem.getProdutosOrdem()) {
                // Vincular produtos ordem com ordem
                ProdutoOrdemDAO.criar(produtoOrdem);
            }

            if (ordem instanceof OrdemCompra) {
                OrdemCompraDAO.criar((OrdemCompra) ordem);
            } else if (ordem instanceof OrdemVenda) {
                OrdemVendaDAO.criar((OrdemVenda) ordem);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
