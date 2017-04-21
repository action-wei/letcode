package www.xiaowei.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ��������ֵС��x�Ľڵ��Ƶ�������ֵ���ڵ���x�Ľڵ�֮ǰ����֤ԭ�����˳�򲻱�
 * 
 * @author xiaowei
 */
public class PartitionList {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int len = scan.nextInt();
		int x = scan.nextInt();
		ListNode head = new ListNode(scan.nextInt());
		ListNode pn = head;
		for(int i=1;i<len;i++){
			ListNode tmp = new ListNode(scan.nextInt());
			pn.next = tmp;
			pn= pn.next;
		}
		//����
		ListNode ret = solution(head,x);
		//��ӡ���
		ListNode.printList(ret);
	}
	
	private static ListNode solution(ListNode head,int x){
		if(head==null || head.next==null) return head;
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode cur1 = dummy1,cur2=dummy2;
        while(head!=null){
        	if(head.val < x){
        		cur1.next=head;
        		cur1=cur1.next;
        	}else{
        		cur2.next=head;
        		cur2 = cur2.next;
        	}
        	head = head.next;
        }
        cur2.next=null;//����dummy2��ĩβ�ÿ�
        cur1.next=dummy2.next;
        return dummy1.next;
    }
}
