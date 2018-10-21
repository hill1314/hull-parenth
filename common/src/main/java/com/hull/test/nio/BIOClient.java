package com.hull.test.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 *
 *
 * @author
 * @create 2018-09-05 下午10:09
 **/

public class BIOClient {

    public static void main(String[] args) throws IOException {

        Socket client = new Socket("localhost",6666);

        OutputStream os = client.getOutputStream();

        String str = UUID.randomUUID().toString();

        os.write(str.getBytes());

        os.flush();
        os.close();
        client.close();

    }
}
