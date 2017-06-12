package com.xiaowei.design;

/**
 * Created by xiaowei on 2017/6/12.
 */

public class skip_list<valueType> {

    private int level; //层次
    private Node<valueType> head;// 指向头节点

    private final static int DEFAULT_LEVEL =  3;
    private Node<valueType> nil;  //结束标志
    private Node<valueType> current; // 当前位置


    /**
     * 跳表节点定义
     */
    class Node<valueType>{
        private valueType value; // 节点的值
        private Node[] forward ; //指向下一个节点的数组

        public Node( valueType value,int level_num){
            this.value = value;
            this.forward = new Node[level_num];
        }
    }

}



