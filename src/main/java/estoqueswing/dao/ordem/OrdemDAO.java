package estoqueswing.dao.ordem;

import estoqueswing.dao.Conexao;
import estoqueswing.dao.entidades.ClienteDAO;
import estoqueswing.dao.entidades.FornecedorDAO;
import estoqueswing.dao.entidades.TransportadoraDAO;
import estoqueswing.model.ordem.NaturezaOrdem;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.ordem.OrdemEntrada;
import estoqueswing.model.ordem.OrdemSaida;
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
            "natureza VARCHAR(32)," +
            "datetime VARCHAR(32)," +
            "FOREIGN KEY (idEstoque) REFERENCES estoques(idEstoque)" +
            ")";

    public static Ordem[] adquirirOrdens() {
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idOrdem, idTransportadora, idFornecedor, idDestinatario, idEstoque, natureza, valorProduto, quantidadeProduto,datetime FROM ordens");
            ResultSet rs = stmt.executeQuery();

            ArrayList<Ordem> ordens = new ArrayList<>();
            while (rs.next()){
                Ordem ordem = null;
                String natureza = rs.getString("natureza");
                if (natureza.equals(NaturezaOrdem.Entrada.toString())){
                    ordem = new OrdemSaida();
//                    ordem.setFornecedor(FornecedorDAO.adquirirFornecedor(rs.getInt("idFornecedor")));
                    ordem.setTransportadora(TransportadoraDAO.adquirirTransportadora(rs.getInt("idTransportadora")));
                }else if(natureza.equals(NaturezaOrdem.Saida.toString())){
                    ordem = new OrdemEntrada();
//                    ordem.setDestinatario(ClienteDAO.adquirirCliente(rs.getInt("idDestinatario")));
                }
                assert ordem != null;
                ordem.setIdOrdem(rs.getInt("idOrdem"));
                ordem.setTransportadora(null);
                ordem.setValor(rs.getDouble("valorProduto"));
                ordem.setQuntidadeProduto(rs.getInt("quantidadeProduto"));
                ordem.setDataHora(rs.getString("datetime"));
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
            PreparedStatement stmt = conexao.prepareStatement("UPDATE ordens SET natureza = ?, valorProduto = ?," +
                    "quantidadeProduto = ?,datetime = ? WHERE idOrdem = ?");
//            stmt.setString(1,ordem.getDestinatario());
//            stmt.setString(2,ordem.getRemetente());
            stmt.setString(3,ordem.getNatureza().toString());
            stmt.setDouble(4,ordem.getValor());
            stmt.setInt(5,ordem.getQuntidadeProduto());
            stmt.setString(6,ordem.getDataHora());
            stmt.setInt(7,ordem.getIdOrdem());
            stmt.executeUpdate();
            return ordem;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static int criarOrdem(Ordem ordem) {
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO ordens (idOrdem, idTransportadora, idFornecedor, idDestinatario, idEstoque, natureza, valorProduto, quantidadeProduto,datetime) VALUES (?,?,?,?,?,?,?)");
            stmt.setInt(1,ordem.getIdOrdem());
//            stmt.setString(2, ordem.getDestinatario());
//            stmt.setString(3,ordem.getRemetente());
            stmt.setString(4, ordem.getNatureza().toString());
            stmt.setDouble(5,ordem.getValor());
            stmt.setInt(6,ordem.getQuntidadeProduto());
            stmt.setString(7,ordem.getDataHora());
            stmt.executeUpdate();

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null){
                ordem.setIdOrdem(id);
                return id;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 0;
    }
}
