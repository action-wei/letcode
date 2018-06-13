package com.xiaowei.offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * problem 67: 机器人的运动范围
 *
 * Created by zhangwei84 on 2017/7/24.
 */
public class movingCount {

  //解题思路：使用队列实现广度遍历
  public int solution(int threshold, int rows, int cols) {
    int counter = 0;
    Queue<int[]> queue = new LinkedList<int[]>();
    boolean[] visited = new boolean[rows*cols];
    //判断起始点是否满足要求
    if(threshold<0) return 0;
    //加入起始点
    queue.add(new int[]{0, 0});
    visited[0*cols+0] = true;
    counter++;
    //移动方向
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


  //解题思路2：使用递归
  public int solution2(int threshold, int rows, int cols) {
    boolean[] visited = new boolean[rows * cols];//默认初始化为false
    return movingCounterCore(threshold, rows, cols,0, 0, visited);
  }

  public int movingCounterCore(int threshold,int rows,int cols,int x,int y, boolean[] visited){
    //超过边界条件
    if(x<0 || x>=rows || y<0 || y>=cols) return 0;
    //已经访问过
    if(visited[x*cols+y]) return 0;
    //大于制定阈值
    if(!checkValid(x,y,threshold)) return 0;
    //满足遍历条件
    visited[x*cols+y] = true;
    return 1 + movingCounterCore(threshold, rows, cols, x - 1, y, visited) +
        movingCounterCore(threshold, rows, cols, x, y + 1, visited) +
        movingCounterCore(threshold, rows, cols, x + 1, y, visited) +
        movingCounterCore(threshold, rows, cols, x, y - 1, visited);
  }


  /**
   * 根据规则判断机器人是否可以进入该点
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
