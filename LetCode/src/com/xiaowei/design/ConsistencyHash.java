package com.xiaowei.design;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * һ����hash�㷨---�ֲ�ʽhash�㷨�����Ŀ����Ϊ�˽���������е��ȵ㣨hot spot)���⡣
 * ���ó������ֲ�ʽ��Ⱥ�У���λ��Դ������λ��
 * Created by xiaowei on 2017/6/16.
 */
public class ConsistencyHash {
    //������Hash���ķ������б�
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};
    // key��������hashֵ��value������������
    private static SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();

    static{
        for(int i=0;i<servers.length;i++) {
            int hash = getHash(servers[i]);
            System.out.println("["+servers[i]+"]���뼯���У���HashֵΪ��"+hash);
            sortedMap.put(hash, servers[i]);
        }
    }

    /**
     * �����������hashֵ
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
        //����������ֵΪ������ȡ����ֵ
        if(hash <0 ) hash = Math.abs(hash);
        return hash;
    }

    /**
     * ����·�ɵķ������Ľڵ�
     * @param node
     * @return
     */
    private static String getServer(String node) {
        int hash = getHash(node);
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        //˳ʱ��Ѱ��
        Integer i= subMap.isEmpty() ? sortedMap.firstKey():subMap.firstKey();
        return sortedMap.get(i);
    }

    public static void main(String[] args) {
        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:22", "10.211.0.1:33"};
        for(int i=0;i<nodes.length;i++) {
            System.out.println("["+nodes[i]+"]��hashֵΪ��"+getHash(nodes[i])+",��·�ɵĽڵ�["+getServer(nodes[i])+"]");
        }
    }
}
