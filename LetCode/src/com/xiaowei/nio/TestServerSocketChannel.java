package com.xiaowei.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by zhangwei on 2017/6/29.
 */
public class TestServerSocketChannel {
    public static void main(String[] args){

    }

    public void demo(){
        try{
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.socket().bind(new InetSocketAddress(9999));
            serverSocketChannel.configureBlocking(false);

            while(true){
                SocketChannel socketChannel =
                        serverSocketChannel.accept();

                if(socketChannel != null){
                    //do something with socketChannel...
                }
            }
        }catch (IOException e ){
            e.printStackTrace();
        }

    }

}
