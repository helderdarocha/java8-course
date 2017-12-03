package br.com.argonavis.java.lambda;

import br.com.argonavis.java.lambda.data.Movie;
import br.com.argonavis.java.lambda.data.Operations;
import br.com.argonavis.java.lambda.function.FunctionalInterfaceFive;
import br.com.argonavis.java.lambda.function.FunctionalInterfaceFour;
import br.com.argonavis.java.lambda.function.FunctionalInterfaceOne;
import br.com.argonavis.java.lambda.function.FunctionalInterfaceThree;
import br.com.argonavis.java.lambda.function.FunctionalInterfaceTwo;
public class LambdaExamples {

	public static void main(String[] args) {
		// 1) Implementação de run() da interface funcional Runnable 
		// a) Usando lambda
		new Thread(() -> System.out.println("Lambda")).start();
		
		// b) Usando classe anônima
		new Thread(new Runnable() {
			public void run() {
				System.out.println("Anonymous");
			}
		}).start();
		
		FunctionalInterfaceOne obj1 = () -> System.out.println(123);
		obj1.doSomething();
		
		FunctionalInterfaceTwo obj2 = x -> x*x;
		System.out.println(obj2.doSomething(5));
		
		print((a,b) -> a*b, 3, 6);
		print(Operations::sum, 3, 6);
		
		printString((a, b)  -> {
			if(a > b) return "A is larger";
			else return "B is larger";
		}, 7, 8);
		
		FunctionalInterfaceFive<Movie> five = Movie::new;
		Movie m = five.create("The Shining", "Stanley Kubrick", "tt0081505", 1980, 146);
		System.out.println(m);

	}
	
	public static <T,U> void  printString(FunctionalInterfaceFour<T,U> three, T parameter1, T parameter2) {
		System.out.println(three.doSomething(parameter1, parameter2));
	}
	
	public static void print(FunctionalInterfaceThree three, double parameter1, double parameter2) {
		System.out.println(three.doSomething(parameter1, parameter2));
	}

}
