/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package model;

import java.awt.List;

/**
 *
 * @author Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
public class Permissoes {
    
    private int Cod_Permissao;
    private List permissoes;

    public int getCod_Permissao() {
        return Cod_Permissao;
    }

    public void setCod_Permissao(int Cod_Permissao) {
        this.Cod_Permissao = Cod_Permissao;
    }

    public List getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List permissoes) {
        this.permissoes = permissoes;
    }
    
    public boolean getPermissaoAt(int indice) {
        return (Integer.parseInt(this.permissoes.getItem(indice)) == 1);
    }
}
