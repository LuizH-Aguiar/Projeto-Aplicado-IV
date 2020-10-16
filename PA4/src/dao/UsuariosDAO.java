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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jdbc.ConnectionFactory;
import model.Usuarios;
import telas.FrmMenu;

/**
 *
 * @author Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
public class UsuariosDAO {
    
    private Connection conexao;
    
    //Construtor para abrir a conexão
    public UsuariosDAO() throws SQLException {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    //Método que faz o login
    public boolean EfetuaLogin(Usuarios obj) throws SQLException {
        
        //Passo 1 - Comando sql
        String sql = "select * from Usuarios where UsuarioNome = ? and UsuarioSenha = ? and UsuarioTipo = ?";
        
        //Passo 2 - Organiza o sql
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getSenha());
        stmt.setString(3, obj.getTipo());
        
        //Passo 3 - Executa o comando
        ResultSet rs = stmt.executeQuery();
        
        //Passo 4 - Verificar o usuario
        if (rs.first()) {
            FrmMenu menu = new FrmMenu();
            
            //Acesso ao sistema
            JOptionPane.showMessageDialog(null, "Bem vindo ao sistema!");
            menu.setVisible(true);
            stmt.close();
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Usuario não encontrado!");
            stmt.close();
            return false;
        }
          
    }
    
    //Metodo que cadastra usuarios
    public void Cadastrar(Usuarios obj) {
        try {
            String sql = "insert into Usuarios (UsuarioNome, UsuarioSenha, UsuarioTipo) values (?,?,?) ";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getSenha());
            stmt.setString(3, obj.getTipo());
          
            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que altera usuarios
    public void Alterar(Usuarios obj) {
        try {
            String sql = "update Usuarios set UsuarioNome=?, UsuarioSenha=?, UsuarioTipo=?, where idUsuario=? ";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getSenha());
            stmt.setString(3, obj.getTipo());

            //Pegando o codigo do usuario para alterar
            stmt.setInt(4, obj.getCod_usuario());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que exclui usuarios
    public void Excluir(Usuarios obj) {
        try {
            String sql = "delete from Usuarios where idUsuario=? ";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            //Pegando o codigo do usuario para excluir
            stmt.setInt(1, obj.getCod_usuario());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que lista todos os usuarios
    public DefaultTableModel Listar() {
        try {
            String sql = "select * from Usuarios";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Código");
            model.addColumn("UserName");
            model.addColumn("Senha");
            model.addColumn("Tipo");
            model.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idUsuario") + "",
                    rs.getString("UsuarioNome") + "",
                    rs.getString("UsuarioSenha") + "",
                    rs.getString("UsuarioTipo") + ""
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
            String sql = "select * from Usuarios where UsuarioNome like ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, ("%"+busca+"%"));
            ResultSet rs = stmt.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Código");
            model.addColumn("UserName");
            model.addColumn("Senha");
            model.addColumn("Tipo");
            model.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idUsuario") + "",
                    rs.getString("UsuarioNome") + "",
                    rs.getString("UsuarioSenha") + "",
                    rs.getString("UsuarioTipo") + ""
                });
                //System.out.println("Nome: "+ rs.getString("UsuarioNome"));
            }
            
            stmt.close();
            return model;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
