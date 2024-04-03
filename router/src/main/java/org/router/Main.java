package org.router;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private ServerSocket serverSocketBroker;
    private ServerSocket serverSocketMarket;
    private Socket clientSocketMarket;
    private Socket clientSocketBroker;
    private PrintWriter bOut, mOut;
    private BufferedReader bIn, mIn;

    public void cleanSocketsIO() {
        try {
            bIn.close();
            mIn.close();
            bOut.close();
            mOut.close();
            clientSocketMarket.close();
            clientSocketBroker.close();
            serverSocketBroker.close();
            serverSocketMarket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void marketConnectivityProvider() {
        try {
            serverSocketBroker = new ServerSocket(5000);
//            serverSocketMarket = new ServerSocket(5001);

            clientSocketBroker = serverSocketBroker.accept();
//            clientSocketMarket = serverSocketMarket.accept();

            bOut = new PrintWriter(clientSocketBroker.getOutputStream(), true);
//            mOut = new PrintWriter(clientSocketMarket.getOutputStream(), true);

            bIn = new BufferedReader(new InputStreamReader(clientSocketBroker.getInputStream()));
//            mIn = new BufferedReader(new InputStreamReader(clientSocketMarket.getInputStream()));

            String greeting = bIn.readLine();
            if ("hello router".equals(greeting)) {
                bOut.println("hello broker");
            }
            else {
                bOut.println("unrecognised greeting");
            }

            cleanSocketsIO();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello from router, we dispatch messages");
        Main mainObject = new Main();
        mainObject.marketConnectivityProvider();
    }
}