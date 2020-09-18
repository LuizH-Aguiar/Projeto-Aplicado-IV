/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    public Connection getConnection (){
    
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_java", "root", "");
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
