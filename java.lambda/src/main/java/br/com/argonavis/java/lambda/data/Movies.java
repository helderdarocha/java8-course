package br.com.argonavis.java.lambda.data;

import java.util.ArrayList;
import java.util.Collection;

public class Movies {
	private Collection<Movie> movies;
	private static Movies instance = null;

	private Movies() {
		movies = new ArrayList<>();
		movies.add(new Movie("The Shining", "Stanley Kubrick", "tt0081505", 1980, 144));
		movies.add(new Movie("Melancolia", "Lars von Trier", "tt1527186", 2011, 136));
		movies.add(new Movie("Annie Hall", "Woody Allen", "tt0075686", 1977, 93));
		movies.add(new Movie("Fahrenheit 451", "François Truffaut", "tt0060390", 1966, 112));
		movies.add(new Movie("Nosferatu, eine Symphonie des Grauens", "F. W. Murnau", "tt0013442", 1922, 81));
		movies.add(new Movie("Amarcord", "Frederico Fellini", "tt0071129", 1973, 123));
		movies.add(new Movie("A Clockwork Orange", "Stanley Kubrick", "tt0066921", 1972, 136));
		movies.add(new Movie("La double vie de Véronique", "Krzysztof Kieslowski", "tt0101765", 1991, 98));
		movies.add(new Movie("Solyaris", "Andrei Tarkovsky", "tt0069293", 1972, 167));
		movies.add(new Movie("Jodaeiye Nader az Simin", "Asghar Farhadi", "tt1832382", 2011, 123));
		movies.add(new Movie("Hearat Shulayim", "Joseph Cedar", "tt1445520", 2011, 103));
		movies.add(new Movie("O Som ao Redor", "Kleber Mendonça Filho", "tt2190367", 2012, 131));
		movies.add(new Movie("Un Cuento Chino", "Sebastián Borensztein", "tt1705786", 2011, 93));
		movies.add(new Movie("El Laberinto del Fauno", "Guillermo del Toro", "tt0457430", 2006, 118));
		movies.add(new Movie("Holy Motors", "Leos Carax", "tt2076220", 2012, 115));
		movies.add(new Movie("La Flor de mi Secreto", "Pedro Almodovar", "tt0113083", 1995, 103));
		movies.add(new Movie("La Piel que Habito", "Pedro Almodovar", "tt1189073", 2011, 120));
		movies.add(new Movie("Stalker", "Andrei Tarkovsky", "tt0079944", 1979, 163));
		movies.add(new Movie("Nymphomaniac", "Lars von Trier", "tt1937390", 2013, 330));
		movies.add(new Movie("2001: A Space Odyssey", "Stanley Kubrick", "tt0062622", 1968, 160));
		movies.add(new Movie("The Godfather", "Francis Ford Copolla", "tt0068646", 1972, 175));
	}
	
	public static Collection<Movie> getMovieList() {
		if(instance == null) {
			instance = new Movies();
		}
		return instance.movies;
	}
}
