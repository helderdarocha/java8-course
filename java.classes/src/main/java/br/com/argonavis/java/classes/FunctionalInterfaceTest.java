package br.com.argonavis.java.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionalInterfaceTest {

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

	public static void main(String[] args) {
		System.out.println("Hello World!");

		method1(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello 1");
			}
		});

		method2(new Interface() {
			public void m1() {
				System.out.println("Hello 2");
			}
		});

		method3(new Runnable() {
			public void run() {
				System.out.println("Hello 3");
			}
		});
	}
}