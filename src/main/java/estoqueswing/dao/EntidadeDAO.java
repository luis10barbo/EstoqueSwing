package estoqueswing.dao;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;
import estoqueswing.model.entidade.*;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntidadeDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS entidades (" +
            "idEntidade INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idTelefone INTEGER," +
            "idEndereco INTEGER," +
            "nome VARCHAR(64)," +
            "cpf VARCHAR(14)," +
            "cnpj VARCHAR(18)," +
            "tipo VARCHAR(16)," +
            "FOREIGN KEY (idTelefone) REFERENCES telefones(idTelefone)," +
            "FOREIGN KEY (idEndereco) REFERENCES enderecos(idEndereco)" +
            ")";
    /**
     * Adquirir entidade Banco de Dados
     * @param pesquisa texto de pesquisa, utilizar "" para pesquisar nada
     * @return array entidades
     */
    public static Entidade[] adquirirEntidades(String pesquisa) {
        Connection conexao = Conexao.adquirir();
        try{
            if (pesquisa == null) pesquisa = "";
            PreparedStatement stmt = conexao.prepareStatement("Select idEntidade,idTelefone,idEndereco,nome,cpf,cnpj,tipo From entidades");
            ResultSet rs = stmt.executeQuery();

            ArrayList<Entidade>entidades = new ArrayList<>();
            while (rs.next()){
                Entidade entidade = null;
                String  tipo = rs.getString("tipo");
                if (tipo.equals(TipoEntidade.Cliente.toString())){
                    entidade =  new Cliente();
                } else if (tipo.equals(TipoEntidade.Fornecedor.toString())) {
                    entidade = new Fornecedor();
                } else if (tipo.equals(TipoEntidade.Transportadora.toString())) {
                    entidade = new Transportadora();
                }
                entidade.setIdEntidade(rs.getInt("idEntidade"));
                entidade.setNome(rs.getString("nome"));
                entidade.setCnpj(rs.getString("cnpj"));
                entidade.setCpf(rs.getString("cpf"));
                entidade.setEndereco(EnderecoDAO.adquirirEndereco(rs.getInt("idEndereco")));
                entidade.setTelefone(TelefoneDAO.adquirirTelefone(rs.getInt("idTelefone")));
                entidades.add(entidade);
            }
               return entidades.toArray(new Entidade[0]);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }
    /**
     * Remover entidade Banco de Dados
     * @param entidade produto a ser removido
     * @return true se produto existir e for removido, caso contrario, false
     */
    public static boolean removerEntidade(Entidade entidade) {
        return false;
    }
    /**
     * TODO: implementar
     * @param entidadeEditada produto a ser removido
     * @return produto editado
     */
    public static Entidade editarEntidade(Entidade entidadeEditada) {
        return entidadeEditada;
    }
    /**
     * Adicionar entidade
     * @param novaEntidade produto a ser adicionado
     * @return id produto adicionado
     */
    public static int adicionarEntidade(Entidade novaEntidade) {
        Connection conexao = Conexao.adquirir();
        try{
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO entidades (idTelefone,idEndereco,nome,cpf,cnpj,tipo) VALUES (?,?,?,?,?,?)");
            stmt.setString(3, novaEntidade.getNome());
            stmt.setString(4,novaEntidade.getCpf());
            stmt.setString(5, novaEntidade.getCnpj());
            stmt.setString(6,novaEntidade.getTipo().toString());
            if (novaEntidade.getTelefone() != null){
                 stmt.setInt(1,novaEntidade.getTelefone().getIdTelefone());
            } if (novaEntidade.getEndereco() != null) {
                stmt.setInt(2,novaEntidade.getEndereco().getId());
            }
            stmt.executeUpdate();
            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null) {
                novaEntidade.setIdEntidade(id);
                return id;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return 0;
    }
}
