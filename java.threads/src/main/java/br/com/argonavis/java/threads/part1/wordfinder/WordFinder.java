package br.com.argonavis.java.threads.part1.wordfinder;

import java.util.ArrayList;
import java.util.List;

public class WordFinder implements Runnable {
	volatile boolean done;
	volatile List<Integer> words = new ArrayList<>();
	
	private String tmpText;
	private String wordToFind;
	private int position = 0;
	
	public WordFinder(String sourceText, String wordToFind) {
		this.tmpText = new String(sourceText);
		this.wordToFind = wordToFind;
	}

	public void run() {
		while (!done) { // leitura feita por thread secundário
			words.add(lookForWord(wordToFind)); // alteração feita por thread secundário
		}
		System.out.println("Thread irá terminar agora.");
	}

	private synchronized int lookForWord(String string) {
		int substringPosition = tmpText.indexOf(string);
		if(substringPosition > 0) {
			position += substringPosition+1;
			tmpText = tmpText.substring(substringPosition+1);
		} else {
			return -1;
		}
		
		return position-1;
	}
}
