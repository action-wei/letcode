package com.xiaowei.offer;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * problem 65: 最大窗口
 * Created by zhangwei84 on 2017/7/24.
 */
public class MaxWindows {

  public ArrayList<Integer> solution(int[] num,int size){
    ArrayList<Integer> result = new ArrayList<Integer>();
    if(size <= 0 || num==null || num.length==0) return result;
    //边界判断
    if(num.length >= size ){
      //使用双向链表保存最大值
      LinkedList<Integer> deque = new LinkedList<Integer>();
      for(int i=0;i<size;i++){
        while(!deque.isEmpty() && num[i]>deque.getLast()){
          deque.pollLast();
        }
        //尾端插入
        deque.addLast(i);
      }

      for(int i=size;i<num.length;i++) {
        //保存当前窗口最大值
        result.add(num[deque.getFirst()]);
        //如果下一个数比尾端值大，则先删除小值，再加入该值
        while(!deque.isEmpty() && num[i] > deque.getLast()){
          deque.pollLast();
        }
        //如果之前最大值已经滑出窗口，则删除最大值
        if(!deque.isEmpty() && deque.getFirst() <= i-size){
          deque.pollFirst();
        }
        //尾端加入该值
        deque.add(i);
      }
      result.add(num[deque.getFirst()]);

    }else{
      int max = Integer.MIN_VALUE;
      for(int i=0;i<num.length;i++){
        if(num[i] > max) max = num[i];
      }
      result.add(max);
    }
    return result;
  }

}
