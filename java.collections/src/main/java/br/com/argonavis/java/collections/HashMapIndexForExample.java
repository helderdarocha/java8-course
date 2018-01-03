package br.com.argonavis.java.collections;

public class HashMapIndexForExample {

	public static void main(String[] args) {
		int capacity = 16;
		int[] hashes = {0,1,2,3,4,7,8,9,10,14,15,16,21,22,28};
		for(int hash : hashes) {
			System.out.println("h: " + hash + ", bucket: " + indexFor(hash, capacity));
		}

	}
	
	static int indexFor(int h, int length) { //h = hash of key 
	     return h & (length-1);               //length = capacity of array at 
	} 

}
