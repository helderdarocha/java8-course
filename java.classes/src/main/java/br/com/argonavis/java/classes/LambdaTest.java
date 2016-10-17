package br.com.argonavis.java.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LambdaTest  {
	
    @FunctionalInterface
    public static interface Interface {
        void m1();
    }
    
    public static void method1(ActionListener s) {
    	s.actionPerformed(null);
    }
    public static void method2(Interface i) {
    	i.m1();
    }
    public static void method3(Runnable s) {
    	s.run();
    }
    
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        method1( (ActionEvent e) ->  System.out.println("Hello 1 - Lambda"));
        method2( () -> System.out.println("Hello 2 - Lambda"));
        method3( () -> System.out.println("Hello 3 - Lambda"));

    }

}