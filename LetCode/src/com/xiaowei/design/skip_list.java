package com.xiaowei.design;

/**
 * Created by xiaowei on 2017/6/12.
 */

public class skip_list<valueType> {

    private int level; //���
    private Node<valueType> head;// ָ��ͷ�ڵ�

    private final static int DEFAULT_LEVEL =  3;
    private Node<valueType> nil;  //������־
    private Node<valueType> current; // ��ǰλ��


    /**
     * ����ڵ㶨��
     */
    class Node<valueType>{
        private valueType value; // �ڵ��ֵ
        private Node[] forward ; //ָ����һ���ڵ������

        public Node( valueType value,int level_num){
            this.value = value;
            this.forward = new Node[level_num];
        }
    }

}



