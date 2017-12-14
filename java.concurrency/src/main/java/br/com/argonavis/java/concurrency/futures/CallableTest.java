package br.com.argonavis.java.concurrency.futures;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableTest {
	
	static class FileOpener implements Callable<String> {
		
		String filename;
		public FileOpener(String filename) {
			this.filename = filename;
		}

		@Override
		public String call() throws Exception {
			System.out.println("Reading file.");
			try (FileChannel channel = (FileChannel) Files.newByteChannel(Paths.get(filename))) {
				long size = channel.size();
				MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
				StringBuilder builder = new StringBuilder();
				for(int i = 0; i < size; i++) {
					builder.append((char)buffer.get());
				}
				return builder.toString();
			}
		}
		
	}
	
	static class Computer implements Callable<Double> {
		
		@Override
		public Double call() throws Exception {
			System.out.println("Making computation.");
			return Math.sqrt(6 * 29 * 765);
		}
		
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		
		Future<String> file = service.submit(new FileOpener(System.getProperty("user.dir") + "/target/classes/sample.txt"));
		Future<Double> calculation = service.submit(new Computer());
		Future<Integer> sum = service.submit(() -> 1+2+3+4+5);
		
		FutureTask<String> task = new FutureTask<String>(new FileOpener(System.getProperty("user.dir") + "/target/classes/sample.txt"));
		service.execute(task);
		
		try {
			System.out.println("Result: " + calculation.get());
			System.out.println("Result: " + sum.get());
			System.out.println("Result: \n" + file.get(10, TimeUnit.SECONDS));

			System.out.println("Result: \n" + task.get());
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.out.println("File reading timed out");
		}

	}
	
}
