/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package model;

/**
 *
 * @author Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
public class Fornecedores {
    
    private int Cod_fornecedor;
    private String empresa;
    private String representante;
    private int cnpj;

    public int getCod_fornecedor() {
        return Cod_fornecedor;
    }

    public void setCod_fornecedor(int Cod_fornecedor) {
        this.Cod_fornecedor = Cod_fornecedor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    
}
