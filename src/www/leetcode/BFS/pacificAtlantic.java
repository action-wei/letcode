package www.leetcode.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 417. Pacific Atlantic Water Flow
 * ����˼·����̫ƽ��ʹ�����ı�Ե��ʼ��������������ܱ����ĵ��¼������Ȼ���󲢼�
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

    //�ȱ���pacific Area
    Queue<int[]> queue = new LinkedList<int[]>();
    //��һ��
    for(int i=0;i<nrow;i++) {
      queue.add(new int[]{i, 0});
    }
    //��һ��
    for(int j=0;j<ncol;j++) {
      queue.add(new int[]{0, j});
    }
    //��pacific��Ե����ʼ�����ܱ���
    bfs(queue, matrix, pacific);

    //����atlantic Area
    //�����һ�м������������
    for(int i=0;i<nrow;i++) {
      queue.add(new int[]{i, ncol - 1});
    }
    //�����һ�м������������
    for(int j=0;j<ncol;j++) {
      queue.add(new int[]{nrow - 1, j});
    }
    //��atlantic��Ե����ʼ�����ܱ���
    bfs(queue, matrix, atlantic);

    //�ҳ�pacific��atlantic �ɱ���������غϵ㣬��������
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
    //���������ĸ�����λ��
    int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    while (!queue.isEmpty()) {
      int[] point = queue.poll();
      //���Ա������ı�ʶΪtrue
      visits[point[0]][point[1]] = true;
      //��ȱ���
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
