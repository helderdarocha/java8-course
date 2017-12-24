package br.com.argonavis.java.threads.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;

public class RunnableWorker implements Runnable {
	
	private Socket client;
	private ServerSocket server;
	
	public RunnableWorker(Socket client, ServerSocket server) {
		this.client = client;
		this.server = server;
	}

	@Override public void run() {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			 PrintWriter out   = new PrintWriter(new OutputStreamWriter(client.getOutputStream()))) {
			
			System.out.println("Waiting for commands.");
			String command = in.readLine();
			System.out.println("Command received from client: " + command);
			while (command != null) {
				System.out.println("Command received from client: " + command);
				if (command.equals("get-time")) {
					System.out.println("Sending time to " + client.getLocalAddress());
					out.println(Instant.now());
					out.flush();
				} else if (command.equals("get-host")) {
					System.out.println("Sending host to " + client.getLocalAddress());
					out.println(server.getInetAddress());
					out.flush();
				} else {
					System.out.println("Quitting " + client.getLocalAddress());
					out.println("Bye!");
					out.flush();
					break;
				}
				System.out.println("Waiting for commands.");
				command = in.readLine();
			}
		} catch (IOException e) {
			System.out.println("Exception: " + e);
			return;
		}
		System.out.println("Client thread for socket "+client.getLocalAddress()+" is DONE!");
	}

}
