package br.com.argonavis.java.threads.part1;

public class ThreadExampleAsync {
    public static void main(String[] args) {
        Runnable paralelo = new RunnableHelloWorld();
        Thread t1 = new Thread(paralelo);
        t1.start();
        System.out.println("Thread principal: " + Thread.currentThread().getName());
    }
}

