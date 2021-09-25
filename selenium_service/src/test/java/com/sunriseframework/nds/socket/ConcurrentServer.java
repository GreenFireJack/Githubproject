package com.sunriseframework.nds.socket;

import com.sunriseframework.nds.Move;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenhao on 2021/9/1.
 */
public class ConcurrentServer {

    private static int distance;

    public static void clear() {
        distance = 0;
    }

    public static void start(WebDriver webDriver,WebElement webElement) throws IOException {
        ServerSocket server = new ServerSocket(20001);

        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        while (true){
            Socket socket = server.accept();
            Runnable  runnable = new Runnable(){
                @Override
                public void run() {
                    InputStream in = null;
                    try {
                        in = socket.getInputStream();
                        distance = in.read();
                        try {
                            System.out.println(distance);
                            Move.move(webDriver, webElement,distance);
                            clear();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            in.close();
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            executorService.submit(runnable);
        }
    }
}
