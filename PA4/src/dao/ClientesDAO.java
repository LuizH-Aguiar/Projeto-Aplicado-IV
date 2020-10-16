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
import model.Clientes;

/**
 *
 * @author Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
public class ClientesDAO {
    private Connection conexao;
    
    //Construtor para abrir a conexão
    public ClientesDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    //Metodo que cadastra clientes
    public void Cadastrar(Clientes obj) {
        try {
            String sql = "insert into Clientes (ClienteNome, ClienteCPF, ClienteUF, ClienteCidade, ClienteEndereço, ClienteCredito, ClienteConta) values (?,?,?,?,?,?,?) ";
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, obj.getNome());
            stmt.setInt(2, obj.getCpf());
            stmt.setString(3, obj.getUf());
            stmt.setString(4, obj.getCidade());
            stmt.setString(5, obj.getEndereco());
            stmt.setDouble(6, obj.getCredito());
            stmt.setDouble(7, obj.getConta());
          
            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que altera clientes
    public void Alterar(Clientes obj) {
        try {
            String sql = "update Clientes set ClienteNome =?, ClienteCPF =?, ClienteUF =?, ClienteCidade =?, ClienteEndereço =?, ClienteCredito =?, ClienteConta =? where idCliente =? ";

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setInt(2, obj.getCpf());
            stmt.setString(3, obj.getUf());
            stmt.setString(4, obj.getCidade());
            stmt.setString(5, obj.getEndereco());
            stmt.setDouble(6, obj.getCredito());
            stmt.setDouble(7, obj.getConta());

            //Pegando o codigo do cliente para alterar
            stmt.setDouble(8, obj.getCod_cliente());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que exclui clientes
    public void Excluir(Clientes obj) {
        try {
            String sql = "delete from Clientes where idCliente =? ";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            //Pegando o codigo do cliente para excluir
            stmt.setInt(1, obj.getCod_cliente());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    //Metodo que lista todos os clientes
    public DefaultTableModel Listar() {
        try {
            String sql = "select * from Clientes";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Código");
            model.addColumn("Nome");
            model.addColumn("CPF");
            model.addColumn("UF");
            model.addColumn("Cidade");
            model.addColumn("Endereço");
            model.addColumn("Credito");
            model.addColumn("Saldo");
            model.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idCliente") + "",
                    rs.getString("ClienteNome") + "",
                    rs.getInt("ClienteCPF") + "",
                    rs.getString("ClienteUF") + "",
                    rs.getString("ClienteCidade") + "",
                    rs.getString("ClienteEndereço") + "",
                    rs.getDouble("ClienteCredito") + "",
                    rs.getDouble("ClienteConta") + ""
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
            String sql = "select * from Clientes where ClienteNome like ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, ("%"+busca+"%"));
            ResultSet rs = stmt.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Código");
            model.addColumn("Nome");
            model.addColumn("CPF");
            model.addColumn("UF");
            model.addColumn("Cidade");
            model.addColumn("Endereço");
            model.addColumn("Credito");
            model.addColumn("Saldo");
            model.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idCliente") + "",
                    rs.getString("ClienteNome") + "",
                    rs.getInt("ClienteCPF") + "",
                    rs.getString("ClienteUF") + "",
                    rs.getString("ClienteCidade") + "",
                    rs.getString("ClienteEndereço") + "",
                    rs.getDouble("ClienteCredito") + "",
                    rs.getDouble("ClienteConta") + ""
                });
            }
            
            stmt.close();
            return model;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
