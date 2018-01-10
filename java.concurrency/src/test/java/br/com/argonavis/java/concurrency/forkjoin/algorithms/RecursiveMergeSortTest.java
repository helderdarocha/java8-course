package br.com.argonavis.java.concurrency.forkjoin.algorithms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class RecursiveMergeSortTest {
	
	RecursiveMergeSort sort = new RecursiveMergeSort();
	
	int[][] arrays = {
			{1,2,3,4},
			{9,2,1},
			{1},
			{},
			{1,9,2,8,4,7},
			{4,3,2,1},
			{4,2,1,3},
			{3,1,4,2},
			{1,2,3,4,9,2,1},
	};
	
	int[][] expected = {
			{1,2,3,4},
			{1,2,9},
			{1},
			{},
			{1,2,4,7,8,9},
			{1,2,3,4},
			{1,2,3,4},
			{1,2,3,4},
			{1,1,2,2,3,4,9},
	};

	@Test
	void testMerge() {
		int[][] a = {
				{1,2,3,4},
				{},
				{3},
				{7,1},
				{9}
		};
		int[][] b = {
				{9,2,1},
				{2},
				{4},
				{5,1},
				{1,2}
		};
		int[][] e = {
				{1,2,3,4,9,2,1},
				{2},
				{3,4},
				{5,1,7,1},
				{1,2,9}
		};
		
		for(int i = 0; i < a.length; i++)
		    assertArrayEquals(e[i], sort.merge(a[i], b[i]));
	}
	
	@Test
	void testSplit() {
		int[] a = {1,2,3,4,9,2,1};
		int[][] expectedA = {{1,2,3},{4,9,2,1}};
		assertArrayEquals(expectedA, sort.split(a));
		
		int[] b = {9,2,1};
		int[][] expectedB = {{9},{2,1}};
		assertArrayEquals(expectedB, sort.split(b));
		
		int[] c = {2,1};
		int[][] expectedC = {{2},{1}};
		assertArrayEquals(expectedC, sort.split(c));
		
		int[] d = {9};
		int[][] expectedD = {{},{9}};
		assertArrayEquals(expectedD, sort.split(d));
		
		int[] e = {};
		int[][] expectedE = {{},{}};
		assertArrayEquals(expectedE, sort.split(e));
	}
	
	@Test
	void testMergeSort() {
		for(int i = 0; i < arrays.length; i++)
			assertArrayEquals(expected[i], sort.mergeSort(arrays[i]));
	}
	
}
