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
    int height = matrix.length;
    int width = matrix[0].length;
    int[][] result = new int[height][width];

    for(int i=0;i<height;i++) {
      for(int j=0;j<width;j++) {
        int ret = bfs(matrix, i,j);
        result[i][j] = ret;
      }
    }
    return result;
  }

  public int bfs(int[][] matrix, int i, int j) {
    if (matrix[i][j] == 0) {
      return 0;
    }
    //初始化距离为0
    int distance = 0;
    //广度遍历使用的字符串
    Queue<point> queue = new LinkedList<point>();
    //遍历方向，上右下左
    int[][] direction = {{-1,0},{0,1},{1,0},{0,-1}};

    queue.add(new point(i, j));

    while (!queue.isEmpty()) {
      point  po = queue.poll();
      int x = po.getX();
      int y = po.getY();

      //distance+1
      distance++;

      //开始遍历该点点上右下左
      //向上方向
      if(validate(matrix,x, y,direction[0])){
        int xx = x+direction[0][0];
        int yy = y+direction[0][1];
        if(matrix[xx][yy]==0){
          return distance;
        }else{
          queue.add(new point(xx, yy));
        }
      }
      //向右方向
      if (validate(matrix, x, y, direction[1])) {
        int xx = x+direction[1][0];
        int yy = y+direction[1][1];
        if (matrix[xx][yy]==0){
          return distance;
        }else{
          queue.add(new point(xx, yy));
        }
      }
      //向下方向
      if (validate(matrix, x, y, direction[2])) {
        int xx = x+direction[2][0];
        int yy = y+direction[2][1];
        if (matrix[xx][yy] == 0) {
          return distance;
        }else{
          queue.add(new point(xx, yy));
        }

      }
      //向左方向
      if (validate(matrix, x, y, direction[3])) {
        int xx = x+direction[3][0];
        int yy = y+direction[3][1];
        if (matrix[xx][yy] == 0) {
          return distance;
        }else{
          queue.add(new point(xx, yy));
        }

      }

    }
    return distance;
  }

  /**
   * 判断下一个点是否存在
   * @param matrix
   * @param i
   * @param j
   * @param direction
   * @return
   */
  public boolean validate(int[][] matrix, int i, int j,int[] direction) {
    int height = matrix.length;
    int width = matrix[0].length;
    if (0 <= i + direction[0] && i + direction[0] < height && 0 <= j + direction[1]
        && j + direction[1] < width) {
      return true;
    }else{
      return false;
    }
  }

  public class point{
    private int x;
    private int y;

    public point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public void setX(int x) {
      this.x = x;
    }

    public int getY() {
      return y;
    }

    public void setY(int y) {
      this.y = y;
    }
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
