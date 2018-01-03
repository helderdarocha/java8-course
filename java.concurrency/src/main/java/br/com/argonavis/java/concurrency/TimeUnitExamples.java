package br.com.argonavis.java.concurrency;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class TimeUnitExamples {

	public static void main(String[] args) throws InterruptedException {
		// Conversions
		long days = TimeUnit.DAYS.convert(Instant.now().toEpochMilli(), TimeUnit.MILLISECONDS);
		System.out.println(days + " days since 1/1/1970");
		
		long days2 = TimeUnit.MILLISECONDS.toDays(Instant.now().toEpochMilli());
		System.out.println(days2 + " days since 1/1/1970");

		// Sleep
		System.out.println("Will sleep for 2 seconds.");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Done.");
	}
}
