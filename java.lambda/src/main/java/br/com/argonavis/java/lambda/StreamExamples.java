package br.com.argonavis.java.lambda;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.argonavis.java.lambda.data.Movie;
import br.com.argonavis.java.lambda.data.Movies;

public class StreamExamples {

	public static void main(String[] args) {
		Collection<Movie> movies = Movies.getMovieList();

		// count
		long count = movies.stream()
				.count();
		System.out.println("Movie count: " + count);

		// filter + count
		long before50count = movies.stream()
				.filter(m -> m.getYear() < 1970)
				.count();
		System.out.println("Movies before 1970: " + before50count);

		// filter + collect
		List<Movie> before50 = movies.stream()
				.filter(m -> m.getYear() < 1970)
				.collect(Collectors.toList());
		System.out.println("Movies before 1970: ");
		for (Movie m : before50) {
			System.out.println("* " + m);
		}
		
		// filter + collect + new stream + map + collect
		List<String> before50Titles = movies.stream()
				.filter(m -> m.getYear() < 1970)
				.collect(Collectors.toList())
				.stream()
				.map(Movie::getTitle)
				.collect(Collectors.toList());
		System.out.println("Titles of movies before 1970: ");
		for (String title : before50Titles) {
			System.out.println("* " + title);
		}

		// map / collect / reduce; sum and average
		List<Integer> durations = movies.stream()
				.map(Movie::getDuration) // same as .map(m -> m.getDuration())
				.collect(Collectors.toList());
		System.out.println("Durations: " + durations);
		
		Optional<Integer> sum = durations.stream()
				.reduce((a, b) -> a + b);
		System.out.println("Total duration: " + (sum.isPresent() ? sum.get() : 0) + " minutes.");
		double average = (double) sum.get() / count;
		System.out.println("Average duration per movie: " + new DecimalFormat(".00").format(average) + " minutes.");
		
		// Summing collector
		Integer durationSum = movies.stream()
				.map(Movie::getDuration) // same as .map(m -> m.getDuration())
				.collect(Collectors.summingInt(Integer::intValue));
		System.out.println("Duration sum: " + durationSum + " minutes.");
		
		// Averaging collector
		Double durationAvg = movies.stream()
				.map(Movie::getDuration) // same as .map(m -> m.getDuration())
				.collect(Collectors.averagingDouble(Integer::doubleValue));
		System.out.println("Duration average: " + new DecimalFormat(".00").format(durationAvg) + " minutes.");
		
	}

}
