/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import jdbc.ConnectionFactory;
import model.Produtos;

/**
 *
 * @author Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
public class ProdutosDAO {
    private Connection conexao;
    
    //Construtor para abrir a conexão
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
    
    //Metodo que lista todos os usuarios
    public DefaultTableModel Listar() {
        try {
            String sql = "select * from Produtos";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Código");
            model.addColumn("Nome");
            model.addColumn("Custo");
            model.addColumn("Venda");
            model.addColumn("Quantidade");
            model.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idProduto") + "",
                    rs.getString("ProdutoNome") + "",
                    rs.getDouble("ProdutoValorCompra") + "",
                    rs.getDouble("ProdutoValorVenda") + "",
                    rs.getInt("ProdutoQuantidade") + ""
                });
            }
            
            stmt.close();
            return model;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo que busca usuarios
    public DefaultTableModel Buscar(String busca) {
        try {
            String sql = "select * from Produtos where ProdutoNome like ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, ("%"+busca+"%"));
            ResultSet rs = stmt.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Código");
            model.addColumn("Nome");
            model.addColumn("Custo");
            model.addColumn("Venda");
            model.addColumn("Quantidade");
            model.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idProduto") + "",
                    rs.getString("ProdutoNome") + "",
                    rs.getDouble("ProdutoValorCompra") + "",
                    rs.getDouble("ProdutoValorVenda") + "",
                    rs.getInt("ProdutoQuantidade") + ""
                });
            }
            
            stmt.close();
            return model;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
