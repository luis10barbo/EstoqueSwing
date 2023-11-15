package estoqueswing.dao;

import estoqueswing.model.Endereco;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS enderecos(" +
            "idEndereco INTEGER PRIMARY KEY AUTOINCREMENT," +
            "CEP VARCHAR(9)," +
            "pais VARCHAR(32)," +
            "estado VARCHAR(32)," +
            "cidade VARCHAR(32)," +
            "bairro VARCHAR(32)," +
            "logradouro VARCHAR(64)," +
            "complemento VARCHAR(32)" +
            ")";
    public static Endereco adquirirEndereco(int idEndereco) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idEndereco, CEP,pais,estado,cidade,bairro,logradouro,complemento FROM enderecos WHERE idEndereco = ? ");
            stmt.setInt(1,idEndereco);
            ResultSet rs = stmt.executeQuery();
            Endereco endereco = null;
            if (rs.next()){
                endereco = new Endereco();
                endereco.setId(rs.getInt("idEndereco"));
                endereco.setCep(rs.getString("CEP"));
                endereco.setPais(rs.getString("pais"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setComplemento(rs.getString("complemento"));
            }
            return endereco;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static boolean removerEndereco(Endereco endereco) {
        Connection conexao = BancoDados.adquirirConexao();
        try {


            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM enderecos WHERE idEndereco = ?");
            stmt.setInt(1,endereco.getId());
            return stmt.executeUpdate()>0;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static Endereco editarEndereco(Endereco endereco) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("UPDATE enderecos SET CEP = ?, pais = ?,estado = ?,cidade = ?,bairro = ?,logradouro = ?, complemento = ? WHERE idEndereco = ?");
            stmt.setString(1,endereco.getCep());
            stmt.setString(2,endereco.getPais());
            stmt.setString(3,endereco.getEstado());
            stmt.setString(4,endereco.getCidade());
            stmt.setString(5,endereco.getBairro());
            stmt.setString(6,endereco.getLogradouro());
            stmt.setString(7,endereco.getComplemento());
            stmt.setInt(8,endereco.getId());
            stmt.executeUpdate();
            return endereco;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static int criarEndereco(Endereco endereco) {
        Connection conexao = BancoDados.adquirirConexao();
        if (adquirirEndereco(endereco.getId()) != null) {
            editarEndereco(endereco);
            return endereco.getId();
        };
        try {
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO enderecos (CEP,pais,estado,cidade,bairro,logradouro,complemento) VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1,endereco.getCep());
            stmt.setString(2,endereco.getPais());
            stmt.setString(3,endereco.getEstado());
            stmt.setString(4,endereco.getCidade());
            stmt.setString(5,endereco.getBairro());
            stmt.setString(6,endereco.getLogradouro());
            stmt.setString(7, endereco.getComplemento());
            stmt.executeUpdate();

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if(id != null) {
                endereco.setId(id);
                return id;
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}
