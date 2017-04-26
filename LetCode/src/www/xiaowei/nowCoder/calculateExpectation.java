package www.xiaowei.nowCoder;

import java.util.Scanner;

/**
 * 计算数学期望值
 * 
 * @author xiaowei
 */
public class calculateExpectation {
	public static void main(String[] args) {
		solution();
	}
	
	private static void solution(){
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		double expectation=0.0;
		for(int i=0;i<n;i++){
			int data = scan.nextInt();
			double p = scan.nextInt();
			expectation+=data*p/100;
		}
		System.out.printf("%.3f",expectation);
	}
}
