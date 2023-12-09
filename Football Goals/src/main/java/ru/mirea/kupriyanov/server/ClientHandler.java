package ru.mirea.kupriyanov.server;

import ru.mirea.kupriyanov.client.GoalsPerSeason;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private ObjectInputStream  in;
    private ObjectOutputStream  out;


    @Override
    public void run() {
        try {
            GoalsPerSeason goalsPerSeason = (GoalsPerSeason) in.readObject();
            System.out.println("Server got the data");
            String result = "Количество голов игрока за сезон: " + goalsPerSeason.getGoals() +
                    ". В среднем за матч: " + (double) goalsPerSeason.getGoals() / goalsPerSeason.getMatchList().size();
            Thread.sleep(3000);
            out.writeObject(result);
            clientSocket.close();
            System.out.println("Server has finished his work");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.in = new ObjectInputStream(clientSocket.getInputStream());
        this.out = new ObjectOutputStream(clientSocket.getOutputStream());
    }
}
