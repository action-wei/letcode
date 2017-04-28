package www.xiaowei.stack;

import java.util.Stack;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * eg : path = "/home/", => "/home" ,  path = "/a/./b/../../c/", => "/c"
 * 
 * @author xiaowei
 */
public class SimplifyPath {
	public static void main(String[] args) {
		String path = "/";
		String ret = solution(path);
		System.out.println(ret);
	}
	
	public static String solution(String path){
		String array[] = path.split("/");
		Stack<String> stack = new Stack<String>();
		for(String s: array){
			if(s.equals("") || s.equals(".")) continue;
			else if(s.equals("..")){
				if(stack.isEmpty()) return "/";
				stack.pop();
			}else{
				stack.add(s);
			}
		}
		if(stack.isEmpty()) return "/";
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()){
			sb.insert(0, stack.pop());
			sb.insert(0, "/");
		}
		return sb.toString();
	}
}
