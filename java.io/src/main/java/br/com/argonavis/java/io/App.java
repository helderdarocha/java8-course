package br.com.argonavis.java.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        URL url = new URL("http://www.argonavis.com.br/index.html");

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}