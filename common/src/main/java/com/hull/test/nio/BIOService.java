package com.hull.test.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 *
 * @author
 * @create 2018-09-05 下午10:10
 **/

public class BIOService {
    private ServerSocket server;

    public BIOService(int port) throws IOException {
        server = new ServerSocket(port);
    }

    private void lister() throws IOException {
        while (true){
            Socket socket = server.accept();
            InputStream is = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len=is.read(bytes))!=-1){
                System.out.println("收到："+new String(bytes,0,len));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new BIOService(6666).lister();
    }
}
