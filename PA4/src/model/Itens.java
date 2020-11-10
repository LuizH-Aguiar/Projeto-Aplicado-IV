/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package model;

/**
 *
 * @author Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
public class Itens {
    
    private int Cod_Lista;
    private int Cod_Item;
    private double valor;
    private int quantidade;
    private int Cod_CV;

    public int getCod_Lista() {
        return Cod_Lista;
    }

    public void setCod_Lista(int Cod_Lista) {
        this.Cod_Lista = Cod_Lista;
    }

    public int getCod_Item() {
        return Cod_Item;
    }

    public void setCod_Item(int Cod_Item) {
        this.Cod_Item = Cod_Item;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCod_CV() {
        return Cod_CV;
    }

    public void setCod_CV(int Cod_CV) {
        this.Cod_CV = Cod_CV;
    }
}
