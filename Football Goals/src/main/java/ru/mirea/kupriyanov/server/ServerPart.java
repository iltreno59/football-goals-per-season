package ru.mirea.kupriyanov.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPart implements Runnable{
    @Override
    public void run() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(27001);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            System.out.println("Connecting");
            Socket incomingConnection;
            try {
                incomingConnection = serverSocket.accept();
                System.out.println("Connection established");
                ClientHandler clientHandler = new ClientHandler(incomingConnection);
                Thread myThread = new Thread(clientHandler);
                myThread.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
