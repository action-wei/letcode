package www.bupt.nowCoder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/*
 * �ڸ����ַ������ҳ��ַ���"coder",�����ִ�Сд��������"coder"���ַ��������е���Ŀ��������
 * �ýⷨ�ʺϸ����ַ����е������ɿո������
 */
public class FindCoder {
	static TreeMap<Integer,List<String>> map = new TreeMap<Integer,List<String>>();
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//�����ַ�������n
		int n = scan.nextInt();
		String A[] = new String[n];
		scan.nextLine();
		//����n���ַ���
		for(int i=0;i<n;i++){
			String str = scan.nextLine();
			A[i] = str;
		}
		List<String> ret = findWords(A,n);
		printArr(ret);
	}
	private static void printArr(List<String> a) {
		for(int i=0;i<a.size();i++){
			System.out.println(a.get(i));
		}
		
	}
	public static List<String> findWords(String[] A,int n){
		List<String> arr = new ArrayList<String>();
		SortByCoder(A,n);
		Iterator<Integer> iterator = map.descendingKeySet().descendingIterator();
		
		while(iterator.hasNext()){
			int key = iterator.next();
			int len = map.get(key).size();
			for(int i=0;i<len;i++){
				arr.add(map.get(key).get(i));
			}
		}
		return arr;
		
	}
	
	public static void SortByCoder(String[] A,int n){
		for(int i=0;i<n;i++){
			countNumber(A[i]);
		}
	}
	
	public static void countNumber(String str){
		String tem = str.toLowerCase();
		String[]  arr = tem.split(" ");
		int count = 0;
		for(int i=0;i<arr.length;i++){
			if(arr[i].equals("coder")) count++;
		}
		if(count>0){
			if(map.containsKey(count)){
				//���map���Ѿ����ڸø�����key,��ֱ�ӽ��ַ�������value��������
				map.get(count).add(str);
			}else{
				List<String> list = new ArrayList<String>();
				list.add(str);
				map.put(count,list);
			}
		}
	}

	
}
