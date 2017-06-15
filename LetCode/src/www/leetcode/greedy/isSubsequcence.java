package www.leetcode.greedy;

/**
 * Created by leetcode on 2017/6/12.
 */
public class isSubsequcence {
    public static void main(String[] args) {
        String s = "acb";
        String t = "ahbgdc";
        boolean ret = solution(s, t);
        if (ret) {
            System.out.println("true");
        }else
            System.out.println("false");
    }

    public static boolean solution(String s, String  t) {
        int slen = s.length();
        int tlen = t.length();
        int i=0,j=0;
        for(;i<tlen && j<slen;i++) {
            if (t.charAt(i) == s.charAt(j)) {
                j++;
            }
        }
        if(i<=tlen && j==slen)return true;
        else return false;
    }
}
