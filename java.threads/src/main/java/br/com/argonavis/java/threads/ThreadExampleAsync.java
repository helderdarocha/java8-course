package br.com.argonavis.java.threads;

public class ThreadExampleAsync {
    public static void main(String[] args) {
        Runnable paralelo = new RunnableHelloWorld();
        Thread t1 = new Thread(paralelo);
        t1.start();
        System.out.println("Thread principal: " + Thread.currentThread().getName());
        System.out.println("ID do thread principal: " + Thread.currentThread().getId());
    }
}

