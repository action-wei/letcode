package com.xiaowei.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created by zhangwei on 2017/7/1.
 */
public class PipeTest {
    public static void main(String[] args) {

    }

    public void writeDemo() throws IOException{
        //open a pipe
        Pipe pipe = Pipe.open();
        //write to a pipe
        Pipe.SinkChannel sinkChannel = pipe.sink();
        String newData = "new String to write to file" + System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.put(newData.getBytes());
        buffer.flip();

        while (buffer.hasRemaining()) {
            sinkChannel.write(buffer);
        }

    }

    public void readDemo() throws IOException{
        Pipe pipe = Pipe.open();
        Pipe.SourceChannel  sourceChannel = pipe.source();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytesRead = sourceChannel.read(buffer);
    }
}
