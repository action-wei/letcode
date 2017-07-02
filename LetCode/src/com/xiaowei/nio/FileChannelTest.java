package com.xiaowei.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by zhangwei on 2017/7/1.
 */
public class FileChannelTest {
    public static void main(String[] args) {
    }

    public void demo() {
        try {
            RandomAccessFile fromFile = new RandomAccessFile("/Users/zhangwei/workspace/TestFile/fromFile.txt", "rw");
            FileChannel fromChannel = fromFile.getChannel();
            RandomAccessFile toFile = new RandomAccessFile("/Users/zhangwei/workspace/TestFile/toFile.txt", "rw");
            FileChannel toChannel = toFile.getChannel();

            long position = 0;
            long count = fromChannel.size();
//            toChannel.transferFrom(fromChannel,position,count);
            fromChannel.transferTo(position,count,toChannel);

        } catch (FileNotFoundException e) {
            System.out.println("there is no file in the system");
        }catch(IOException e){
            System.out.println("io exception");
        }

    }
}
