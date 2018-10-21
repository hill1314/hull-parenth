package com.hull.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *
 *
 * @author
 * @create 2018-09-05 下午10:32
 **/

public class NIOServer {
    private Selector selector;

    public NIOServer(int port) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();

        server.bind(new InetSocketAddress(port));
        server.configureBlocking(false);

        selector = Selector.open();

        server.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务启动,port："+port);
    }


    public void listen() throws IOException {
        while (true){
            //遍历到达的 selectKey, 空则返回
            int wait = selector.select();
            if (wait==0)
                continue;

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterable = keys.iterator();
            while(iterable.hasNext()){
                SelectionKey key = iterable.next();
                iterable.remove();
                process(key);
            }

        }
    }

    private void process(SelectionKey key) throws IOException {
        ByteBuffer buffer= ByteBuffer.allocate(1024);
        //判断这个key是否可以接受，刚开始都是可以接受的
        if(key.isAcceptable()){
            //根据key得到代表服务端的通道
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();//接受客户端的请求得到一个代表客户端的Channel
            client.configureBlocking(false);
            client.register(selector,SelectionKey.OP_READ);  //告诉大管家表示接下来可以读取数据了
        }else if(key.isReadable()){
            //从key中得到代表客户端的通道
            SocketChannel client= (SocketChannel) key.channel();
            //读取到哪里去，读到缓冲区中
            int len=client.read(buffer);
            if(len>0){
                buffer.flip();   //锁定
                //从缓冲区中取出数据
                String content=new String(buffer.array(),0,len);
                //打印出结果
                System.out.println(content);
            }
            //此时告诉大管家，可以进行写操作了
            client.register(selector,SelectionKey.OP_WRITE);
            buffer.clear();
        }else if(key.isWritable()){
            SocketChannel client = (SocketChannel)key.channel();
            client.write(buffer.wrap("Hello GuPao!".getBytes()));
            client.close();
        }
    }


    public static void main(String[] args) throws IOException {
        new NIOServer(888).listen();
    }
}
