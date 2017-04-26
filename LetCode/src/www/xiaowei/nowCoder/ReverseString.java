package www.xiaowei.nowCoder;
/**
 * ·­×ªÒ»¸ö×Ö·û´®
 * 
 * @author xiaowei
 */
public class ReverseString {
	public static void main(String[] args) {
		String str = "this is a reversed string";
		String ret = solution(str);
		System.out.println(ret);
	}
	
	public static String solution(String str){
		StringBuilder sb = new StringBuilder(str);
		int left = 0 ,right = str.length()-1;
		while(left<right){
			char tmp = sb.charAt(left);
			sb.setCharAt(left, sb.charAt(right));
			sb.setCharAt(right, tmp);
			left++;right--;
		}
		return sb.toString();
	}
}
