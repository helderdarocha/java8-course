package br.com.argonavis.java.concurrency.executors.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("localhost", 9999);
		s.getOutputStream().write(0);
		try (s; BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
			System.out.println(in.readLine());
		}
	}
}
