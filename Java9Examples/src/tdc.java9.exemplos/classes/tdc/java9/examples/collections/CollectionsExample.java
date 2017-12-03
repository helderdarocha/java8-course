/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdc.java9.examples.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author helderdarocha
 */
public class CollectionsExample {

    public static void main(String[] args) {
        List<String> lista1 = new ArrayList<>();
        lista1.add("Um");
        lista1.add("Dois");
        lista1.add("Tres");
        lista1 = Collections.unmodifiableList(lista1);

        List<String> lista2 = List.of("Um", "Dois", "Três");
        for (String s : lista2) {
            System.out.println(s);
        }

        Set<String> conj = Set.of("A", "B", "C");
        for (String s : conj) {
            System.out.println(s);
        }

        Map<Integer, String> mapa1 = Map.of(1, "Um", 2, "Dois", 3, "Três");

        Map.Entry<String, String> e1 = Map.entry("A", "Um");
        Map.Entry<String, String> e2 = Map.entry("B", "Dois");
        Map<String, String> mapa2 = Map.ofEntries(e1, e2);
    }
}
