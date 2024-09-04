import java.util.*;

public class Main {
	static class edge implements Comparable<edge>{
		int from,to,w;

		public edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(edge o) {
			return this.w-o.w;
		}
	}
	
	static int N,M;
	static int[] parents;
	
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();
		
		edge[] edgeList = new edge[M];
		for (int i=0; i<M; i++) {
			edgeList[i] = new edge(s.nextInt(), s.nextInt(), s.nextInt());
		}
		Arrays.sort(edgeList);
		
		make();
		
		int cnt =0; // 선택된 간선의 수
		int sum = 0; // 가중치의 합
		for (edge e: edgeList) {
			if (!union(e.from, e.to)) continue;
			
			sum += e.w;
			if (++cnt == N-1) break;
		}
		System.out.println(sum);
	}
	
	static void make() {
		parents = new int[N+1];
		for (int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int x) {
		if (x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
	
	static boolean union(int x, int y) {
		int xR = find(x);
		int yR = find(y);
		
		if (xR == yR) return false;
		parents[yR] = xR;
		return true;
	}
	
}
