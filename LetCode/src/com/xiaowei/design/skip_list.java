package com.xiaowei.design;

import java.util.Random;

/**
 * the skip_list max level can only be MAXLEVEL,
 * when it set over the value of MAXLEVEL,it will be setted default vaule with MAXLEVEL
 * Created by zhangwei on 2017/6/12.
 */

public class skip_list<E extends Comparable<? super E>> {
    private Node<E> head;// 指向头节点
    private int level; //设置该链表的最大层数
    private int len; //链表的长度
    private Random rand = new Random();
    //链表可设置的最大层数阈值
    private final static int MAXLEVEL = 63;

    public skip_list(int level){
        if(level < 2) level = 2;
        else if(level>MAXLEVEL) level = MAXLEVEL;
        this.level = level;
        head = new Node<>(null,level);
    }

    /**
     * 添加一个新节点
     * @param val
     */
    public void add(E val) {
        //确定新节点的level
        int nodeLevel = chooseLevel(this.level);
        //创建新节点
        Node<E> node = new Node<E>(val, level);
        //添加到skip_list中
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
     * 从链表中删除一个元素
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
     * 判断链表中是否存在某个元素
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
     * 获取指定下标元素的值
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
     * 获取链表的长度
     * @return
     */
    public int size(){
        return this.len;
    }
    /**
     * 确定新节点的层次
     */
    private int chooseLevel(int level) {
        long n = (long) 1 << (level - 1);
        long ranNum;
        if (level < 32) {
            //ranNum范围[1,n]
            ranNum = rand.nextInt((int) n) + 1;
        }else{
            ranNum = rand.nextLong();
            if (ranNum < 0) {
                //ranNum范围[1,n]
                ranNum = (ranNum + Long.MAX_VALUE)%n + 1;
            } else if (ranNum > n) {
                //ranNum范围[1,n]
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
 * 跳表节点定义
 */
class Node<E extends Comparable<? super E>>{
    private E value; // 节点的值
    private Node[] forward; //指向下一个节点的数组
    //构造函数
    public Node( E value,int level_num){
        this.value = value;
        this.forward = new Node[level_num];
    }

    //获取节点的值
    public E getValue(){
        return value;
    }

    public Node getNext(int curLevel) {
        return forward[curLevel];
    }

    public void setNext(int curLevel,Node node) {
        this.forward[curLevel] = node;
    }

    //获取层数
    public int levelNum() {
        return forward.length-1;
    }

    public String toString() {
        return "SLV："+value;
    }
}



