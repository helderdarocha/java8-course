package br.com.argonavis.java.concurrency.locks;

public class StampedDictionaryExample {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Adding to dictionary");
		StampedTermDictionary dict = new StampedTermDictionary();
		
		Thread[] writers = {
			new Thread(() -> dict.insert("Guidraco", "A toothed crested pterosaur.")),
			new Thread(() -> dict.insert("Tupuxuara", "A toothless crested pterosaur.")),
			new Thread(() -> dict.insert("Tyranosaurus", "A large carnivorous dinosaur.")),
			new Thread(() -> dict.insert("Stenopterygius", "A jurassic ichthyosaur.")),
			new Thread(() -> dict.insert("Stegosaurus", "An armored dinosaur.")),
			new Thread(() -> dict.insert("Archeopteryx", "A flying dinosaur."))
		};
		
		for(Thread t : writers) {
			t.start();
		}
		
		for(Thread t : writers) {
			t.join();
		}
		
		System.out.println("Looking up in dictionary");
		Thread[] readers = {
			new Thread(() -> System.out.println(dict.lookup("Guidraco"))),
			new Thread(() -> System.out.println(dict.lookup("Tupuxuara"))),
			new Thread(() -> System.out.println(dict.lookup("Stenopterygius"))),
			new Thread(() -> System.out.println(dict.lookup("Archeopteryx"))),
			new Thread(() -> System.out.println(dict.lookup("Stegosaurus"))),
			new Thread(() -> System.out.println(dict.lookup("Tyranosaurus")))
		};
		
		for(Thread t : readers) {
			t.start();
		}
	}
}
