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
            stmt.setString(3, obj.getCnpj());
          
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
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, obj.getEmpresa());
            stmt.setString(2, obj.getRepresentante());
            stmt.setString(3, obj.getCnpj());

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

            //Pegando o codigo do fornecedor para excluir
            stmt.setInt(1, obj.getCod_fornecedor());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que lista todos os fornecedores
    public DefaultTableModel Listar() {
        try {
            String sql = "select * from Fornecedores";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Código");
            model.addColumn("Empresa");
            model.addColumn("Representante");
            model.addColumn("CNPJ");
            model.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idFornecedor") + "",
                    rs.getString("FornecedorEmpresa") + "",
                    rs.getString("FornecedorRepresentante") + "",
                    rs.getString("FornecedorCNPJ") + ""
                });
            }
            
            stmt.close();
            return model;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo que busca fornecedores
    public DefaultTableModel Buscar(String busca) {
        try {
            String sql = "select * from Fornecedores where FornecedorEmpresa like ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, ("%"+busca+"%"));
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Código");
            model.addColumn("Empresa");
            model.addColumn("Representante");
            model.addColumn("CNPJ");
            model.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idFornecedor") + "",
                    rs.getString("FornecedorEmpresa") + "",
                    rs.getString("FornecedorRepresentante") + "",
                    rs.getString("FornecedorCNPJ") + ""
                });
            }
            
            stmt.close();
            return model;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
