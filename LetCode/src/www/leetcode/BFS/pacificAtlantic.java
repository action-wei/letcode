package www.leetcode.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 417. Pacific Atlantic Water Flow
 * 解题思路：从太平洋和大西洋的边缘开始向四面遍历，把能遍历的点记录下来，然后求并集
 *
 * Created by zhangwei84 on 2017/7/14.
 */
public class pacificAtlantic {
  public static  List<int[]> solution(int[][] matrix) {
    List<int[]> result = new ArrayList<int[]>();
    if(matrix==null) return result;
    int nrow = matrix.length;
    if(nrow==0){
      for(int i=0;i<nrow;i++){
        result.add(new int[]{i,0});
      }
      return result;
    }
    int ncol = matrix[0].length;

    boolean[][] pacific =new boolean[nrow][ncol];
    boolean[][] atlantic = new boolean[nrow][ncol];
    for(int i=0;i<nrow;i++) {
      for(int j=0;j<ncol;j++) {
        pacific[i][j] = false;
        atlantic[i][j] = false;
      }
    }

    //先遍历pacific Area
    Queue<int[]> queue = new LinkedList<int[]>();
    //第一列
    for(int i=0;i<nrow;i++) {
      queue.add(new int[]{i, 0});
    }
    //第一行
    for(int j=0;j<ncol;j++) {
      queue.add(new int[]{0, j});
    }
    //从pacific边缘区域开始向四周遍历
    bfs(queue, matrix, pacific);

    //遍历atlantic Area
    //将最后一列加入待遍历队列
    for(int i=0;i<nrow;i++) {
      queue.add(new int[]{i, ncol - 1});
    }
    //将最后一行加入待遍历队列
    for(int j=0;j<ncol;j++) {
      queue.add(new int[]{nrow - 1, j});
    }
    //从atlantic边缘区域开始向四周遍历
    bfs(queue, matrix, atlantic);

    //找出pacific和atlantic 可遍历区域的重合点，即所求结果
    for(int i=0;i<nrow;i++) {
      for(int j=0;j<ncol;j++) {
        if (pacific[i][j] && atlantic[i][j]) {
          result.add(new int[]{i, j});
        }
      }
    }
    return result;
  }

  public static void bfs(Queue<int[]> queue, int[][] matrix,boolean[][] visits) {
    int nrow = matrix.length;
    int ncol = matrix[0].length;
    //上右下左四个方向位移
    int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    while (!queue.isEmpty()) {
      int[] point = queue.poll();
      //可以遍历到的标识为true
      visits[point[0]][point[1]] = true;
      //广度遍历
      for (int i = 0; i < 4; i++) {
        int x = point[0] + direction[i][0];
        int y = point[1] + direction[i][1];
        if (x < 0 || x >= nrow || y < 0 || y >= ncol || matrix[x][y] < matrix[point[0]][point[1]]) {
          continue;
        }
        if (matrix[x][y] >= matrix[point[0]][point[1]] && !visits[x][y]) {
          queue.add(new int[]{x, y});
        }
      }
    }
  }

  public static void main(String[] args) {
    int[][] matrix = {{1, 2, 2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
    List<int[]> ret = solution(matrix);
    for (int[] it : ret) {
      System.out.print("("+it[0]+","+it[1]+") ");
    }
  }

}
