package br.com.argonavis.java.io;

import java.io.IOException;
import java.io.StringReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class WordReader {
	private List<String> words = new ArrayList<>();
	private Map<Character, Integer> lettersMap = new TreeMap<>();
	private Map<String, Integer> wordsMap = new TreeMap<>();
	private List<Character> nonLetters = new ArrayList<>();

	private StringBuilder currentWord;
	private boolean inWord = false;

	private final StringReader reader;

	public WordReader(StringReader reader) {
		this.reader = reader;
	}

	public boolean isLetter(char c) {
		return c >= 'a' && c <= 'z';
	}

	public void readLettersAndWords() throws IOException {
		try (reader) {
			int c = reader.read();
			//int count = 0;
			while (c > 0) {
				Character character = Character.toLowerCase((char)c);
				if (isLetter(character)) {
					if (lettersMap.containsKey(character)) {
						lettersMap.put(character, lettersMap.get(character) + 1);
					} else {
						lettersMap.put(character, 1);
					}
					if (!inWord) {
						currentWord = new StringBuilder();
						inWord = true;
					}
					currentWord.append(character);
				} else {
					nonLetters.add(character);
					if (inWord) {
						words.add(currentWord.toString());
						inWord = false;
					}
				}
				//++count;
				//if(count % 100000 == 0)
				//    System.out.print('.');
				c = reader.read();
			}
		}
		//System.out.println('X');
	}

	public void makeWordsMap() throws IOException {
		//int count = 0;
		for (String word : words) {
			//++count;
			//if(count % 100000 == 0)
			//    System.out.print('.');
			if (wordsMap.containsKey(word)) {
				wordsMap.put(word, wordsMap.get(word) + 1);
			} else {
				wordsMap.put(word, 1);
			}
		}
	}

	public void printWordStats() {
		Map<String, Integer> sortedMap = wordsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(100)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e1, LinkedHashMap::new));
		
		for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	public void printLetterStats() {
		Map<Character, Integer> sortedMap = lettersMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e1, LinkedHashMap::new));
		
		for (Map.Entry<Character, Integer> entry : sortedMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	public static void main(String[] args) throws IOException {
		String sourceText = loadText(System.getProperty("user.dir") + "/target/classes/2701.txt");
		WordReader reader = new WordReader(new StringReader(sourceText));
		reader.readLettersAndWords();
		reader.printLetterStats();
		reader.makeWordsMap();
		reader.printWordStats();
	}

	public static String loadText(String name) throws IOException {
		try (FileChannel channel = (FileChannel) Files.newByteChannel(Paths.get(name))) {
			long size = channel.size();
			MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < size; i++) {
				builder.append((char) buffer.get());
			}
			return builder.toString();
		}
	}
}
