package com.xiaowei.design;

import java.util.Random;

/**
 * the skip_list max level can only be MAXLEVEL,
 * when it set over the value of MAXLEVEL,it will be setted default vaule with MAXLEVEL
 * Created by zhangwei on 2017/6/12.
 */

public class skip_list<E extends Comparable<? super E>> {
    private Node<E> head;// ָ��ͷ�ڵ�
    private int level; //���ø������������
    private int len; //����ĳ���
    private Random rand = new Random();
    //��������õ���������ֵ
    private final static int MAXLEVEL = 63;

    public skip_list(int level){
        if(level < 2) level = 2;
        else if(level>MAXLEVEL) level = MAXLEVEL;
        this.level = level;
        head = new Node<>(null,level);
    }

    /**
     * ���һ���½ڵ�
     * @param val
     */
    public void add(E val) {
        //ȷ���½ڵ��level
        int nodeLevel = chooseLevel(this.level);
        //�����½ڵ�
        Node<E> node = new Node<E>(val, level);
        //��ӵ�skip_list��
        Node<E> curNode = head;
        int curLevel = nodeLevel -1;
        while (curLevel >= 0) {
            while (curNode.getNext(curLevel) != null && curNode.getNext(curLevel).getValue().compareTo(val) < 0) {
                curNode = curNode.getNext(curLevel);
            }
            node.setNext(curLevel,curNode.getNext(curLevel));
            curNode.setNext(curLevel,node);
            curLevel--;
        }
        this.len++;
    }

    /**
     * ��������ɾ��һ��Ԫ��
     * @param val
     */
    public void remove(E val) {
        Node<E> curNode = this.head;
        int curLevel = this.level-1;
        boolean isExist = false;
        while (curLevel >= 0) {
            if (curNode.getNext(curLevel) != null && curNode.getNext(curLevel).getValue().compareTo(val) > 0) {
                curLevel--;
            } else if (curNode.getNext(curLevel) != null && curNode.getNext(curLevel).getValue().compareTo(val) < 0) {
                curNode = curNode.getNext(curLevel);
            }else{
                if(curNode.getNext(curLevel)!=null)
                    curNode.setNext(curLevel,curNode.getNext(curLevel).getNext(curLevel));
                else
                    curNode.setNext(curLevel,null);
                curLevel--;
                isExist = true;
            }
        }
        if(isExist) this.len--;
    }

    /**
     * �ж��������Ƿ����ĳ��Ԫ��
     * @param val
     * @return
     */
    public boolean contains(E val) {
        Node<E> curNode = this.head;
        int curLevel = this.level-1;
        while (curLevel >= 0) {
            if (curNode.getNext(curLevel) == null || curNode.getNext(curLevel).getValue().compareTo(val) > 0) {
                curLevel--;
            } else if (curNode.getNext(curLevel).getValue().compareTo(val) < 0) {
                curNode = curNode.getNext(curLevel);
            }else{//found
                return true;
            }
        }
        return false;
    }

    /**
     * ��ȡָ���±�Ԫ�ص�ֵ
     * @param index
     * @return
     */
    public E get(int index) {
        Node<E> curNode = this.head;
        while (index >= 0) {
            curNode = curNode.getNext(0);
            index--;
        }
        return curNode.getValue();
    }

    /**
     * ��ȡ����ĳ���
     * @return
     */
    public int size(){
        return this.len;
    }
    /**
     * ȷ���½ڵ�Ĳ��
     */
    private int chooseLevel(int level) {
        long n = (long) 1 << (level - 1);
        long ranNum;
        if (level < 32) {
            //ranNum��Χ[1,n]
            ranNum = rand.nextInt((int) n) + 1;
        }else{
            ranNum = rand.nextLong();
            if (ranNum < 0) {
                //ranNum��Χ[1,n]
                ranNum = (ranNum + Long.MAX_VALUE)%n + 1;
            } else if (ranNum > n) {
                //ranNum��Χ[1,n]
                ranNum = ranNum % n + 1;
            }
        }
        int ranLevel = 1;
        while (true) {
            n >>= 1;
            if (ranNum > n) return ranLevel;
            ranLevel++;
        }
    }

    //test
    public static void main(String[] args) {
        skip_list<Integer> skipList = new skip_list<>(6);
        skipList.add(1);
        skipList.add(3);
        skipList.add(2);
        skipList.add(4);
        skipList.add(10);
        skipList.add(100);
        skipList.add(100);
        skipList.add(99);
        for(int i=0;i<skipList.size();i++) {
            System.out.println(skipList.get(i)+" ");
        }
        System.out.println();

//        skipList.remove(100);
        skipList.remove(2);
        for(int i=0;i<skipList.size();i++) {
            System.out.println(skipList.get(i)+" ");
        }
        System.out.println();

    }
}

/**
 * ����ڵ㶨��
 */
class Node<E extends Comparable<? super E>>{
    private E value; // �ڵ��ֵ
    private Node[] forward; //ָ����һ���ڵ������
    //���캯��
    public Node( E value,int level_num){
        this.value = value;
        this.forward = new Node[level_num];
    }

    //��ȡ�ڵ��ֵ
    public E getValue(){
        return value;
    }

    public Node getNext(int curLevel) {
        return forward[curLevel];
    }

    public void setNext(int curLevel,Node node) {
        this.forward[curLevel] = node;
    }

    //��ȡ����
    public int levelNum() {
        return forward.length-1;
    }

    public String toString() {
        return "SLV��"+value;
    }
}



