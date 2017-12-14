package br.com.argonavis.java.concurrency.futures;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * See https://www.callicoder.com/java-8-completablefuture-tutorial/
 */
public class CompletableFutureExamples {

	static class TagOperations {
		public String tag(String tagName, String contents) {
			return "<" + tagName + ">" + contents + "</" + tagName + ">";
		}

		public String unTag(String taggedText) {
			Pattern pattern = Pattern.compile("<(\\w+)>(.*)</\\1>");
			Matcher matcher = pattern.matcher(taggedText);
			matcher.find();
			return matcher.group(2);
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(4);
		
		System.out.println("[1] Example 1 - Callbacks");
		System.out.println("[1] Create future 1.");
		CompletableFuture<String> future1 = new CompletableFuture<String>();

		System.out.println("[1] Setup callback for future 1.");
		new Thread(() -> {
			try {
				System.out.println("[1] Result of future 1: " + future1.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}).start();

		System.out.println("[1] Send completion notification for future 1.");
		future1.complete("[1] It's DONE.");

		System.out.println("\n[2] Example 2 - Asynchronous computation with Runnable");
		System.out.println("[2] Create future 2.");
		CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
			for (int i = 0; i < 1_000_000_000; i++) {
				if (i % 100_000_000 == 0)
					System.out.print("2");
			}
			System.out.println("[2] DONE");
		});
		System.out.println("[2] Will block future 2 until done.");
		try {
			future2.get();
			System.out.println("[2] Future 2 is DONE");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("\n[3] Example 3 - Asynchronous computation with supplyAsync & get");
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < 1_000_000_000; i++) {
				if (i % 100_000_000 == 0) {
					System.out.print("3");
					builder.append((char)(new Random().nextInt(26) + 'A'));
				}
			}
			System.out.println("[3] DONE");
			return builder.toString();
		});

		System.out.println("[3] Will block future 3 until done.");
		try {
			String result = future3.get();
			System.out.println("[3] Future 3 result is: " + result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n[4] Example 4 - Using thenRun");
		CompletableFuture.supplyAsync(() -> {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < 1_000_000_000; i++) {
				if (i % 100_000_000 == 0) {
					System.out.print("4");
					builder.append((char)(new Random().nextInt(26) + 'A'));
				}
			}
			System.out.println("[4] DONE");
			return builder.toString();
		}, service).thenRun(() -> System.out.println("[4] Future 4 is DONE (return value not used)"));

		System.out.println("\n[5] Example 5 - Using thenAccept");
		CompletableFuture.supplyAsync(() -> {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < 1_000_000_000; i++) {
				if (i % 100_000_000 == 0) {
					System.out.print("5");
					builder.append((char)(new Random().nextInt(26) + 'A'));
				}
			}
			System.out.println("[5] DONE");
			return builder.toString();
		}, service).thenAccept(result -> System.out.println("[5] Future 5 is DONE. Result is: " + result));
		
		System.out.println("\n[6] Example 6 - Cascading thenApply + thenAccept");
		TagOperations to = new TagOperations();
		
		CompletableFuture<String> future6 = CompletableFuture.supplyAsync(() -> "text", service)
			.thenApply(s -> to.tag("p", s))
			.thenApply(s -> s.concat("\n"))
			.thenApply(s -> "\n".concat(s))
			.thenApply(s -> to.tag("div", s));
		
		future6.thenAccept(s -> System.out.println("[6] Future 6 result:\n" + s));
		
		System.out.println("\n[7] Example 7 - Asynchronous tasks with thenApplyAsync");

		CompletableFuture<String> future7 = CompletableFuture.supplyAsync(() -> "async-text", service)
			.thenApplyAsync(s -> to.tag("inner", s))
			.thenApplyAsync(s -> s.concat("\n"))
			.thenApplyAsync(s -> "\n".concat(s))
			.thenApplyAsync(s -> to.tag("outer", s));
		
		future7.thenAcceptAsync(s -> System.out.println("[7] Future 7 result:\n" + s));
		
		// 
		System.out.println("\n[8] Example 8 - Changing types - String to integer");
		System.out.println();
		CompletableFuture<Integer> future8 = future7.thenApply(s -> s.length());
		System.out.println("[8] Result: " + future8.get());
		
		System.out.println("\n[9] Example 9 - thenCombine");
		CompletableFuture<Integer> future9 = future6.thenCombine(future8, (f6,f8)-> f6.length() + f8);
		System.out.println("[9] Result (string length of future6 + future8): " + future9.get());
		
		System.out.println("\n[10] Example 10 - allOf");
		List<CompletableFuture<String>> futures = List.of(future1, future3, future6, future7);
		CompletableFuture<Void> future10 = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
		future10.thenApply(s -> futures.stream().map(f -> f.join()).collect(Collectors.toList()))
			    .thenAccept(s -> System.out.println("[10] Future 10 result:\n{{{{" + s + "}}}}"));

		System.out.println("\n[11] Example 11 - exceptionally");

		CompletableFuture.supplyAsync(() -> "text", service)
			.thenApply(s -> {
				if(s.equals("text")) 
					throw new RuntimeException("Write something creative!");
				return s;
			})
			.exceptionally(e -> {
				System.out.println("[11] Exception occurred. Replacing text.");
				return "Creative Text";
			})
			.thenAcceptAsync(s -> System.out.println("[11] Future 11 result:\n" + s));
		
		System.out.println("\n[11] Example 12 - handle");
		
		CompletableFuture.supplyAsync(() -> new Random().nextInt(2)==0 ? "text" : "normal text", service)
		.thenApply(s -> {
			if(s.equals("text")) 
				throw new RuntimeException("Write something creative!");
			return s;
		})
		.handle((data, error) -> {
			if(error != null) {
			    System.out.println("[12] Error occurred. Replacing text.");
			    return "Creative Text";
			} else {
				return data;
			}
		})
		.thenAcceptAsync(s -> System.out.println("[12] Future 12 result:\n" + s));
		service.shutdown();
	}

}
