package br.com.argonavis.java.classes.geometria;

public interface Superficie {
	double area();

	default double perimetro() {
		return 0;
	}

}
