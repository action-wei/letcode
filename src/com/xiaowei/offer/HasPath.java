package com.xiaowei.offer;


import java.util.Stack;

/**
 * problem 66: �����е�·��
 *
 * ����˼·�����ݷ�
 *
 * Created by zhangwei84 on 2017/7/16.
 */
public class HasPath {

  public static boolean solution(char[][] matrix, String path) {
    //validate
    if (null == matrix) {
      return false;
    }

    int nrow = matrix.length;
    int ncol = 0;
    if (nrow != 0) {
      ncol = matrix[0].length;
    }

    //����һ�����飬��¼ÿ�����ӵķ���״̬
    boolean[][] visit = new boolean[nrow][ncol];
    for (boolean[] array : visit) {
      for (boolean iter : array) {
        iter = false;
      }
    }

    for (int i = 0; i < nrow; i++) {
      for (int j = 0; j < ncol; j++) {
        boolean ret = hasPathCore(matrix, i, j, visit, path, 0);
        if (ret) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * �ݹ����
   */
  public static boolean hasPathCore(char[][] matrix, int x, int y, boolean[][] visit, String path,
      int begin) {
    if (path.length() == begin) {
      return true;
    }
    int nrow = matrix.length;
    int ncol = 0;
    if (nrow != 0) {
      ncol = matrix[0].length;
    }
    //��������
    int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    boolean haspath = false;
    if (x >= 0 && x < nrow && y >= 0 && y < ncol && !visit[x][y] && matrix[x][y] == path
        .charAt(begin)) {
      begin++;
      visit[x][y] = true;
      for (int i = 0; i < direction.length; i++) {
        haspath =
            haspath || hasPathCore(matrix, x + direction[i][0], y + direction[i][1], visit, path,
                begin);
      }

      if (!haspath) {
        begin--;
        visit[x][y] = false;
      }
    }
    return haspath;
  }

  /**
   * -----------------------------������--------------------------------
   **/

  //ʹ��ջʵ��
  public static boolean solution2(char[] matrix, int rows, int cols, char[] path) {
    if (matrix == null || rows <= 0 || cols <= 0 || matrix.length == 0
        || matrix.length != rows * cols || path.length > matrix.length) {
      return false;
    }
    //Ĭ�ϳ�ʼ��Ϊfalse
    boolean[] visit = new boolean[rows * cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (matrix[i * cols + j] == path[0]) {
          boolean ret = dfsWithStack(matrix, i, j, rows, cols, visit, path);
          if (ret) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public static boolean dfsWithStack(char[] matrix, int x, int y, int rows, int cols,
      boolean[] visit, char[] path) {
    //��������
    int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    //ʹ��ջʵ�ֻ��ݣ�int[]��¼�������ݣ�ǰ��������¼�����(x,y),����������¼��ǰ����ʷ���״̬
    Stack<int[]> stack = new Stack<>();
    int curlen = 0;
    stack.add(new int[]{x, y, 0});
    visit[x*cols+y] = true;
    curlen++;
    while (!stack.isEmpty() && stack.size() != path.length) {
      boolean flag = false;
      for (int i = 0; i < direction.length; i++) {
        int[] point = stack.peek();
        int nx = point[0] + direction[i][0];
        int ny = point[1] + direction[i][1];
        int direction_status = point[2];
        if (direction_status > i) {
          continue;
        }
        if (nx < 0 || nx >= rows || ny >= cols || ny < 0 || visit[nx * cols + ny]
            || matrix[nx * cols + ny] != path[curlen]) {
          continue;
        }
        if (matrix[nx * cols + ny] == path[curlen]) {
          curlen++;
          //���µ�ǰ���ӱ�������״̬
          stack.peek()[2]=i;
          //����µĸ���
          stack.add(new int[]{nx, ny, 0});
          visit[nx * cols + ny] = true;
          flag = true;
          break;
        }
      }
      if (!flag) {
        int[] po = stack.pop();
        curlen--;
        visit[po[0] * cols + po[1]] = false;
        //���·���״̬
        if (stack.isEmpty()) {
          break;
        }
        if (stack.peek()[2] < 4) {
          stack.peek()[2]++;
        } else {
          break;
        }
      }
    }
    if (stack.size() == path.length) {
      return true;
    } else {
      return false;
    }
  }


  public static void main(String[] args) {
    //��ʽһ
//    char[][] matrix = {{'a', 'b', 'c', 'e'}, {'s', 'f', 'c', 'e'}, {'a', 'd', 'e', 'e'}};
//    char[][] matrix = {{'a', 'b', 'c', 'e'}};
//    boolean ret = solution(matrix, "abcced");

    //��ʽ��
//    char[] matrix = {'A', 'B', 'C', 'E', 'S', 'F', 'C', 'S', 'A', 'D', 'E', 'E'};
    char[] matrix = {'A', 'B', 'C', 'E', 'H', 'J', 'I', 'G', 'S', 'F', 'C', 'S', 'L', 'O', 'P', 'Q',
        'A', 'D', 'E', 'E', 'M', 'N', 'O', 'E', 'A', 'D', 'I', 'D', 'E', 'J', 'F', 'M', 'V', 'C',
        'E', 'I', 'F', 'G', 'G', 'S'};
//    char[] str = {'A', 'B', 'C', 'C', 'E', 'D'};
    char[] str = {'S','L','H','E','C','C','E','I','D','E','J','F','G','G','F','I','E'};


    boolean ret = solution2(matrix, 5, 8, str);
    if (ret) {
      System.out.println("target found");
    } else {
      System.out.println("target not found");
    }
  }


}
