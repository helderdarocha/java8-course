/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.java.io.serialization;

class Endereco implements java.io.Serializable {
    private String rua;
    private int numero;

    public Endereco() {}
    
    public Endereco(String rua, int numero) {
        this.rua = rua;
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.rua != null ? this.rua.hashCode() : 0);
        hash = 71 * hash + this.numero;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if ((this.rua == null) ? (other.rua != null) : !this.rua.equals(other.rua)) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Endereco{" + "rua=" + rua + ", numero=" + numero + '}';
    }
    
    
}
