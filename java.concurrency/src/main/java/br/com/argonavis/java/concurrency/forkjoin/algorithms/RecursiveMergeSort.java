package br.com.argonavis.java.concurrency.forkjoin.algorithms;

public class RecursiveMergeSort {

	public RecursiveMergeSort() {}
	
	public int[] mergeSort(int[] array) {
		if(array.length > 1) {
			int[][] halves = split(array);
			array = merge(mergeSort(halves[0]), mergeSort(halves[1]));
		}
		return array;
	}

	public int[][] split(int[] pr) {
		int pqLen = pr.length / 2;
		int[] pq = new int[pqLen];
		int[] qr = new int[pr.length - pqLen];
		System.arraycopy(pr, 0, pq, 0, pq.length);
		System.arraycopy(pr, pqLen, qr, 0, qr.length);
		return new int[][] {pq, qr};
	}
	
	public int[] merge(int[] arrayL, int[] arrayR) {
		int[] array = new int[arrayL.length + arrayR.length];
		int i = 0, j = 0, k = 0;
	    for (; i < arrayL.length && j < arrayR.length; k++) {
	        if (arrayL[i] <= arrayR[j]) {
	            array[k] = arrayL[i];
	            i++;
	        } else {
	            array[k] = arrayR[j];
	            j++;
	        }
	    }

	    for (; i < arrayL.length; k++, i++)
	    		array[k] = arrayL[i];

	    for (; j < arrayR.length; k++, j++)
    		    array[k] = arrayR[j];

		return array;
	}

}
