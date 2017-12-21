package br.com.argonavis.java.threads.part1.synch;

import java.util.Arrays;
import java.util.Random;

public class SyncStringArray {
	private char[] letters = {'1', '2', '3', '4', '5'};
	
	public synchronized void reverse() {
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
	public synchronized String toString() {
		return new String(letters);
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof SyncStringArray)) return false;
		SyncStringArray array = (SyncStringArray) other;
		return Arrays.equals(letters, array.letters);
	}
}
