package com.sunriseframework.nds.socket;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by chenhao on 2021/9/19.
 */
public class Client {

    //148  113  35  0.23648
    //113  87   26  0.230088
    //107  82   25  0.233644
    //146  113  33  0.23
    //151  117  34  0.23
    //118  90.86    27.14  0.23
    //153  117.81   35.19  差1
    //72   55.44(56)    16.56   刚好
    //130  100.1 (101)    29.9  刚好
    //137  105.49 (106) 31.51  差1
    //174  133.98 （134）           40.02刚好
    //95   73.15(74)             21.85 差2
    //125   96.25              28.75  差2
    //140   107.8           32.2
    //122   93.94            28.06    差1
    //185   142.45(143)                 42.55  刚好 (之前认为的刚好，其实是差1，差1是差1/2，差2是差3/4)
    //114   87.78                         差1
    //185   142.45(144)             42.55     刚好
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 20001;
        Socket socket = new Socket(host, port);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(144);
        //释放资源
        outputStream.close();
        socket.close();
    }
}
