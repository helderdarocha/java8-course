package br.com.argonavis.java.threads.part1.synch;

import java.util.Arrays;
import java.util.Random;

public class StringArray {
	private char[] letters = {'1', '2', '3', '4', '5'};
	
	public void reverse() {
		char tmp = letters[0];
		letters[0] = letters[4];
		try {Thread.sleep(new Random().nextInt(500));} catch (InterruptedException e) {}
		letters[4] = tmp;
		try {Thread.sleep(new Random().nextInt(500));} catch (InterruptedException e) {}
		tmp = letters[1];
		letters[1] = letters[3];
		try {Thread.sleep(new Random().nextInt(500));} catch (InterruptedException e) {}
		letters[3] = tmp;
	}
	
	@Override
	public String toString() {
		return new String(letters);
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof StringArray)) return false;
		StringArray array = (StringArray) other;
		return Arrays.equals(letters, array.letters);
	}
}
