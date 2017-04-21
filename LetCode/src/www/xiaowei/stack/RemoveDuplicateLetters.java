package www.xiaowei.stack;

import java.util.Arrays;
import java.util.Queue;

/**
 * given a string which only contains lowerletters,remove duplicate letters so that every letter only appear once.
 * you must make sure the result is the smallest lexicographical order among all possible results
 * 
 * ����ַ����б�֤ԭ�ַ������λ��
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
	 *  �㷨˼�룺��ͷ��β�����ַ���������һ�Σ����ַ��ļ�������1,�����ַ�������Ϊ0��˵������û���ظ��ĸ��ַ������ַ���Сֻ���ڸ�λ��
	 *  �Ӷ�ÿ�α������ܽ�������е��ֵ�����С���ַ�����
	 *  
	 *  ʱ�临�Ӷ�ΪO(n2)
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
	 *  �����㷨�ľ���棨ʹ���˵ݹ�ķ�������ʣ�µ��ַ�����
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
	 * Ͱ�����˼�룬�����ϱ����Ҫ��
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
