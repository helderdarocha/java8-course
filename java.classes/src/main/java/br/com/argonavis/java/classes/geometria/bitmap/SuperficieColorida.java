package br.com.argonavis.java.classes.geometria.bitmap;

import java.awt.Color;
import java.util.Random;

import br.com.argonavis.java.classes.geometria.Superficie;

public interface SuperficieColorida extends Superficie {
	Color WHITE = new Color(255,255,255);
	Color BLACK = new Color(0,0,0);
	Color RED   = new Color(255,0,0);
	Color GREEN = new Color(0,255,0);
	Color BLUE  = new Color(0,0,255);
	
	static String convert(Color c) {
		return Integer.toHexString(c.getRed()) + Integer.toHexString(c.getGreen()) + Integer.toHexString(c.getBlue());
	}
	
	private int getColorComponent() {
		return new Random().nextInt(256);
	};

	default java.awt.Color getColor() {
		return new Color(getColorComponent(), getColorComponent(), getColorComponent());
	}
}
