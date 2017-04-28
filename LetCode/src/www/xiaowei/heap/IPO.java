package www.xiaowei.heap;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * pick a list of at most k distinct projects from given projects to maximize your final capital, 
 * and output your final maximized capital.
 * you should disign a strategy from K distinct projects to get max profit;
 * 
 * @author xiaowei
 *
 * 2017年4月28日 下午12:53:20
 */
public class IPO {
	public static void main(String[] args) {
		int k=3, W=0, Profits[]={1,2,2}, Capital[]={0,1,1};
		int ret = findMaximizedCapital(k, W, Profits, Capital);
		System.out.println(ret);
	}
	
	
	/**
	 * 贪心算法  ----基于堆实现（prorityQueue)
	 * @param k
	 * @param W
	 * @param Profits
	 * @param Capital
	 * @return
	 */
	public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital){
		//内部类
		class pair{
			int capital;
			int  profit;
			pair(int c,int p){
				this.capital = c;
				this.profit = p;
			}
			/**
			 *  说明：对于本题，不重写equal和hashCode方法，则使用HashSet时，比较对象时，比较的是对象地址，
			 *  所以即使有值相同的pair(Capital[i],Profits[i])，也可以顺利插入到set中，
			 *  这个正好满足题目的要求。
			 *  举例：如果重写了equal和hashCode方法，比较对象的内容，则出现 W=0，Profits[]={1,2,2}, Capital[]={0,1,1}时，
			 *  第一次划分数据到low和set时，第二个和第三个项目只能有一个被加入到set中，这样就会有问题。
			 */
			
					
/*			@Override
			public boolean equals(Object obj) {
				if(obj==null) return false;
				if(obj==this) return true;
				if(getClass() != obj.getClass()) return false;
				pair p = (pair) obj;
				if(p.capital==this.capital && p.profit==this.profit) return true;
				else return false;
			}

			@Override
			public int hashCode() {
				return this.capital*32 + this.profit;
			}*/
			
		}
		int size = Profits.length;
		//自定义Comparator实现大根堆优先队列
		PriorityQueue<Integer> low = new PriorityQueue<Integer>(size,new Comparator<Integer>(){

			@Override
			public int compare(Integer a, Integer b) {
				if(a>b) return -1;
				if(a<b) return 1;
				return 0;
			}
			
		});
		Set<pair> set = new HashSet<pair>();
		//分段，将比目前资本小于等于的project的收益加入到low，大于目前资本的项目加入set中存放
		for(int i=0;i<Capital.length;i++){
			if(Capital[i]<=W) low.add(Profits[i]);
			else set.add(new pair(Capital[i],Profits[i]));
		}
		while(k-->0 && !low.isEmpty()){
			W+=low.poll(); //每次取可以取到的最大收益
			//收益增加后，更新low中的记录
			Iterator<pair> it = set.iterator();
			while(it.hasNext()){
				pair tmp = it.next();
				if(tmp.capital<=W) {
					low.add(tmp.profit);
//					it.remove();
					set.remove(tmp);
				}
			}
		}
		return W;
	}
}
