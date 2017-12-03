/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdc.java9.examples.interfaces;

/**
 *
 * @author helderdarocha
 */
public interface Java9Interface {
    default String tagText(String nome, String tag) {
        return initag(tag) + nome + endtag(tag);
    }
    
    private String initag(String tag) {
        return "<"+tag+">";
    }
    
    private String endtag(String tag) {
        return "</"+tag+">";
    }
}
