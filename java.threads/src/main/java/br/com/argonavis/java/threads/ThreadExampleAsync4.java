package br.com.argonavis.java.threads;

public class ThreadExampleAsync4 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println("Hello world paralelo!"));
        t1.start();
        System.out.println("Thread principal: " + Thread.currentThread().getName());
    }
}
