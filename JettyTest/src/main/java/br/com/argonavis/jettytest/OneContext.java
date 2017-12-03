/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.jettytest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;

public class OneContext {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        // Add a single handler on context "/hello"
        ContextHandler context = new ContextHandler();
        context.setContextPath("/hello");
        context.setHandler(new SimpleHandler());

        // Can be accessed using http://localhost:8080/hello
        server.setHandler(context);

        // Start the server
        server.start();
        server.join();
    }
}
