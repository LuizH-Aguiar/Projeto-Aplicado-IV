/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package dao;

import com.sun.jdi.connect.spi.Connection;
import jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import telas.FrmPrincipal;

/**
 *
 * @author Fabiana Nunes
 */
public class UsuariosDAO {
    
    private Connection conexao;
    
    //Abrir conexao
    public UsuariosDAO() throws SQLExecption {
        this.conexao = new ConnectionFactory.getConnection();
    }
    
    //Metodo que faz o login
    public void EfetuaLogin(Usuarios obj) throws SQLExecption {
        //Passo 1 - Comando SQL
        String cmdSQL = "SELECT * FROM Usuarios where UsuarioNome = ? and UsuarioSenha = ? and UsuarioTipo = ?";
        
        //Passo 2 - Dados do comando
        PreparedStatement stmt = conexao.prepareStatement(cmdSql);
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getSenha());
        stmt.setString(3, obj.getTipo());
        
        //Passo 3 - Executa o comando
        ResultSet rs = stmt.executeQuery();
        
        //Passo 4 - Verificar o usuario
        if(rs.first()){
            FrmPrincipal menu = new FrmPrincipal();
            
            //Acesso ao sistema
            JOptionPane.showMessageDialog(null, "Bem vindo ao sistema!");
            menu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Usuários e/ou senha incorreto(s)!");
        }
    }
}
