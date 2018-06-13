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
    private static SortedMap<Integer, String> hashCircleMap = new TreeMap<Integer, String>();
    //��������
    private static int numReplicas = 5;

    static{
        for(int i=0;i<servers.length;i++) {
            int hash = getHashKey(servers[i]);
            System.out.println("["+servers[i]+"]���뼯���У���HashֵΪ��"+hash);
            hashCircleMap.put(hash, servers[i]);
        }
    }

    /**
     * �����������hashֵ
     * @param str
     * @return
     */
    public static int getHashKey(String str) {
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
    public static String getServer(String node) {
        int hash = getHashKey(node);
        SortedMap<Integer, String> subMap = hashCircleMap.tailMap(hash);
        //˳ʱ��Ѱ��
        Integer i= subMap.isEmpty() ? hashCircleMap.firstKey():subMap.firstKey();
        return hashCircleMap.get(i);
    }

    /**
     * �Ƴ��������ڵ�
     * @return 0:û�нڵ����ɾ�� 1�� ɾ���ɹ�
     */
    public static int rmServer(String node) {
        int hash = getHashKey(node);
        if (hashCircleMap.containsKey(hash)) {
            hashCircleMap.remove(hash);
            return 0;
        }
        return 1;
    }

    public static void main(String[] args) {
        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:22", "10.211.0.1:33"};
        for(int i=0;i<nodes.length;i++) {
            System.out.println("["+nodes[i]+"]��hashֵΪ��"+ getHashKey(nodes[i])+",��·�ɵĽڵ�["+getServer(nodes[i])+"]");
        }
    }
}
