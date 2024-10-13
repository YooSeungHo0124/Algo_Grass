import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static int[] parents;
	static ArrayList<Edge> edges = new ArrayList<>();
	static int N, M;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();
		
		if (N==2) {
			System.out.println(0);
			return;
		}
		
		// union을 위한 parents 배열 작성
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		// 입력값 받기
		for (int i = 0; i < M; i++) {
			int from = s.nextInt();
			int to = s.nextInt();
			int weight = s.nextInt();

			edges.add(new Edge(from, to, weight));
		}

		Collections.sort(edges); // 간선들을 가중치 순으로 정렬
		
		int cnt = 0;
		ArrayList<Integer> weights = new ArrayList<>();
		for (Edge e : edges) {
			if (union(e.from, e.to)) {
				weights.add(e.weight);    // 사용된 weight 값들 모음
				cnt++;
				if (cnt == N - 2) break;  // 모든 섬을 연결한 경우 종료
			}
		}
		
		Collections.sort(weights);
		
		int sum = 0; 
		for (int i=0; i<weights.size(); i++) {
			sum += weights.get(i);
		}
		System.out.println(sum); 
	}

	// find
	static int find(int x) {
		if (parents[x] == x)
			return x;
		return parents[x] = find(parents[x]);
	}

	// union 함수
	static boolean union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);

		if (rootX != rootY) {
			parents[rootY] = rootX;
			return true;
		}
		return false;
	}
}
