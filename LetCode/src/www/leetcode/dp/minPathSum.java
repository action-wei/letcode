package www.leetcode.dp;

/**
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *
 * note: you can only move either down or right at any point in time
 *
 * analyse: This is a typical DP problem. Suppose the minimum path sum of arriving at point (i, j) is S[i][j],
 * then the state equation is S[i][j] = min(S[i - 1][j], S[i][j - 1]) + grid[i][j].
 *
 * Created by zhangwei on 2017/6/20.
 */
public class minPathSum {
    public static int solution(int grid[][]) {
        int dp[][] = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for(int i=1;i<grid.length;i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int j=1;j<grid[0].length;j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        for(int j=1;j<grid[0].length;j++) {
            for(int i=1;i<grid.length;i++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

}
