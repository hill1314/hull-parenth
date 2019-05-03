package com.hull.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 *
 *
 * @author
 * @create 2018-09-05 下午10:32
 **/

public class NIOClient {
    private InetSocketAddress serverAdrress = new InetSocketAddress("localhost",8888);
    private Selector selector = null;
    private SocketChannel client = null;

    private Charset charset = Charset.forName("UTF-8");

    public NIOClient() throws IOException {
        client = SocketChannel.open(serverAdrress);
        client.configureBlocking(false);

        //开门接客
        selector = Selector.open();
        client.register(selector, SelectionKey.OP_READ);

    }

    private void request() {
        //开辟一个新线程从服务器端读数据
        new Reader().start();
        //开辟一个新线程往服务器端写数据
        new Writer().start();
    }

    private class Writer extends Thread{
        @Override
        public void run() {
            try{
                //在主线程中 从键盘读取数据输入到服务器端
                Scanner scan = new Scanner(System.in);
                while(scan.hasNextLine()){
                    String line = scan.nextLine();
                    if("".equals(line)) continue; //不允许发空消息
//                    if("".equals(nickName)) {
//                        nickName = line;
//                        line = nickName + USER_CONTENT_SPILIT;
//                    } else {
//                        line = nickName + USER_CONTENT_SPILIT + line;
//                    }
//		            client.register(selector, SelectionKey.OP_WRITE);
                    client.write(charset.encode(line));//client既能写也能读，这边是写
                }
                scan.close();
            }catch(Exception e){

            }
        }
    }

    private class Reader extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    int count = selector.select();
                    if(count==0) continue;
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterable = selectionKeys.iterator();
                    while (iterable.hasNext()){
                        SelectionKey key = iterable.next();
                        process(key);
                        iterable.remove();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void process(SelectionKey key) throws IOException {
            if(key.isReadable()){
                SocketChannel channel = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                String content = "";
                if(channel.read(buffer)>0){
                    buffer.flip();
                    content += charset.decode(buffer);
                }
                System.out.println(content);
                key.interestOps(SelectionKey.OP_READ);
            }
        }
    }



    public static void main(String[] args) throws IOException {
        new NIOClient().request();
    }


}
