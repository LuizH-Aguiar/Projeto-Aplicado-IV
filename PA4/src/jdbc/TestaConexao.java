/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package jdbc;

/**
 *
 * @author Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
public class TestaConexao {
    
    public static void main(String[]args){
     
        new ConnectionFactory().getConnection();
        
        System.out.println("Conexão criada com sucesso");
    }
    
}
