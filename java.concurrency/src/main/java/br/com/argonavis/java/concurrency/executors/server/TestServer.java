package br.com.argonavis.java.concurrency.executors.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class TestServer {
	public static void main(String[] args) throws Exception {
		Executor pool = Executors.newFixedThreadPool(5);
		System.out.print("Esperando cliente...");
		try (ServerSocket socket = new ServerSocket(9999)) {
			while (true) {
				final Socket cliente = socket.accept();
				String ip = socket.getInetAddress().getHostAddress();
				System.out.println("Cliente de " + ip + " conectado!");
				Runnable tarefa = new Runnable() {
					public void run() {
						try(cliente) {
							processarSocket(cliente);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				};
				pool.execute(tarefa);
			}
		}
	}

	public static void processarSocket(Socket s) throws IOException {
		try (InputStream in = s.getInputStream();
			 PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()))) {
			
			int command = in.read();
			switch (command) {
			case 0:
				out.println("Zero");
			default:
				out.println("Not Zero");
			}
		}
	}
}
