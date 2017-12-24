package br.com.argonavis.java.threads.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedServerSocketExample {
	public static void main(String[] args) throws IOException {
		try (ServerSocket server = new ServerSocket(9999)) {
			while (true) {
				System.out.println("Server waiting for client.");
				Socket client = server.accept(); // blocks
				System.out.println("Client from " + client.getLocalAddress() + " connected.");
				new Thread(new RunnableWorker(client, server)).start();
			}
		}
	}
}
