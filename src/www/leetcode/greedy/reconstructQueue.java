package www.leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by zhangwei on 2017/6/10.
 */
public class reconstructQueue {
    public static void main(String[] args) {
        int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] ret = solution(people);
        for(int i=0;i<ret.length;i++) {
            System.out.println(ret[i][0]+" "+ret[i][1]);
        }
    }

    public static int[][] solution(int[][] people) {
        if (people==null||people.length == 0 || people[0].length == 0) {
            return new int[0][0];
        }

        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]==o2[0]) return o1[1]-o2[1];
                else
                    return o2[0] - o1[0];
            }
        });

        ArrayList<int[]> list = new ArrayList<>();
        for (int i=0;i<people.length;i++) {
            list.add(people[i][1],new int[]{people[i][0],people[i][1]});
        }

        int[][] ret = new int[people.length][2];
        for(int i=0;i<people.length;i++) {
            ret[i][0] = list.get(i)[0];
            ret[i][1] = list.get(i)[1];
        }
        return ret;
    }
}
