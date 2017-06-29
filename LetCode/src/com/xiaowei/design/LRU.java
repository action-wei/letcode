package com.xiaowei.design;

import java.util.HashMap;

/**
 * Created by xiaowei on 2017/6/29.
 */
public class LRU<Tkey,Tvalue> {
    int capacity;//容量
    HashMap<Tkey,DoubleLinkedListNode<Tkey,Tvalue>> map = new HashMap<>();

    DoubleLinkedListNode<Tkey,Tvalue> head; //链表的头节点
    DoubleLinkedListNode<Tkey,Tvalue> end; //链表的尾节点

    public LRU(int capacity){
        this.capacity = capacity;
    }

    //获取缓存
    public Tvalue get(Tkey key){
        if(map.containsKey(key)){
            //缓存中存在记录
            DoubleLinkedListNode<Tkey,Tvalue> node = map.get(key);
            remove(node);
            setHead(node);
            return node.value;
        }
        return null;
    }

    //移除某个缓存
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
    //将节点移至头部
    public void setHead(DoubleLinkedListNode<Tkey,Tvalue> node){
        node.next = head;
        node.pre = null;
        if(head!=null){
            head.pre = node;
        }
        head = node;
        //向链表添加第一个元素时，链表的head和end都为null
        if (end == null) {
            end = head;
        }
    }

    //设置缓存值
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

//双向链表节点
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
