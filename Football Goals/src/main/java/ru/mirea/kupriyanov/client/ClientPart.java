package ru.mirea.kupriyanov.client;

import com.github.javafaker.Faker;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ClientPart {
    public static void main(String[] args) {

        List<Match> matches = generateData();
        GoalsPerSeason goalsPerSeason = new GoalsPerSeason(matches);
        try {
            Socket clientSocket = new Socket(InetAddress.getByName(null), 27001);
            System.out.println("Client connected to server");
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeObject(goalsPerSeason);
            outputStream.flush();
            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
            String response = (String) inputStream.readObject();
            System.out.println(response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static List<Match> generateData(){

        List<Match> matches = new ArrayList<>();
        for(int i = 1; i <= 30; i++){
            LocalDate matchDate = LocalDate.ofInstant(Faker.instance().date().between(
                    Date.from(Instant.now()),
                    Date.from(Instant.now().plus(365, ChronoUnit.DAYS))
            ).toInstant(), ZoneId.systemDefault());
            int goals = new Random().nextInt(6);
            matches.add(new Match(matchDate, goals));
        }

        return matches;
    }
}
