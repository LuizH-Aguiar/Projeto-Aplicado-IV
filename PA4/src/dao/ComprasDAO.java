/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import jdbc.ConnectionFactory;
import model.Compras;
import model.Itens;

/**
 *
 * @author Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
public class ComprasDAO {
    
    private Connection conexao;
    
    //Construtor para abrir a conexão
    public ComprasDAO() throws SQLException {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    //Metodo que cadastra compra
    public void Cadastrar(Compras obj) {
        try {
            String sql = "insert into Compras (CompraValor, CompraData, CompraIDFornecedor, CompraIDUsuario) values (?,?,?,?) ";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setDouble(1, obj.getValor());
            stmt.setString(2, obj.getData());
            stmt.setInt(3, obj.getCod_fornecedor());
            stmt.setInt(4, obj.getCod_Usuario());
            
            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    //Metodo que cadastra itens das compras
    public void CadastrarItens(Itens obj) {
        try {
            String sql = "insert into ItemsCompras (ItemIDProdutoCompra, ItemValorCompra, ItemQuantidadeCompra, ItemIDCompra) values (?,?,?,?) ";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, obj.getCod_Item());
            stmt.setDouble(2, obj.getValor());
            stmt.setInt(3, obj.getQuantidade());
            stmt.setInt(4, obj.getCod_CV());
            
            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    //Metodo que busca o numero da compra
    public int NumeroCompra() {
        try {
            
            String sql = "select idCompra from Compras";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            rs.last();
            int N_Compra = rs.getInt("idCompra") + 0;
            
            stmt.close();
            return N_Compra;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
