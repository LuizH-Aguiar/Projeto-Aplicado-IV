/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package model;

/**
 *
 * @author Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
public class TiposUsuarios {
    
    private int Cod_tipo;
    private String nome;
    private int Cod_permissoes;

    public int getCod_tipo() {
        return Cod_tipo;
    }

    public void setCod_tipo(int Cod_tipo) {
        this.Cod_tipo = Cod_tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCod_permissoes() {
        return Cod_permissoes;
    }

    public void setCod_permissoes(int Cod_permissoes) {
        this.Cod_permissoes = Cod_permissoes;
    }
}
