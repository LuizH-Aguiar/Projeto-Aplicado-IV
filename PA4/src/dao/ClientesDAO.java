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
            stmt.setString(2, obj.getCpf());
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
            stmt.setString(2, obj.getCpf());
            stmt.setString(3, obj.getUf());
            stmt.setString(4, obj.getCidade());
            stmt.setString(5, obj.getEndereco());
            stmt.setDouble(6, obj.getCredito());
            stmt.setDouble(7, obj.getConta());

            //Pegando o codigo do cliente para alterar
            stmt.setDouble(8, obj.getCod_cliente());

            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
            
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que exclui clientes
    public void Excluir(Clientes obj) {
        try {
            String sql = "select VendaIDCliente from Vendas where VendaIDCliente = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, obj.getCod_cliente());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Item não pode ser excluído, pois é utilizado em registros");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!");
            }
            
            sql = "delete from Clientes where idCliente =? ";
            stmt = conexao.prepareStatement(sql);
            
            //Pegando o codigo do cliente para excluir
            stmt.setInt(1, obj.getCod_cliente());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    //Metodo que lista todos os clientes
    public DefaultTableModel Listar(boolean pag) {
        try {
            String sql = "select * from Clientes";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            
            model.addColumn("Código");
            model.addColumn("Nome");
            model.addColumn("CPF");
            if (!pag) model.addColumn("UF");
            if (!pag) model.addColumn("Cidade");
            if (!pag) model.addColumn("Endereço");
            model.addColumn("Credito");
            model.addColumn("Saldo");
            model.setNumRows(0);
            
            if (!pag) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("idCliente") + "",
                        rs.getString("ClienteNome") + "",
                        rs.getString("ClienteCPF") + "",
                        rs.getString("ClienteUF") + "",
                        rs.getString("ClienteCidade") + "",
                        rs.getString("ClienteEndereço") + "",
                        rs.getDouble("ClienteCredito") + "",
                        rs.getDouble("ClienteConta") + ""
                    });
                }
            } else {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("idCliente") + "",
                        rs.getString("ClienteNome") + "",
                        rs.getString("ClienteCPF") + "",
                        rs.getDouble("ClienteCredito") + "",
                        rs.getDouble("ClienteConta") + ""
                    });
                }
            }
            
            stmt.close();
            return model;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo que busca usuarios
    public DefaultTableModel Buscar(String busca, int mode, boolean pag) {
        try {
            String sql = "";
            PreparedStatement stmt = null;
            
            if (mode == 0) {        //Busca por codigo, nome ou cpf
                sql = "select * from Clientes where ClienteNome like ? or ClienteCPF like ?";
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, ("%"+busca+"%"));
                stmt.setString(2, ("%"+busca+"%"));
            } else if (mode == 1) { //Busca por cpf
                sql = "select * from Clientes where ClienteCPF like ?";
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, (""+busca+""));
            }
            
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            
            model.addColumn("Código");
            model.addColumn("Nome");
            model.addColumn("CPF");
            if (!pag) model.addColumn("UF");
            if (!pag) model.addColumn("Cidade");
            if (!pag) model.addColumn("Endereço");
            model.addColumn("Credito");
            model.addColumn("Saldo");
            model.setNumRows(0);
            
            if (!pag) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("idCliente") + "",
                        rs.getString("ClienteNome") + "",
                        rs.getString("ClienteCPF") + "",
                        rs.getString("ClienteUF") + "",
                        rs.getString("ClienteCidade") + "",
                        rs.getString("ClienteEndereço") + "",
                        rs.getDouble("ClienteCredito") + "",
                        rs.getDouble("ClienteConta") + ""
                    });
                }
            } else {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("idCliente") + "",
                        rs.getString("ClienteNome") + "",
                        rs.getString("ClienteCPF") + "",
                        rs.getDouble("ClienteCredito") + "",
                        rs.getDouble("ClienteConta") + ""
                    });
                }
            }
            
            stmt.close();
            return model;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //Metodo que altera o saldo de clientes
    public void AlterarSaldo(Clientes obj) {
        try {
            //Pega o saldo atual
            String sql = "select ClienteConta from Clientes where idCliente =?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, obj.getCod_cliente());
            
            ResultSet rs = stmt.executeQuery();
            
            rs.first();
            double saldo = rs.getDouble("ClienteConta") + 0.0;
            
            //Muda o saldo
            sql = "update Clientes set ClienteConta =? where idCliente =? ";
            stmt = conexao.prepareStatement(sql);

            stmt.setDouble(1, obj.getConta()+saldo);
            
            //Pegando o codigo do cliente para alterar
            stmt.setDouble(2, obj.getCod_cliente());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
