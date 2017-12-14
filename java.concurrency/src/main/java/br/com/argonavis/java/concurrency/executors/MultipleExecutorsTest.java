package br.com.argonavis.java.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultipleExecutorsTest {

	public static void main(String[] args) {
		System.out.println("3 tarefas em sequencia rodando em paralelo com uma o9utra tarefa");
		ExecutorService e1 = Executors.newSingleThreadExecutor();
		ExecutorService e2 = Executors.newSingleThreadExecutor();
		e1.execute( new ExclusiveTask() );
		e2.execute( new ConcurrentTask("Bg-Um"));
		e2.execute( new ConcurrentTask("Bg-Dois"));
		e2.execute( new ConcurrentTask("Bg-Tres"));
		e1.shutdown();
		e2.shutdown();

	}

}
