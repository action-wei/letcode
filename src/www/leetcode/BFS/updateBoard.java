package www.leetcode.BFS;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode  529
 * Created by zhangwei84 on 2017/7/14.
 */
public class updateBoard {
  public static char[][] solution(char[][] board, int[] click) {
    int nr = board.length;
    int nc = board[0].length;
    int row = 0;
    int col = 0;
    Queue<int[]> queue = new LinkedList<int[]>();
    queue.add(click);
    while (!queue.isEmpty()) {
      List<int[]> list = new LinkedList<int[]>();
      int[] it = queue.poll();
      row = it[0];
      col = it[1];
      if (board[row][col] == 'M') {
        board[row][col] = 'X';
        break;
      } else {
        int count = 0;
        for (int i = -1; i < 2; i++) {
          for (int j = -1; j < 2; j++) {
            int r =row + i;
            int c =col + j;
            if (r < 0 || r >= nr || c < 0 || c >= nc) {
              continue;
            }
            if (board[r][c] == 'M') {
              count++;
            }
            if (board[r][c] == 'E') {
              list.add(new int[]{r, c});
            }
          }
        }
        if(count>0){
          board[row][col] = (char)(count+'0');
        }else{
          for (int[] arr : list) {
            board[arr[0]][arr[1]] = 'B';
            queue.add(new int[]{arr[0], arr[1]});
          }
        }
      }
    }
    return board;
  }

  public static void main(String[] args) {
    char[][] board = {{'E', 'E', 'E', 'E', 'E'},
        {'E', 'E', 'M', 'E', 'E'},
        {'E', 'E', 'E', 'E', 'E'},
        {'E', 'E', 'E', 'E', 'E'}};

    int[] click = {3, 0};
    char[][] result = solution(board, click);
    for (char[] it : result) {
      for (char c : it) {
        System.out.print(c+" ");
      }
      System.out.println();
    }
  }
}
