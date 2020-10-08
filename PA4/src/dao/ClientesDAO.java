/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jdbc.ConnectionFactory;
import model.Clientes;

/**
 *
 * @author Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
public class ClientesDAO {
    private Connection conexao;

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

            //Pegando o codigo do cliente para excluir
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, obj.getCod_cliente());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
