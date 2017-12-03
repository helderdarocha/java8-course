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
public class InterfaceExamples {
    public static void main(String[] args) {
        // JEP 213 (Milling Project Coin)
        Java9Interface java9Interface = new Java9Interface() {};
        System.out.println("Result: " + java9Interface.tagText("Hello", "h1"));
    }
}
