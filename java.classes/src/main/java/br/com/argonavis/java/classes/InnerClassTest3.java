package br.com.argonavis.java.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.argonavis.java.classes.FunctionalInterfaceTest.Interface;

public class InnerClassTest3  {

    public static void method1(ActionListener s) {
    	s.actionPerformed(null);
    }
    public static void method2(Interface i) {
    	i.m1();
    }
    public static void method3(Runnable s) {
    	s.run();
    }
    
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        class Inner1 implements ActionListener {
        	public void actionPerformed(ActionEvent e) {
                System.out.println("Hello 1");
            }
        }
        class Inner2 implements Interface {
        	public void m1() {
                System.out.println("Hello 2");
            }
        }
        
        class Inner3 implements Runnable {
        	public void run() {
                System.out.println("Hello 3");
        	}
        }
        
        ActionListener i1 = new Inner1();
        Interface i2 = new Inner2();
        Runnable i3 = new Inner3();
        
        method1(i1);
        method2(i2);
        method3(i3);
        
    }
}