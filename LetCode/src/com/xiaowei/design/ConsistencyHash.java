package com.xiaowei.design;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性hash算法---分布式hash算法，设计目标是为了解决因特网中的热点（hot spot)问题。
 * 常用场景：分布式集群中，定位资源服务器位置
 * Created by xiaowei on 2017/6/16.
 */
public class ConsistencyHash {
    //待加入Hash环的服务器列表
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};
    // key：服务器hash值，value：服务器名称
    private static SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();

    static{
        for(int i=0;i<servers.length;i++) {
            int hash = getHash(servers[i]);
            System.out.println("["+servers[i]+"]加入集合中，其Hash值为："+hash);
            sortedMap.put(hash, servers[i]);
        }
    }

    /**
     * 计算服务器的hash值
     * @param str
     * @return
     */
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int)2166136261L;
        for(int i=0;i< str.length();i++) {
            hash = (hash^str.charAt(i))*p;
        }
        hash +=hash<<13;
        hash ^=hash>>7;
        hash +=hash<<3;
        hash ^=hash>>17;
        hash +=hash<<5;
        //如果算出来的值为负数则取绝对值
        if(hash <0 ) hash = Math.abs(hash);
        return hash;
    }

    /**
     * 计算路由的服务器的节点
     * @param node
     * @return
     */
    private static String getServer(String node) {
        int hash = getHash(node);
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        //顺时针寻找
        Integer i= subMap.isEmpty() ? sortedMap.firstKey():subMap.firstKey();
        return sortedMap.get(i);
    }

    public static void main(String[] args) {
        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:22", "10.211.0.1:33"};
        for(int i=0;i<nodes.length;i++) {
            System.out.println("["+nodes[i]+"]的hash值为："+getHash(nodes[i])+",被路由的节点["+getServer(nodes[i])+"]");
        }
    }
}
