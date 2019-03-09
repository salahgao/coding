package com.salah.messagequeue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BrokerServer implements Runnable {

    public static int SERVICE_PORT = 9999;

    private final Socket socket;

    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            while (true) {
                String str = in.readLine();
                if (str == null) {
                    continue;
                }
                System.out.println("接收到原始数据：" + str);

                if (str.equals("CONSUME")) {
                    String messsage = Broker.consume();
                    out.println(messsage);
                    out.flush();
                } else {
                    Broker.produce(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(SERVICE_PORT);
        while (true) {
            System.out.println("======1");
            BrokerServer brokeServer = new BrokerServer(server.accept());
            new Thread(brokeServer).start();
        }
    }

}
