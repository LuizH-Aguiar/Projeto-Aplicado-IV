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
import model.Usuarios;

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
    public Usuarios EfetuaLogin(Usuarios obj) throws SQLException {
        
        //Passo 1 - Comando sql
        String sql = "select U.idUsuario, U.UsuarioNome, U.UsuarioSenha, U.UsuarioTipo, T.TipoUsuarioNome from Usuarios U inner join TiposUsuarios T on U.UsuarioTipo = T.idTipoUsuario where UsuarioNome = ? and UsuarioSenha = ? ";
        
        //Passo 2 - Organiza o sql
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getSenha());
        
        //Passo 3 - Executa o comando
        ResultSet rs = stmt.executeQuery();
        Usuarios usuario = new Usuarios();
        
        //Passo 4 - Verificar o usuario
        if (rs.first()) {
            //Acesso ao sistema
            JOptionPane.showMessageDialog(null, "Bem vindo ao sistema!");
            
            usuario.setCod_usuario(rs.getInt("idUsuario"));
            usuario.setNome(rs.getString("UsuarioNome"));
            usuario.setSenha(rs.getString("UsuarioSenha"));
            usuario.setCod_Tipo(rs.getInt("UsuarioTipo"));
            usuario.setTipo(rs.getString("TipoUsuarioNome"));
        } else {
            JOptionPane.showMessageDialog(null, "Usuario não encontrado!");
            usuario.setCod_usuario(-1);
        }
        
        stmt.close();
        return usuario;
    }
    
    //Metodo que cadastra usuarios
    public void Cadastrar(Usuarios obj) {
        try {
            String sql = "insert into Usuarios (UsuarioNome, UsuarioSenha, UsuarioTipo) values (?,?,?) ";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getSenha());
            stmt.setInt(3, obj.getCod_Tipo());
          
            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que altera usuarios
    public void Alterar(Usuarios obj) {
        try {
            String sql = "update Usuarios set UsuarioNome=?, UsuarioSenha=? where idUsuario=? ";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getSenha());

            //Pegando o codigo do usuario para alterar
            stmt.setInt(3, obj.getCod_usuario());

            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que exclui usuarios
    public void Excluir(Usuarios obj) {
        try {
            String sql = "select CompraIDUsuario from Compras where CompraIDUsuario = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, obj.getCod_usuario());
            ResultSet rs1 = stmt.executeQuery();
            
            
            sql = "select VendaIDUsuario from Vendas where VendaIDUsuario = ?";
            stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, obj.getCod_usuario());
            ResultSet rs2 = stmt.executeQuery();
            
            
            if (rs1.next() || rs2.next()) {
                JOptionPane.showMessageDialog(null, "Item não pode ser excluído, pois é utilizado em registros");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!");
            }
            
            sql = "delete from Usuarios where idUsuario=? ";
            stmt = conexao.prepareStatement(sql);

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
            String sql = "select U.idUsuario, U.UsuarioNome, U.UsuarioSenha, T.TipoUsuarioNome from Usuarios U inner join TiposUsuarios T on U.UsuarioTipo = T.idTipoUsuario";
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
                    rs.getString("TipoUsuarioNome") + ""
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
            String sql = "select U.idUsuario, U.UsuarioNome, U.UsuarioSenha, T.TipoUsuarioNome from Usuarios U inner join TiposUsuarios T on U.UsuarioTipo = T.idTipoUsuario where UsuarioNome like ?";
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
                    rs.getString("TipoUsuarioNome") + ""
                });
            }
            
            stmt.close();
            return model;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
