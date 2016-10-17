package br.com.argonavis.java.classes.geometria;

import java.util.Comparator;

public class PontoXComparator implements Comparator<Ponto>{

	@Override
	public int compare(Ponto o1, Ponto o2) {
		return (int)o1.distancia() - (int)o2.distancia();
	}
}
