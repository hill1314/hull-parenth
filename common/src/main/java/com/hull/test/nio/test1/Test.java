package com.hull.test.nio.test1;

/**
 *
 *
 * @author
 * @create 2019-05-01 09:36
 **/

public class Test {

    public static void main(String[] args) {

        EchoNioServer server = new EchoNioServer(666);
        server.run();

        EchoClient client = new EchoClient("127.0.0.1",666,1024);
        client.run();


    }

}
