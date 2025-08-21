package backend;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyServer {

    private ClientHandler clientHandler;
    private int port;
    private final AtomicBoolean stop;
    private Thread serverThread;

    public MyServer(int port, ClientHandler clientHandler) {

        this.port = port;
        this.clientHandler = clientHandler;
        this.stop = new AtomicBoolean(false); // server is on
    }

    public void start() {

        serverThread = new Thread(() -> runServer());// needs to call a run() method
        serverThread.start();
    }

    private void runServer() {

        try (ServerSocket server = new ServerSocket(port)) {

            server.setSoTimeout(1000);// set timer of 1 second (1000 milsec)

            while (!stop.get()) // while stop is not true
            {
                try {

                    Socket client = server.accept();// wait for a new client
                    try {

                        clientHandler.handleClient(client.getInputStream(), client.getOutputStream());// get the input
                                                                                                      // and output of
                                                                                                      // the client

                    } catch (IOException e) {

                        System.err.println("Error handling client: " + e.getMessage());

                    } finally {
                        client.close();// connection to the client will be close at the end
                    }

                } catch (SocketTimeoutException e) {// check the stop condition

                } catch (IOException e) {

                    System.err.println("Error accepting client: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Server socket error: " + e.getMessage());// the server socket will be close automaticly
        }
    }

    void close() {
        stop.set(true);// server is off

        try {

            if (serverThread != null)
                serverThread.join(); // wait for the server thread to finish

        } catch (InterruptedException e) {
        }

    }

}
