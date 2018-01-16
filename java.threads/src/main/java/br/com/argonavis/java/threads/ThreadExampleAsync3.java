package br.com.argonavis.java.threads;

public class ThreadExampleAsync3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override public void run() {
                System.out.println("Hello world paralelo!");
            }
        });
        t1.start();
        System.out.println("Thread principal: " + Thread.currentThread().getName());
    }
}
