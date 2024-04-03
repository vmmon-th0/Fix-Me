package org.broker;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void cleanSocketsIO() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void joinMarket(String ip, int port) {
        System.out.printf("Enter join market process");
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("hello router");

            String resp = in.readLine();
            System.out.println("Response to broker: " + resp);

            cleanSocketsIO();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.printf("Hello from broker!");
        Main mainObject = new Main();
        mainObject.joinMarket("127.0.0.1", 5000);
    }
}