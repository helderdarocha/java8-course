/*
 * Exemplos de
http://www.eclipse.org/jetty/documentation/9.3.x/embedding-jetty.html
 */
package br.com.argonavis.jettytest;

import org.eclipse.jetty.server.Server;

public class JettyTest {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.start();
        server.dumpStdErr();
        server.join();
    }
}
