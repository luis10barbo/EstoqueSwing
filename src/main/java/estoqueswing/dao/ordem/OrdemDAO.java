package estoqueswing.dao.ordem;

import estoqueswing.dao.Conexao;
import estoqueswing.dao.EstoqueDAO;
import estoqueswing.dao.entidades.TransportadoraDAO;
import estoqueswing.dao.produto.ProdutoEstoqueDAO;
import estoqueswing.dao.produto.ProdutoOrdemDAO;
import estoqueswing.model.ordem.NaturezaOrdem;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.ordem.OrdemEntrada;
import estoqueswing.model.ordem.OrdemSaida;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.utils.UtilsSQLITE;

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
            "FOREIGN KEY (idEstoque) REFERENCES estoques(idEstoque) ON DELETE CASCADE" +
            ")";

    public static Ordem[] adquirirOrdens() {
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idOrdem, idTransportadora, idEstoque, natureza, datetime, frete FROM ordens");
            ResultSet rs = stmt.executeQuery();

            ArrayList<Ordem> ordens = new ArrayList<>();
            while (rs.next()){
                Ordem ordem = new Ordem();
                ordem.setTransportadora(TransportadoraDAO.adquirirTransportadora(rs.getInt("idTransportadora")));
                ordem.setIdOrdem(rs.getInt("idOrdem"));
                ordem.setDataHora(rs.getString("datetime"));
                ordem.setEstoque(EstoqueDAO.adquirir(rs.getInt("idEstoque")));
                ordem.setFrete(rs.getDouble("frete"));
                String natureza = rs.getString("natureza");
                if (natureza.equals(NaturezaOrdem.Entrada.toString())){
                    ordem = OrdemSaidaDAO.adquirir(ordem);
                }else if(natureza.equals(NaturezaOrdem.Saida.toString())){
                    ordem = OrdemEntradaDAO.adquirir(ordem);
                }
                ordens.add(ordem);
            }
            return ordens.toArray(new Ordem[0]);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static boolean removerOrdem(Ordem ordem) {
        Connection conexao = Conexao.adquirir();
        try{
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM ordens WHERE idOrdem = ?");
            stmt.setInt(1,ordem.getIdOrdem());
            return stmt.executeUpdate()>0;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static Ordem editarOrdem(Ordem ordem) {
        Connection conexao = Conexao.adquirir();
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
    public static void criarOrdem(Ordem ordem) {
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO ordens (idTransportadora, idEstoque, natureza, datetime, frete) VALUES (?,?,?,?,?)");
            stmt.setInt(1,ordem.getTransportadora().getIdTransportadora());
            stmt.setInt(2,ordem.getEstoque().getIdEstoque());
            stmt.setString(3, ordem.getNatureza().toString());
            stmt.setString(4,ordem.getDataHora());
            stmt.setDouble(5, ordem.getFrete());


            for (ProdutoOrdem produtoOrdem: ordem.getProdutosOrdem()) {
                // Alterar / adicionar produtos estoque
                ProdutoEstoque produtoEstoque = ProdutoEstoqueDAO.adquirir(produtoOrdem.getProduto().getId(), ordem.getEstoque().getIdEstoque());
                if (produtoEstoque == null) {
                    ProdutoEstoqueDAO.adicionar(new ProdutoEstoque(ordem, produtoOrdem, 0));
                } else {
                    if (ordem instanceof OrdemEntrada) {
                        double novoValorGasto = produtoEstoque.getValorGasto() + (produtoOrdem.getValorProduto() * produtoOrdem.getQuantidade());
                        produtoEstoque.setValorGasto(novoValorGasto);
                        int novaQuantidade = produtoEstoque.getQuantidade() + produtoOrdem.getQuantidade();
                        produtoEstoque.setQuantidade(novaQuantidade);
                    } else if (ordem instanceof OrdemSaida) {
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
                    ProdutoEstoqueDAO.editar(produtoEstoque);
                }

            };

            stmt.executeUpdate();
            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null){
                ordem.setIdOrdem(id);
            }

            for (ProdutoOrdem produtoOrdem: ordem.getProdutosOrdem()) {
                // Vincular produtos ordem com ordem
                ProdutoOrdemDAO.criar(produtoOrdem);
            }

            if (ordem instanceof OrdemEntrada) {
                OrdemEntradaDAO.criar((OrdemEntrada) ordem);
            } else if (ordem instanceof OrdemSaida) {
                OrdemSaidaDAO.criar((OrdemSaida) ordem);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
