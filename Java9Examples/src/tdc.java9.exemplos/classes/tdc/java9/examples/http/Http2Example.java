/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdc.java9.examples.http;

import java.io.IOException;
import java.net.URI;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

/**
 *
 * @author helderdarocha
 */
public class Http2Example {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
	HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.thedevelopersconference.com.br"))
                .GET()
                .build();
	HttpResponse.BodyHandler responseBodyHandler = HttpResponse.BodyHandler.asString();
	HttpResponse response = client.send(request, responseBodyHandler);
	String body = response.body().toString();
	System.out.println(body);
    }
}
