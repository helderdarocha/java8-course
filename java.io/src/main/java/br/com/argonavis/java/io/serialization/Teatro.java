package br.com.argonavis.java.io.serialization;

import java.util.HashMap;
import java.util.Map;

public class Teatro {
	private static int contador = 1;
	private static Map<String, Ingresso> reservas = new HashMap<>();
	
	static {
		reservas.put("Rayssa Yavanova",  new Ingresso(++contador));
	}
	
	public static void reservar(String nome) {
		if(!reservas.containsKey(nome)) {
		    reservas.put(nome,  new Ingresso(++contador));
		}
	}
	
	public static Ingresso getReserva(String nome) {
		return reservas.get(nome);
	}
}
