package com.xiaowei.design;

import java.util.List;
import java.util.PriorityQueue;

/**
 * �ο� http://blog.csdn.net/kimylrong/article/details/17022319
 * Created by zhangwei on 2017/7/17.
 */
public class HuffmanTree {
    private BinaryNode root; //���������ĸ��ڵ�
    private int num; //�������ڵ������

    // huffman���ڵ�
    private class BinaryNode implements Comparable<BinaryNode>{
        String chars; //�ַ�
        int frequence; //�ַ���Ӧ��Ƶ��
        BinaryNode left;
        BinaryNode right;
        BinaryNode parent;

        public BinaryNode(String chars,int frequence, BinaryNode left, BinaryNode right,BinaryNode parent) {
            this.chars = chars;
            this.frequence = frequence;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        @Override
        public int compareTo(BinaryNode o) {
            return this.frequence-o.frequence;
        }
    }

    //������������
    public BinaryNode buildHuffmanTree(List<BinaryNode> list){
        if (list.size() == 1) {
            return list.remove(0);
        }
        PriorityQueue<BinaryNode> queue = new PriorityQueue<>(list);
        while (queue.size() != 1) {
            BinaryNode left = queue.poll();
            BinaryNode right = queue.poll();
            BinaryNode parent = new BinaryNode(null,left.frequence + right.frequence, left, right,null);
            left.parent = parent;
            right.parent = parent;
            queue.add(parent);
        }
        return root=queue.poll();
    }


}


