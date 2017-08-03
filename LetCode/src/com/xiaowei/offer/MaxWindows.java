package com.xiaowei.offer;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * problem 65: ��󴰿�
 * Created by zhangwei84 on 2017/7/24.
 */
public class MaxWindows {

  public ArrayList<Integer> solution(int[] num,int size){
    ArrayList<Integer> result = new ArrayList<Integer>();
    if(size <= 0 || num==null || num.length==0) return result;
    //�߽��ж�
    if(num.length >= size ){
      //ʹ��˫�����������ֵ
      LinkedList<Integer> deque = new LinkedList<Integer>();
      for(int i=0;i<size;i++){
        while(!deque.isEmpty() && num[i]>deque.getLast()){
          deque.pollLast();
        }
        //β�˲���
        deque.addLast(i);
      }

      for(int i=size;i<num.length;i++) {
        //���浱ǰ�������ֵ
        result.add(num[deque.getFirst()]);
        //�����һ������β��ֵ������ɾ��Сֵ���ټ����ֵ
        while(!deque.isEmpty() && num[i] > deque.getLast()){
          deque.pollLast();
        }
        //���֮ǰ���ֵ�Ѿ��������ڣ���ɾ�����ֵ
        if(!deque.isEmpty() && deque.getFirst() <= i-size){
          deque.pollFirst();
        }
        //β�˼����ֵ
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
