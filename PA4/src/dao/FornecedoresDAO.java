/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionFactory;
import model.Fornecedores;

/**
 *
 * @author Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
public class FornecedoresDAO {
    private Connection conexao;
    
    //Construtor para abrir a conexão
    public FornecedoresDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    //Metodo que cadastra fornecedores
    public void Cadastrar(Fornecedores obj) {
        try {
            String sql = "insert into Fornecedores (FornecedorEmpresa , FornecedorRepresentante , FornecedorCNPJ ) values (?,?,?) ";

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, obj.getEmpresa());
            stmt.setString(2, obj.getRepresentante());
            stmt.setInt(3, obj.getCnpj());
          
            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que altera fornecedores
    public void Alterar(Fornecedores obj) {
        try {
            String sql = "update Fornecedores set FornecedorEmpresa=?, FornecedorRepresentante=?, FornecedorCNPJ=? where idFornecedor =? ";

            //organizar o cmd sql
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, obj.getEmpresa());
            stmt.setString(2, obj.getRepresentante());
            stmt.setInt(3, obj.getCnpj());

            //Pegando o codigo do fornecedor para alterar
            stmt.setInt(4, obj.getCod_fornecedor());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que exclui fornecedores
    public void Excluir(Fornecedores obj) {
        try {
            String sql = "delete from Fornecedores where idFornecedor=? ";

            PreparedStatement stmt = conexao.prepareStatement(sql);

            //Pegando o codigo do cliente para excluir
            stmt.setInt(1, obj.getCod_fornecedor());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo lista todos fornecedores
    public List Listar() {

        try {

            List<Fornecedores> lista = new ArrayList<>();
            String sql = "select * from Fornecedores";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();

                obj.setCod_fornecedor(rs.getInt("idFornecedor"));
                obj.setEmpresa(rs.getString("FornecedorEmpresa"));
                obj.setRepresentante(rs.getString("FornecedorRepresentante"));
                obj.setCnpj(rs.getInt("FornecedorCNPJ"));

                lista.add(obj);
            }
            stmt.close();
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Metodo que busca fornecedores por nome
    public List BuscarFornecedorPorNome(String nome) {

        try {

            List<Fornecedores> lista = new ArrayList<>();
            String sql = "select * from Fornecedores where nome like '%?%'";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();

                obj.setCod_fornecedor(rs.getInt("idFornecedor"));
                obj.setEmpresa(rs.getString("FornecedorEmpresa"));
                obj.setRepresentante(rs.getString("FornecedorRepresentante"));
                obj.setCnpj(rs.getInt("FornecedorCNPJ"));
                
                lista.add(obj);
            }
            
            stmt.close();
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}