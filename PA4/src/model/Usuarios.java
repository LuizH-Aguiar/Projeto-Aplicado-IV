/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package model;

/**
 *
 * @author Fabiana Nunes
 */
public class Usuarios {
    
    private int codigo;
    private String nome;
    private String senha;
    private String tipo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
