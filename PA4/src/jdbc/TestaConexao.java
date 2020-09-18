/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package jdbc;

public class TestaConexao {
    
    public static void main(String[]args) {
        
        new ConnectionFactory().getConnection();
        
        System.out.println("conexão criada com sucesso!");
    }
}
