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
        String cmdSql = "SELECT * from Usuarios where UsuarioNome = ? and UsuarioSenha = ? and UsuarioTipo = ?";
        
        //Passo 2 - Organiza o sql
        PreparedStatement stmt = conexao.prepareStatement(cmdSql);
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getSenha());
        stmt.setString(3, obj.getTipo());
        
        //Passo 3 - executa o comando
        ResultSet rs = stmt.executeQuery();
        
        //Passo 4 - Verificar o usuario
        if (rs.first()) {
            FrmMenu menu = new FrmMenu();
            
            //Acesso ao sistema
            JOptionPane.showMessageDialog(null, "Bem vindo ao sistema!");
            menu.setVisible(true);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Usuario não encontrado!");
            return false;
        }
          
    }
}
