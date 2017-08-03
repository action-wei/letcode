package com.xiaowei.offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * problem 67: �����˵��˶���Χ
 *
 * Created by zhangwei84 on 2017/7/24.
 */
public class movingCount {

  //����˼·��ʹ�ö���ʵ�ֹ�ȱ���
  public int solution(int threshold, int rows, int cols) {
    int counter = 0;
    Queue<int[]> queue = new LinkedList<int[]>();
    boolean[] visited = new boolean[rows*cols];
    //�ж���ʼ���Ƿ�����Ҫ��
    if(threshold<0) return 0;
    //������ʼ��
    queue.add(new int[]{0, 0});
    visited[0*cols+0] = true;
    counter++;
    //�ƶ�����
    int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int px = 0;
    int py = 0;
    while (!queue.isEmpty()) {
      int[] point = queue.poll();
      for(int i=0;i<direction.length;i++) {
        px = point[0] + direction[i][0];
        py = point[1] + direction[i][1];
        if(px<0 || px>=rows || py<0 || py>=cols || visited[px*cols+py]) continue;
        if (checkValid(px, py, threshold)) {
          queue.add(new int[]{px, py});
          counter++;
          visited[px*rows+py]=true;
        }
      }
    }
    return counter;
  }


  //����˼·2��ʹ�õݹ�
  public int solution2(int threshold, int rows, int cols) {
    boolean[] visited = new boolean[rows * cols];//Ĭ�ϳ�ʼ��Ϊfalse
    return movingCounterCore(threshold, rows, cols,0, 0, visited);
  }

  public int movingCounterCore(int threshold,int rows,int cols,int x,int y, boolean[] visited){
    //�����߽�����
    if(x<0 || x>=rows || y<0 || y>=cols) return 0;
    //�Ѿ����ʹ�
    if(visited[x*cols+y]) return 0;
    //�����ƶ���ֵ
    if(!checkValid(x,y,threshold)) return 0;
    //�����������
    visited[x*cols+y] = true;
    return 1 + movingCounterCore(threshold, rows, cols, x - 1, y, visited) +
        movingCounterCore(threshold, rows, cols, x, y + 1, visited) +
        movingCounterCore(threshold, rows, cols, x + 1, y, visited) +
        movingCounterCore(threshold, rows, cols, x, y - 1, visited);
  }


  /**
   * ���ݹ����жϻ������Ƿ���Խ���õ�
   *
   * @param threshold
   * @return
   */
  public boolean checkValid(int x,int y, int threshold){
    int sumx = 0;
    while (x!=0){
      sumx+=x%10;
      x/=10;
    }
    int sumy = 0;
    while(y!=0){
      sumy+=y%10;
      y/=10;
    }
    if(sumx+sumy > threshold) return false;
    else return true;
  }

}
