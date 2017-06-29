package com.xiaowei.design;

import java.util.HashMap;

/**
 * Created by xiaowei on 2017/6/29.
 */
public class LRU<Tkey,Tvalue> {
    int capacity;//����
    HashMap<Tkey,DoubleLinkedListNode<Tkey,Tvalue>> map = new HashMap<>();

    DoubleLinkedListNode<Tkey,Tvalue> head; //�����ͷ�ڵ�
    DoubleLinkedListNode<Tkey,Tvalue> end; //�����β�ڵ�

    public LRU(int capacity){
        this.capacity = capacity;
    }

    //��ȡ����
    public Tvalue get(Tkey key){
        if(map.containsKey(key)){
            //�����д��ڼ�¼
            DoubleLinkedListNode<Tkey,Tvalue> node = map.get(key);
            remove(node);
            setHead(node);
            return node.value;
        }
        return null;
    }

    //�Ƴ�ĳ������
    public void remove(DoubleLinkedListNode<Tkey,Tvalue> node){
        if (node.pre != null) {
            node.pre.next = node.next;
        }else{
            head = node.next;
        }

        if (node.next != null) {
            node.next.pre = node.pre;
        }else{
            end = node.pre;
        }
    }
    //���ڵ�����ͷ��
    public void setHead(DoubleLinkedListNode<Tkey,Tvalue> node){
        node.next = head;
        node.pre = null;
        if(head!=null){
            head.pre = node;
        }
        head = node;
        //��������ӵ�һ��Ԫ��ʱ�������head��end��Ϊnull
        if (end == null) {
            end = head;
        }
    }

    //���û���ֵ
    public void set(Tkey key,Tvalue value) {
        if(map.containsKey(key)) {
            DoubleLinkedListNode<Tkey, Tvalue> old_node = map.get(key);
            old_node.value = value;
            remove(old_node);
            setHead(old_node);
        }else{
            DoubleLinkedListNode<Tkey, Tvalue> new_node = new DoubleLinkedListNode<>(key, value);
            if(map.size()>capacity){
                map.remove(end);
                setHead(new_node);
            }else{
                setHead(new_node);
            }
            map.put(key, new_node);
        }
    }
}

//˫������ڵ�
class DoubleLinkedListNode<Tkey,Tvalue>{
    Tkey key;
    Tvalue value;
    DoubleLinkedListNode<Tkey,Tvalue> pre;
    DoubleLinkedListNode<Tkey,Tvalue> next;

    public DoubleLinkedListNode(Tkey key,Tvalue value){
        this.key = key;
        this.value = value;
    }
}
