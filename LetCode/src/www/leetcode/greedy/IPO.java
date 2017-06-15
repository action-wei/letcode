package www.leetcode.greedy;

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
 * @author leetcode
 *
 */
public class IPO {
	public static void main(String[] args) {
		int k=3, W=0, Profits[]={1,2,2}, Capital[]={0,1,1};
		int ret = findMaximizedCapital(k, W, Profits, Capital);
		System.out.println(ret);
	}
	
	
	public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital){
		//?????
		class pair{
			int capital;
			int  profit;
			pair(int c,int p){
				this.capital = c;
				this.profit = p;
			}
			
					
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
		PriorityQueue<Integer> low = new PriorityQueue<Integer>(size,new Comparator<Integer>(){

			@Override
			public int compare(Integer a, Integer b) {
				if(a>b) return -1;
				if(a<b) return 1;
				return 0;
			}
			
		});
		Set<pair> set = new HashSet<pair>();
		for(int i=0;i<Capital.length;i++){
			if(Capital[i]<=W) low.add(Profits[i]);
			else set.add(new pair(Capital[i],Profits[i]));
		}
		while(k-->0 && !low.isEmpty()){
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
