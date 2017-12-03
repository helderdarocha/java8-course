/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdc.java9.examples.processapi;

import java.io.IOException;

/**
 *
 * @author helderdarocha
 */
public class ProcessExamples {
    public static void main(String[] args) throws IOException {
        // JEP 102
        //Process p = new ProcessBuilder("/Applications/Firefox.app/Contents/MacOS/firefox").start();
        //System.out.println("Firefox pid: " + p.getPid());
        
        long pid = ProcessHandle.current().pid();
        System.out.println("Java pid: " + pid);
        
        Process p2 = Runtime.getRuntime().exec("/Applications/Firefox.app/Contents/MacOS/firefox");
        System.out.println("Firefox pid: " + p2.pid());
        
        // Algumas aplicações interessantes com streams e filtros
        // http://iteratrlearning.com/java/2017/03/12/java9-process-api.html
    }
}
