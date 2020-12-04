/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.ConnectionFactory;
import model.Itens;
import model.Vendas;

/**
 *
 * @author Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
public class VendasDAO {
    
    private Connection conexao;
    
    //Construtor para abrir a conexão
    public VendasDAO() throws SQLException {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    //Metodo que cadastra venda
    public void Cadastrar(Vendas obj) {
        try {
            String sql = "insert into Vendas (VendaValor, VendaData, VendaIDCliente, VendaIDUsuario) values (?,?,?,?) ";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setDouble(1, obj.getValor());
            stmt.setString(2, obj.getData());
            stmt.setInt(3, obj.getCod_cliente());
            stmt.setInt(4, obj.getCod_usuario());
            
            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    //Metodo que cadastra itens das vendas
    public void CadastrarItens(Itens obj) {
        try {
            String sql = "insert into ItemsVendas (ItemIDProdutoVenda, ItemValorVenda, ItemQuantidadeVenda, ItemIDVenda) values (?,?,?,?) ";
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
    
    //Metodo que busca o numero da venda
    public int NumeroVenda() {
        try {            
            String sql = "select idVenda from Vendas";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            rs.last();
            int N_Compra = rs.getInt("idVenda") + 0;
            
            stmt.close();
            return N_Compra;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
}
