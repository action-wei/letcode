package www.xiaowei.heap;

/**
 * Created by xiaowei on 2017/6/6.
 */

import java.util.Arrays;
import java.util.Comparator;

/**
 * 452. Minimum Number of Arrows to Burst Balloons
 */
public class findMinArrowShots {
    public static int solution(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0]==b[0]) return a[1] - b[1];
                else return a[0] - b[0];
            }
        });

        int minArrow = 1;
        int pivot = points[0][1];
        for (int[] point : points) {
            int p_begin = point[0];
            if (p_begin > pivot) {
                minArrow++;
                pivot = point[1];
            }else{
                continue;
            }
        }
        return minArrow;
    }

    public static void main(String[] args) {
        int[][] points = {{10,16}, {2,8}, {1,6}, {7,12}};
        int ret = solution(points);
        System.out.println(ret);
    }
}
