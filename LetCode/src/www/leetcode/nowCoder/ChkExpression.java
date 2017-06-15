package www.leetcode.nowCoder;

import java.util.Scanner;
import java.util.Stack;

public class ChkExpression {
	 public static boolean chkLegal(String A){
	        Stack<Character>  s = new Stack<Character>();
	        for(int i=0;i<A.length();i++){
	            char ch = A.charAt(i);
	            switch(ch){
	            	case '(':
	            	case '[':
	            	case '{':s.push(A.charAt(i));break;
	                case ')':{
	                	if(s.empty()) return false;
	                    if(!s.empty() && !s.pop().equals('('))return false;
	                    break;
	                }
	                case ']':{
	                	if(s.empty()) return false;
	                    if(!s.empty() && !s.pop().equals('['))return false;
	                    break;
	                }
	                case '}':{
	                	if(s.empty()) return false;
	                    if(!s.empty() && !s.pop().equals('{')) return false;
	                    break;
	                }
	                default:break;
	            }
	        }
	        return s.empty();
	    }
	    public static void main(String[] args){
	        Scanner scan = new Scanner(System.in);
	        String str = scan.nextLine();
	        boolean  ret = chkLegal(str);
	        System.out.println(ret);
	    }
}
