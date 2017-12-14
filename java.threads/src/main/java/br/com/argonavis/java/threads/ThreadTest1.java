package br.com.argonavis.java.threads;

public class ThreadTest1 {

	public static void main(String[] args) {
		Paralelo r1 = new Paralelo("da montanha", 1000);
		Thread carneiros1 = new Thread(r1);
		
		Paralelo r2 = new Paralelo("do campo", 500);
		Thread carneiros2 = new Thread(r2);
		
		carneiros1.start();
		carneiros2.start();
		
		Thread mainThread = Thread.currentThread();
		
		new Thread(new Runnable() {
			public void run() {
				while(carneiros1.isAlive() || carneiros2.isAlive() || mainThread.isAlive()) {
					int sorte = (int)(Math.random() * 50);
					if (sorte == 25) {
						System.out.println(">>> Redefinindo contagem!");
						r1.contagem = 50;
						r2.contagem = 25;
					}
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {} // espera 1 segundo
				}
				System.out.println("No threads alive!");
			}
		}).start();
		
		try {
			Thread.sleep(10000); // main thread will sleep for 10 seconds
		} catch (InterruptedException e) {}
		System.out.println("Main Thread - end");
	}

}
