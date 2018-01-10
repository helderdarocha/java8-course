package br.com.argonavis.java.concurrency;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Utils {
	public static void simulatedPause(int maxTime) {
		try { 
			Thread.sleep(new Random().nextInt(maxTime)); 
		} catch (InterruptedException ignored) {
			Thread.currentThread().interrupt();
		}
	}
	 
	public static void begin(String name) {
		System.out.println(Thread.currentThread().getName() + " begin " + name);
	}

	public static void end(String name) {
		System.out.println(Thread.currentThread().getName() + " end " + name);
	}
	
	public static void log(String name) {
		System.out.println(Thread.currentThread().getName() + " " + name);
	}
	
	public static String loadText(String name) throws IOException {
		try (FileChannel channel = (FileChannel) Files.newByteChannel(Paths.get(name))) {
			long size = channel.size();
			MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < size; i++) {
				builder.append((char) buffer.get());
			}
			return builder.toString();
		}
	}

}
