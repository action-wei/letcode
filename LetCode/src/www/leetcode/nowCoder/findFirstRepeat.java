package www.leetcode.nowCoder;

import java.util.HashSet;
import java.util.Scanner;


/**
 * Ѱ�������е�һ���ظ�����
 */
public class findFirstRepeat {
	public static char findFirstRepeatChar(String A, int n) {
        HashSet<Character> set = new HashSet<Character>(n+1);
        char ch = ' ';
        for(int i=0;i<n;i++){
        	if(set.contains(A.charAt(i))){
        		ch = A.charAt(i);
        		break;
        	}else{
        		set.add(A.charAt(i));
        	}
        }
        return ch;
    }
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		int n = scan.nextInt();
		char ch = findFirstRepeatChar(str, n);
		System.out.println(ch);
	}
	
}
