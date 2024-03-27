package server;

import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket serverSocket;
    private boolean running;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        running = true;
        System.out.println("Server started on port " + port);
    }

    public void start() {
        while (running) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Handle client communication in a separate thread
                new ClientHandler(clientSocket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() throws IOException {
        running = false;
        serverSocket.close();
        System.out.println("Server stopped.");
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader reader;
        private PrintWriter writer;

        public ClientHandler(Socket socket) throws IOException {
            this.clientSocket = socket;
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        }

        public void run() {
            try {
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    System.out.println("Received message from client: " + inputLine);

                    // Process message if needed

                    // Echo the message back to the client
                    writer.println("Server: " + inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
