package br.com.argonavis.java.threads.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientExample {

	public static void main(String[] args) throws UnknownHostException, IOException {
		try (Socket connection = new Socket("localhost", 9999);
		     BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		     PrintWriter out   = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()))) {
		    
			System.out.println("Asking for the time.");
			out.println("get-time");
			out.flush();
			System.out.println("Server says: " + in.readLine());
			
			System.out.println("Asking for the host.");
			out.println("get-host");
			out.flush();
			System.out.println("Server says: " + in.readLine());
			
			System.out.println("Quitting.");
			out.println("exit");
			out.flush();
			System.out.println("Server says: " + in.readLine());
			
		}
	}
}
