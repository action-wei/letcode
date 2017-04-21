package www.xiaowei.stack;

import java.util.Arrays;
import java.util.Queue;

/**
 * given a string which only contains lowerletters,remove duplicate letters so that every letter only appear once.
 * you must make sure the result is the smallest lexicographical order among all possible results
 * 
 * 结果字符串中保证原字符的相对位置
 * 
 * @author xiaowei
 */
public class RemoveDuplicateLetters {
	public static void main(String[] args) {
		String s = "bcabc";
		String ret = solution_better(s);
		System.out.println(ret);
	}
	
	
	/**
	 *  算法思想：从头到尾遍历字符串，访问一次，该字符的计数减少1,当该字符计数减为0，说明后面没有重复的该字符，该字符最小只能在该位置
	 *  从而每次遍历总能将结果集中的字典序最小的字符找着
	 *  
	 *  时间复杂度为O(n2)
	 * @param s
	 * @return
	 */
	private static String solution_better(String s) {
        if( s.length()<2 )
            return s;
        int[] count = new int[26];
        StringBuilder result = new StringBuilder();
        
        while( s.length()>0 ){
            for( int i=0; i<s.length(); i++ )
                count[ s.charAt(i)-'a' ]++; 
            
            int pos=0; 
            for( int i=0; i<s.length(); i++ ){
                if( s.charAt(i) < s.charAt(pos) )  //always choose the left lexically smallest char 
                    pos = i;
                
                //if a char will not appear in the following sequence, stop here, otherwise the chars of the result may not be in correct order,
                //e.g. "eeffga", 'a' will be selected first;  we must maintain the relative order of chars in the result according to the input
                if( --count[s.charAt(i)-'a'] == 0 )
                    break;
            }
            
            result.append( s.charAt(pos) );
            s = s.substring(pos + 1).replaceAll("" + s.charAt(pos), "");
            Arrays.fill(count, 0);
        }
        
        return result.toString(); 
    }
	/**
	 *  上面算法的精简版（使用了递归的方法处理剩下的字符串）
	 * @param s
	 * @return
	 */
	public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        int pos = 0; // the position for the smallest s[i]
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            if (--cnt[s.charAt(i) - 'a'] == 0) break;
        }
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }
	
	
	/**
	 * 桶排序的思想，不符合本题的要求
	 * @param s
	 * @return
	 */
	private static String solution(String s){
		StringBuilder sb = new StringBuilder();
		int arr[] = new int[26];
		for(int i=0;i<s.length();i++){
			arr[s.charAt(i)-'a']++;
		}
		for(int i=0;i<26;i++){
			if(arr[i]>0){
				sb.append((char)(i+'a'));
			}
		}
		return sb.toString();
	}
}
