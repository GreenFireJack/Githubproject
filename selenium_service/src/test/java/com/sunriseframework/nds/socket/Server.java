package com.sunriseframework.nds.socket;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chenhao on 2021/9/19.
 */
public class Server {

    private static int distance;

    public static int getDistance() {
        return distance;
    }

    public static void start() throws IOException {
        int port = 20001;
        ServerSocket server = new ServerSocket(port);
        Socket socket = server.accept();
        InputStream inputStream = socket.getInputStream();
        int read = inputStream.read();
        inputStream.close();
        socket.close();
        server.close();
        distance = read;
        System.out.println(read);
    }
}
