import java.util.*;

public class Solution {
	static class edge {
		int from, to;

		public edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}

	static int N, M;
	static int[] parents;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int test_case = s.nextInt();
		int result_cnt = 1;

		while (test_case > 0) {
			N = s.nextInt();
			M = s.nextInt();

			edge[] edgeList = new edge[M];
			for (int i = 0; i < M; i++) {
				edgeList[i] = new edge(s.nextInt(), s.nextInt());
			}

			make();
			// 결과를 위한 set 만들기
			Set<Integer> hashSet = new HashSet<>();
			for (int i = 0; i < M; i++) {
				for (edge e : edgeList) {
					if (!union(e.from, e.to)) {
//						hashSet.add(parents[e.from]);
//						hashSet.add(parents[e.to]);
						continue;
					}
				}
			}
			
			for (int i=1; i<parents.length; i++) {
				hashSet.add(parents[i]);
			}
			
			System.out.println("#"+result_cnt+" "+(hashSet.size()));
			
			test_case--;
			result_cnt++;
		}
		s.close();
	}

	static void make() {
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
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

		if (xRoot == yRoot)
			return false;
		parents[yRoot] = xRoot;
		return true;
	}
}
