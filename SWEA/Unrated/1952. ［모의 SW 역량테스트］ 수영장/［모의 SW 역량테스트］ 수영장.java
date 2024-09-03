/*
 * 다익스트라로 풀기
 * */

import java.util.*;
public class Solution {
	static class edge{
		int to;
		int w;
		edge(int to, int w){
			this.to = to;
			this.w = w;
		}
	}
	static int[] distance;
	static int[] cost;
	static int d,m1,m3,y;
	
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		int test_case = s.nextInt();
		int result_cnt =1;
		
		while (test_case>0) {
			d = s.nextInt();
			m1 = s.nextInt();
			m3 = s.nextInt();
			y = s.nextInt();
			cost = new int[13];
			for (int i=1; i<=12; i++) {
				cost[i] = s.nextInt();
			}
			
			distance = new int[13];
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[0] = 0; // 0달은 0원
			
			
			PriorityQueue<edge> pq = new PriorityQueue<>((a,b)->(a.w-b.w));
			pq.offer(new edge(0,0));  // 첫달 요금
			
			while(!pq.isEmpty()) {
				edge tmp = pq.poll();
				int to = tmp.to;
				int w = tmp.w;
				
				if (distance[to] < w) {continue;}
				
				// 1달 요금 pq offer
				// Math.min(데일리, m1 ) -> 다음달로 
				if (to < 12) {
					int next = to+1;
					if ( distance[next] > w + Math.min(cost[to + 1] * d, m1)) {
						 distance[next] = w + Math.min(cost[to + 1] * d, m1);
						 pq.offer(new edge(next, distance[next]));
					}
					
					
				}
				
				// 3달 요금 pq offer
				// 12월에도 3달 요금제 사용 할 수도 있음
				if (to <= 12) {
					int next = to+3>12 ? 12:to+3;
					if ( distance[next] > w+m3) {
						distance[next] = w+m3;
						pq.offer(new edge(next, distance[next]));
					}
				}
			}
			
			// 1년 요금제 비교
			int result = Math.min(distance[12], y);
			System.out.println("#"+result_cnt+" "+result);
		
			test_case--;
			result_cnt++;
		}
		s.close();
	}
}
