package www.leetcode.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 *
 * Created by zhangwei84 on 2017/7/10.
 */
public class updateMatrix {

  public int[][] solution(int[][] matrix) {
    return bfs(matrix);
  }

    /**
     * 解题思路：bfs遍历
     * At beginning, set cell value to Integer.MAX_VALUE if it is not 0.
     * If newly calculated distance >= current distance, then we don't need to explore that cell again.
     * @param matrix
     * @return
     */
  public int[][] bfs(int[][] matrix) {
      int nrow = matrix.length;
      int ncol = matrix[0].length;

      //广度遍历使用的队列
      Queue<int[]> queue = new LinkedList<>();

      for (int i = 0; i < nrow; i++) {
          for (int j = 0; j < ncol; j++) {
              if (matrix[i][j] == 0) {
                  queue.add(new int[]{i, j});
              } else {
                  matrix[i][j] = Integer.MAX_VALUE;
              }
          }
      }


      //遍历方向，上右下左
      int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

      while (!queue.isEmpty()) {
          int[] cell = queue.poll();

          //开始遍历该点上右下左
          for (int[] d : direction) {
              int r = cell[0] + d[0];
              int c = cell[1] + d[1];
              if (r < 0 || r >=nrow || c < 0 || c >=ncol || matrix[r][c] <= matrix[cell[0]][cell[1]] + 1) continue;
              queue.add(new int[]{r, c});
              matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
          }
      }
      return matrix;
  }

  public static void main(String[] args) {
    int matrix[][] = {
        {0, 0 ,0},
        {0, 1 ,0},
        {1, 1 ,1}};

    updateMatrix um = new updateMatrix();
    int[][] ret = um.solution(matrix);
    for(int i=0;i<matrix.length;i++) {
      for(int j=0;j<matrix[0].length;j++) {
        System.out.print(ret[i][j]+ " ");
      }
      System.out.println("/n");
    }
  }

}
