package br.com.argonavis.java.threads.part1.wordfinder;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestWordFinder {
	public static void main(String[] args) throws IOException {
		
		String sourceText = loadText(System.getProperty("user.dir") + "/target/classes/sample.txt");
		String word = "thread";
		
		WordFinder runnable = new WordFinder(sourceText, word);
		
		new Thread(runnable).start();
		while (runnable.words.size() <= 5) { // leitura feita por thread main
			runnable.done = false; // alteração feita por thread main
		} 
		runnable.done = true; // alteração feita por thread main
		
		int n = 0;
		for(int wordPosition : runnable.words) {
			if(wordPosition != -1) {
				String palavra = sourceText.substring(wordPosition, wordPosition + word.length());
				System.out.println("Posição da occorrência no. " + ++n + " da palavra «" + palavra + "» :" + wordPosition);
			}
		}
	}
	
	public static String loadText(String name) throws IOException {
		try (FileChannel channel = (FileChannel) Files.newByteChannel(Paths.get(name))) {
			long size = channel.size();
			MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < size; i++) {
				builder.append((char)buffer.get());
			}
			return builder.toString();
		}
	}
}
