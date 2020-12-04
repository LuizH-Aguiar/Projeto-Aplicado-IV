/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jdbc.ConnectionFactory;
import model.TiposUsuarios;

/**
 *
 * @author gabri
 */
public class TiposUsuariosDAO {
    
    private Connection conexao;
    
    //Construtor para abrir a conexão
    public TiposUsuariosDAO() throws SQLException {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    //Metodo que cadatra um tipo de usuario e suas respectivas permissoes
    public void Cadastrar(TiposUsuarios tipo, List permissoes) {
        try {
            //Encontra o id do tipo e das permissões
            String sql = "select idPermissao from Permissoes";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            rs.last();
            int N_Perm = rs.getInt("idPermissao") + 1;
            
            //Cadastra as permissões
            sql = "insert into Permissoes "
                    + "(PermissaoCadastrar, PermissaoBuscar, PermissaoAlterar, PermissaoCompra, "
                    + "PermissaoVenda, PermissaoPagamentos, PermissaoClientes, PermissaoFornecedores, "
                    + "PermissaoProdutos, PermissaoUsuarios, PermissaoConfiguracoes, PermissaoRelatorios) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?) ";
            stmt = conexao.prepareStatement(sql);
            
            //Pega todos os valores dentro da lista
            for (int i=0; i<12; i++) {
                stmt.setInt(i+1, Integer.parseInt(permissoes.getItem(i)));
            }
            
            stmt.execute();
            
            //Cadastra o tipo do usuário
            sql = "insert into TiposUsuarios values (?,?,?)";
            stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, N_Perm);
            stmt.setString(2, tipo.getNome());
            stmt.setInt(3, N_Perm);
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    //Metodo que altera um tipo de usuario e suas respectivas permissoes
    public void Alterar(TiposUsuarios tipo, List permissoes) {
        try {
            String //Cadastra as permissões
            sql = "update Permissoes set "
                    + "PermissaoCadastrar =?, PermissaoBuscar =?, PermissaoAlterar =?, PermissaoCompra =?, "
                    + "PermissaoVenda =?, PermissaoPagamentos =?, PermissaoClientes =?, PermissaoFornecedores =?, "
                    + "PermissaoProdutos =?, PermissaoUsuarios =?, PermissaoConfiguracoes =?, PermissaoRelatorios =? "
                    + "where idPermissao =?";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            //Pega todos os valores dentro da lista
            for (int i=0; i<12; i++) {
                stmt.setInt(i+1, Integer.parseInt(permissoes.getItem(i)));
            }

            //Pegando o codigo do tipo para alterar
            stmt.setDouble(13, tipo.getCod_permissoes());

            stmt.execute();
            
            //Cadastra o tipo do usuário
            sql = "update TiposUsuarios set TipoUsuarioNome =? where idTipoUsuario =?";
            stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, tipo.getNome());
            
            //Pega o codigo do tipo para alterar
            stmt.setInt(2, tipo.getCod_tipo());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
            
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //Metodo que exclui um tipo de usuario e suas respectivas permissoes
    public void Excluir(TiposUsuarios tipo) {
        try {
            String sql = "select UsuarioTipo from Usuarios where UsuarioTipo =?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, tipo.getCod_tipo());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Item não pode ser excluído, pois é utilizado em registros");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!");
            }
            
            //Exclui tipo
            sql = "delete from TiposUsuarios where idTipoUsuario =? ";
            stmt = conexao.prepareStatement(sql);
            
            //Pegando o codigo do tipo para excluir
            stmt.setInt(1, tipo.getCod_tipo());
            stmt.execute();
            
            
            //Exclui permissoes
            sql = "delete from Permissoes where idPermissao =? ";
            stmt = conexao.prepareStatement(sql);
            
            //Pegando o codigo do tipo para excluir
            stmt.setInt(1, tipo.getCod_permissoes());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    //Metodo que lista os tipos de usuarios
    public List ListarTipos() {
        try {
            String sql = "select * from TiposUsuarios";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            List tipos = new List();
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                tipos.add(rs.getString("TipoUsuarioNome")+"");
            }
            
            stmt.close();
            
            return tipos;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    //Metodo que busca tipos de usuarios
    public DefaultTableModel BuscarTipos(String busca) {
        try {
            String sql = "select * from TiposUsuarios where TipoUsuarioNome like ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, ("%"+busca+"%"));
            ResultSet rs = stmt.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Código");
            model.addColumn("Tipo de usuário");
            model.addColumn("Cadastrar");
            model.addColumn("Buscar");
            model.addColumn("Alterar");
            model.setNumRows(0);
            
            while (rs.next()) {
                
                sql = "select * from Permissoes where idPermissao = ?";
                stmt = conexao.prepareStatement(sql);
                
                stmt.setInt(1, (rs.getInt("idTipoUsuario")));
                ResultSet rs2 = stmt.executeQuery();
                
                rs2.next();
                
                model.addRow(new Object[]{
                    rs.getInt("idTipoUsuario") + "",
                    rs.getString("TipoUsuarioNome") + "",
                    rs2.getInt("PermissaoCadastrar") == 1 ? "Permitido" : "Não permitido",
                    rs2.getInt("PermissaoBuscar") == 1 ? "Permitido" : "Não permitido",
                    rs2.getInt("PermissaoAlterar") == 1 ? "Permitido" : "Não permitido",
                });
            }
            
            stmt.close();
            
            return model;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    //Metodo que busca as permissoes de um tipo de usuario
    public List BuscarPermissoes(int tipo) {
        try {
            String sql = "select * from Permissoes where idPermissao = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, tipo);
            
            ResultSet rs = stmt.executeQuery();
            rs.first();
            
            List permissoes = new List();
            
            permissoes.add(rs.getInt("PermissaoCadastrar")+"");
            permissoes.add(rs.getInt("PermissaoBuscar")+"");
            permissoes.add(rs.getInt("PermissaoAlterar")+"");
            permissoes.add(rs.getInt("PermissaoCompra")+"");
            permissoes.add(rs.getInt("PermissaoVenda")+"");
            permissoes.add(rs.getInt("PermissaoPagamentos")+"");
            permissoes.add(rs.getInt("PermissaoClientes")+"");
            permissoes.add(rs.getInt("PermissaoFornecedores")+"");
            permissoes.add(rs.getInt("PermissaoProdutos")+"");
            permissoes.add(rs.getInt("PermissaoUsuarios")+"");
            permissoes.add(rs.getInt("PermissaoConfiguracoes")+"");
            permissoes.add(rs.getInt("PermissaoRelatorios")+"");
            
            stmt.close();
            
            return permissoes;
            
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
}
