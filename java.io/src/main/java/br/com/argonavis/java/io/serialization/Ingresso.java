/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.java.io.serialization;

class Ingresso {
    private int numero;

    public Ingresso() {}
    
    public Ingresso(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Ingresso{" + "numero=" + numero + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.numero;
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
        final Ingresso other = (Ingresso) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }
    
    
}
