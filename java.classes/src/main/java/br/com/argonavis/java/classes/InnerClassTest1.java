package br.com.argonavis.java.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InnerClassTest1  {
	
    public static interface Interface {
        void m1();
    }
    
    private static class Inner1 implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
            System.out.println("Hello 1");
        }
    }
    
    private static class Inner2 implements InnerClassTest1.Interface {
    	private String nome;
    	public Inner2(String nome) {
    		this.nome = nome;
    	}
    	public void m1() {
            System.out.println("Hello 2: " + nome);
        }
    }
    
    public static void method1(ActionListener s) {
    	s.actionPerformed(null);
    }
    public static void method2(Interface i) {
    	i.m1();
    }

    public static void main( String[] args ) {

        InnerClassTest1.Inner1 i1 = new Inner1();
        InnerClassTest1.Inner2 i2 = new Inner2("Hello World");
        
        method1(i1);
        method2(i2);
        
    }
}