/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdc.java9.examples.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author helderdarocha
 */
public class ExceptionsExample {
    public static void main(String[] args) throws IOException {
        InputStream is = ExceptionsExample.class.getResourceAsStream("ExceptionsExample.java");
        read(is);
    }
    
    // is is effectively final
    // os is declared final (and not actually used)
    public static void read(InputStream is) throws IOException {
        BufferedReader reader = null;
        final OutputStream os = null;
        //try (InputStream a = is) {
        try (is; os) {
            reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while(line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        //} finally {
        //    reader.close();
        }
    }
}
