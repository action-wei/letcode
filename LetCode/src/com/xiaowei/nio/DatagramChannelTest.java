package com.xiaowei.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created by zhangwei on 2017/7/1.
 */
public class DatagramChannelTest {
    public static void main(String[] args) {

    }

    public void readDemo() throws IOException {
        //Opening a DatagramChannel
        DatagramChannel channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(9999));
        //receive data
        ByteBuffer buffer = ByteBuffer.allocate(128);
        buffer.clear();

        channel.receive(buffer);
    }

    public int writeDemo() throws IOException{
        //opening a datagramChannel
        DatagramChannel channel = DatagramChannel.open();
        //sending data
        String message = "new string to write to file..."+
                System.currentTimeMillis();
        ByteBuffer writeBuffer = ByteBuffer.allocate(48);
        writeBuffer.clear();
        writeBuffer.put(message.getBytes());
        writeBuffer.flip();
        int byteSend = channel.send(writeBuffer, new InetSocketAddress("localhost", 80));
        return byteSend;
    }
}
