/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package model;

/**
 *
 * @author Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
public class Compras {
    
    private int Cod_compra;
    private double valor;
    private String data;
    private int Cod_fornecedor;
    private int Cod_usuario;

    public int getCod_compra() {
        return Cod_compra;
    }

    public void setCod_compra(int Cod_compra) {
        this.Cod_compra = Cod_compra;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCod_fornecedor() {
        return Cod_fornecedor;
    }

    public void setCod_fornecedor(int Cod_fornecedor) {
        this.Cod_fornecedor = Cod_fornecedor;
    }

    public int getCod_usuario() {
        return Cod_usuario;
    }

    public void setCod_usuario(int Cod_Usuario) {
        this.Cod_usuario = Cod_Usuario;
    }
}
