/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
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
            String sql = "insert into Produtos (ProdutoNome, ProdutoValorCompra, ProdutoValorVenda, ProdutoCodBarra, ProdutoDescricao, ProdutoQuantidade) values (?,?,?,?,?,?) ";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setDouble(2, obj.getValorCompra());
            stmt.setDouble(3, obj.getValorVenda());
            stmt.setString(4, obj.getCodBarra());
            stmt.setString(5, obj.getDescricao());
            stmt.setInt(6, obj.getQuantidade());
          
            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que altera produtos
    public void Alterar(Produtos obj) {
        try {
            String sql = "update Produtos set ProdutoNome =?, ProdutoValorCompra =?, ProdutoValorVenda =?, ProdutoCodBarra =?, ProdutoDescricao =?, ProdutoQuantidade =? where idProduto =? ";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setDouble(2, obj.getValorCompra());
            stmt.setDouble(3, obj.getValorVenda());
            stmt.setString(4, obj.getCodBarra());
            stmt.setString(5, obj.getDescricao());
            stmt.setInt(6, obj.getQuantidade());

            //Pegando o codigo do produto para alterar
            stmt.setDouble(7, obj.getCod_produto());

            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que exclui produtos
    public void Excluir(Produtos obj) {
        try {
            String sql = "select ItemIDProdutoCompra from ItemsCompras where ItemIDProdutoCompra = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, obj.getCod_produto());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Item não pode ser excluído, pois é utilizado em registros");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!");
            }
            
            sql = "delete from Produtos where idProduto =? ";
            stmt = conexao.prepareStatement(sql);

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
            model.addColumn("Cod. Barra");
            model.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idProduto") + "",
                    rs.getString("ProdutoNome") + "",
                    rs.getString("ProdutoCodBarra") + ""
                });
            }
            
            stmt.close();
            return model;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo que busca produtos
    public DefaultTableModel Buscar(String busca, int mode) {
        try {
            String sql = "";
            PreparedStatement stmt = null;
            
            if (mode == 0) {        //Busca por nome
                sql = "select * from Produtos where ProdutoNome like ?";
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, ("%"+busca+"%"));
            } else if (mode == 1) { //Busca por codigo de barras
                sql = "select * from Produtos where ProdutoCodBarra like ?";
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, (""+busca+""));
            }
            
            ResultSet rs = stmt.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Código");
            model.addColumn("Nome");
            model.addColumn("Custo");
            model.addColumn("Venda");
            model.addColumn("Cod. Barra");
            model.addColumn("Descrição");
            model.addColumn("Quantidade");
            model.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idProduto") + "",
                    rs.getString("ProdutoNome") + "",
                    rs.getDouble("ProdutoValorCompra") + "",
                    rs.getDouble("ProdutoValorVenda") + "",
                    rs.getString("ProdutoCodBarra") + "",
                    rs.getString("ProdutoDescricao") + "",
                    rs.getInt("ProdutoQuantidade") + ""
                });
            }
            
            stmt.close();
            return model;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //Metodo que altera o estoque de produtos
    public void AlterarEstoque(Produtos obj) {
        try {
            //Pega o estoque atual
            String sql = "select ProdutoQuantidade from Produtos where idProduto =?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, obj.getCod_produto());
            
            ResultSet rs = stmt.executeQuery();
            
            rs.first();
            int estoque = rs.getInt("ProdutoQuantidade") + 0;
            
            //Muda o estoque
            sql = "update Produtos set ProdutoQuantidade =? where idProduto =? ";
            stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, obj.getQuantidade()+estoque);
            
            //Pegando o codigo do produto para alterar
            stmt.setDouble(2, obj.getCod_produto());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
