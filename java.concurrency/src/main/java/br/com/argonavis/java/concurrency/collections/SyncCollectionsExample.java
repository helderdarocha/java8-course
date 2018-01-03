package br.com.argonavis.java.concurrency.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.argonavis.java.concurrency.Utils;

public class SyncCollectionsExample {

	public static void main(String[] args) throws InterruptedException {
		List<String> lista = new ArrayList<>();
		lista.add("1");
		lista.add("2");

		List<String> listaSync = Collections.synchronizedList(lista);
		lista = null;
		
		Thread t1 = new Thread(()-> {
			for(int i = 0; i < 10; i++) {
				listaSync.add(""+(char)(i + 'a'));
				Utils.simulatedPause(100);
			}
		});
		
		Thread t2 = new Thread(()-> {
			for(int i = 0; i < 10; i++) {
				listaSync.add(""+(char)(i + 'A'));
				Utils.simulatedPause(100);
			}
		});
		
		Thread t3 = new Thread(()-> {
			Utils.simulatedPause(200);
			System.out.println(listaSync); // leitura
		});
		
		Thread t4 = new Thread(()-> {
			Utils.simulatedPause(200);
			System.out.println(listaSync); // leitura
		});
		
		t1.start();
		t3.start();
		t2.start();
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();

		Map<String, Double> mapa = new HashMap<>();
		Map<String, Double> mapaThreadSafe = Collections.synchronizedMap(mapa);
		mapa = null;
		mapaThreadSafe.put("Key1", 1.0);
		mapaThreadSafe.put("Key2", 2.0);
		System.out.println(mapaThreadSafe);

	}

}
