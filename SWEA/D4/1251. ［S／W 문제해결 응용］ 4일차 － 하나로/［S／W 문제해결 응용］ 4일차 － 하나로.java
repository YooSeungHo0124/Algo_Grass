import java.util.*;

public class Solution {
	static class edge implements Comparable<edge> {
		double w;
		int from, to;
		@Override
		public String toString() {
			return "edge [w=" + w + ", from=" + from + ", to=" + to + "]";
		}
		public edge( int to,int from,double w) {
			this.to = to;
			this.from = from;
			this.w = w;
		}
		@Override
		public int compareTo(edge o) {
			return Double.compare(this.w,o.w);
		}
		
		
	}

	static int N, edge_idx=0;
	static double E;
	static long result;
	static long[] xlist;
	static long[] ylist;
	static long[][] sel;
	static int[] parents;
	static edge[] edgeList;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		long test_case = s.nextInt();
		long result_cnt = 1;

		while (test_case > 0) {
			N = s.nextInt();
			parents = new int[N];
			xlist = new long[N];
			ylist = new long[N];
			for (int i = 0; i < N; i++) {
				xlist[i] = s.nextInt();
			}
			for (int i = 0; i < N; i++) {
				ylist[i] = s.nextInt();
			}
			E = s.nextDouble();
			
			edgeList = new edge[N*(N-1)/2];
//			System.out.println(edgeList.length);
//			System.out.println(Arrays.toString(edgeList));
			
			
			for (int i=0; i<=N-2; i++) {  // 첫번쨰 자리
				for (int j=i+1; j<=N-1; j++) {  // 두번째 자리
					double w = (Math.pow((xlist[i] - xlist[j]), 2) + Math.pow((ylist[i] - ylist[j]), 2)) * E;
					edgeList[edge_idx++] = new edge(i,j,w);
				}
			}
			
			Arrays.sort(edgeList);
			
			make();
			
			int cnt =0; // 선택된 간선의 수
			double sum = 0; // 가중치의 합
			for (edge e: edgeList) {
				if (!union(e.from, e.to)) continue;
				
				sum += e.w;
				if (++cnt == N-1) break;
			}
			System.out.println("#"+result_cnt+" "+Math.round(sum));
			
			
			test_case--;
			result_cnt++;
			edge_idx =0;
		}
	}


	static void make() {
		for (int i=0; i<N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int x) {
		if (x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
	
	static boolean union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		
		if (xRoot == yRoot) return false;
		
		parents[yRoot] = xRoot;
		return true;
	}
	
}
