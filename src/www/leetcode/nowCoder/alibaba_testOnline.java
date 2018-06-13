package www.leetcode.nowCoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ÿ��������һ����Ԫ���ʾ--������id��������id��ִ�п���������������id��һ����������>0����
 * ������idΪ0��ʾ������ÿ����ҵ����һ��Ψһ�ĸ����񣬲��ң����е���������丸����id��Ϊ0����ô��Ȼ��һ���Ѿ����ڵĸ�����id��
 * ִ�п�����һ����������>0��
 * �ҳ���������Ҷ�ӽڵ����񣬲�����ܿ���ֵ
 *
 * Created by zhangwei84 on 2017/8/9.
 */
public class alibaba_testOnline {

  public static int resolve(ArrayList<Integer> ids, ArrayList<Integer> parents, ArrayList<Integer> costs) {

    class TreeNode{
      Integer id;
      Integer cost;
      TreeNode parent;
      boolean isleaf;

      public TreeNode(Integer id, Integer cost){
        this.id = id;
        this.cost = cost;
        parent = null;
        isleaf = true;
      }

    }
    //����ɭ��
    List<TreeNode> forest = new  ArrayList<TreeNode>();
    for(int i=0;i<ids.size();i++) {
      TreeNode node = new TreeNode(ids.get(i),costs.get(i));
      forest.add(node);
    }

    for(int i=0;i<parents.size();i++) {
      if(parents.get(i)==0){
        forest.get(i).isleaf=false;
        continue;
      }
      // �����id��1��ʼ�ģ����洢ʱ��0��ʼ�ģ����Թ������ڵ�ʱ����Ҫ����һ��
      forest.get(i).parent = forest.get(parents.get(i)-1);
      forest.get(parents.get(i)).isleaf= false;
    }

    //����ɭ�֣������ܿ���ֵ
//    Map<Integer,Integer> map = new HashMap<>();
    Integer maxValue_id = 0;
    Integer maxValue = 0;
    for(int i=0;i<forest.size();i++){
      if(forest.get(i).isleaf){
        //�����Ҷ�ӽڵ�
        TreeNode curNode = forest.get(i);
        int sum = curNode.cost;
        while(curNode.parent!=null){
          curNode = curNode.parent;
          sum+=curNode.cost;
        }
        if(sum>maxValue){
          maxValue = sum;
          maxValue_id = forest.get(i).id;
        }
      }
      if(forest.get(i).parent==null){
        //ֻ��һ�����ڵ����
        if(forest.get(i).cost>maxValue){
          maxValue = forest.get(i).cost;
          maxValue_id = forest.get(i).id;
        }
      }
    }
    System.out.println("max_value:"+maxValue);
    System.out.println("max_value_id:"+maxValue_id);
    return maxValue;
  }

  public static void main(String[] args) {
    ArrayList<Integer> ids = new ArrayList<Integer>();
    ArrayList<Integer> parents = new ArrayList<Integer>();
    ArrayList<Integer> costs = new ArrayList<Integer>();
    Scanner scan = new Scanner(System.in);
    for(int i=0;i<4;i++) {
      ids.add(i,scan.nextInt());
    }
    for(int i=0;i<4;i++) {
      parents.add(i, scan.nextInt());
    }
    for(int i=0;i<4;i++) {
      costs.add(i,scan.nextInt());
    }
    int ret = resolve(ids, parents, costs);
    System.out.println(ret);
  }

}
