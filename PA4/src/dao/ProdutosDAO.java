/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jdbc.ConnectionFactory;
import model.Produtos;

/**
 *
 * @author Fabiana Nunes
 */
public class ProdutosDAO {
    private Connection conexao;

    public ProdutosDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    //Metodo que cadastra produtos
    public void Cadastrar(Produtos obj) {
        try {
            String sql = "insert into Produtos (ProdutoNome, ProdutoValorCompra, ProdutoValorVenda, ProdutoDescricao, ProdutoQuantidade) values (?,?,?,?,?) ";
            
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setDouble(2, obj.getValorCompra());
            stmt.setDouble(3, obj.getValorVenda());
            stmt.setString(4, obj.getDescricao());
            stmt.setInt(5, obj.getQuantidade());
          
            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que altera produtos
    public void Alterar(Produtos obj) {
        try {
            String sql = "update Produtos set ProdutoNome =?, ProdutoValorCompra =?, ProdutoValorVenda =?, ProdutoDescricao =?, ProdutoQuantidade =? where idProduto =? ";

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setDouble(2, obj.getValorCompra());
            stmt.setDouble(3, obj.getValorVenda());
            stmt.setString(4, obj.getDescricao());
            stmt.setInt(5, obj.getQuantidade());

            //Pegando o codigo do produto para alterar
            stmt.setDouble(6, obj.getCod_produto());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que exclui produtos
    public void Excluir(Produtos obj) {
        try {
            String sql = "delete from Produtos where idProduto =? ";

            PreparedStatement stmt = conexao.prepareStatement(sql);

            //Pegando o codigo do produto para excluir
            stmt.setInt(1, obj.getCod_produto());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
