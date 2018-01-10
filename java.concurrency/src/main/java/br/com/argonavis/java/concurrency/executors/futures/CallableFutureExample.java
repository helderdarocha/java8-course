package br.com.argonavis.java.concurrency.executors.futures;

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

import br.com.argonavis.java.concurrency.Utils;

public class CallableFutureExample {
	
	static class FileOpener implements Callable<String> {
		String filename;
		public FileOpener(String filename) {
			this.filename = filename;
		}

		@Override
		public String call() throws Exception {
			Utils.log("Reading file.");
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

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		
		Callable<String> callableTask = new FileOpener(System.getProperty("user.dir") + "/target/classes/sample.txt");
		Future<String> fileContents = service.submit(callableTask);

		FutureTask<String> readFileTask = new FutureTask<String>(callableTask);
		service.execute(readFileTask);
		
		try {
			System.out.println("\nFuture result: \n"     + fileContents.get(10, TimeUnit.SECONDS));
			System.out.println("\nFutureTask result: \n" + readFileTask.get());
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.out.println("File reading timed out");
		}
	}
}
