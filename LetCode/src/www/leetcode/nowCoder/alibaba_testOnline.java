package www.leetcode.nowCoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 每个任务用一个三元组表示--（任务id，父任务id，执行开销），其中任务id是一个正整数（>0）；
 * 父任务id为0表示根任务，每个作业存在一个唯一的根任务，并且，所有的任务，如果其父任务id不为0，那么必然是一个已经存在的根任务id；
 * 执行开销是一个正整数（>0）
 * 找出开销最大的叶子节点任务，并输出总开销值
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
    //建立森林
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
      // 任务的id从1开始的，而存储时从0开始的，所以关联父节点时，需要处理一下
      forest.get(i).parent = forest.get(parents.get(i)-1);
      forest.get(parents.get(i)).isleaf= false;
    }

    //遍历森林，计算总开销值
//    Map<Integer,Integer> map = new HashMap<>();
    Integer maxValue_id = 0;
    Integer maxValue = 0;
    for(int i=0;i<forest.size();i++){
      if(forest.get(i).isleaf){
        //如果是叶子节点
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
        //只有一个根节点情况
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
