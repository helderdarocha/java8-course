package br.com.argonavis.java.classes.geometria;

import java.awt.Color;

import br.com.argonavis.java.classes.geometria.bitmap.SuperficieColorida;

public class CirculoColorido extends Circulo implements SuperficieColorida {
	
	private Color cor;

	public CirculoColorido(int raio) {
		super(raio);
		this.cor = SuperficieColorida.BLACK;
	}
	
	public CirculoColorido(int raio, Color cor) {
		this(raio);
		this.cor = cor;
	}

	// Método default - a implementação é opcional
	@Override
	public Color getColor() {
		return cor;
	}

}
