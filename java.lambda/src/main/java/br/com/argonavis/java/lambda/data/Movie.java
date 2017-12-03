package br.com.argonavis.java.lambda.data;

public class Movie {
	private String title;
    private String director;
    private Integer year;
    private Integer duration;
    private String imdb;
    
    public Movie(String title, String director, String imdb, int year, int duration) {
    		this.title = title;
    		this.director = director;
    		this.imdb = imdb;
    		this.year = year;
    		this.duration = duration;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getImdb() {
		return imdb;
	}

	public void setImdb(String imdb) {
		this.imdb = imdb;
	}
	
    public String toString() {
    		return title + ", " + director + " (" + year + "), imdb: " + imdb + ", duration: " + duration + " min.";
    }
}
