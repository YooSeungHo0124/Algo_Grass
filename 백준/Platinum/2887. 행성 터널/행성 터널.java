import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge other) {
			return Integer.compare(this.weight, other.weight);
		}
	}

	static int N;
	static int[] parent;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();

		// 좌표값 받기
		int[][] nodes = new int[N][4]; // 0:x, 1:y, 2:z, 3:인덱스
		for (int i = 0; i < N; i++) {
			nodes[i][0] = s.nextInt(); // x
			nodes[i][1] = s.nextInt(); // y
			nodes[i][2] = s.nextInt(); // z
			nodes[i][3] = i; // 인덱스
		}

		// 간선을 최소로 유지하며 연결하기 위해 간선 리스트를 생성
		List<Edge> edges = new ArrayList<>();

		// 각 좌표 축(x, y, z)별로 정렬 후 인접 노드끼리만 간선 추가
		addEdges(edges, nodes, 0); // x 축 기준 간선 추가
		addEdges(edges, nodes, 1); // y 축 기준 간선 추가
		addEdges(edges, nodes, 2); // z 축 기준 간선 추가

		// 간선들을 가중치 순으로 정렬
		Collections.sort(edges);

		// Union-Find 초기화
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		// Kruskal 알고리즘으로 최소 스패닝 트리 계산
		int ans = 0;
		int edgeCount = 0;
		for (Edge edge : edges) {
			if (union(edge.from, edge.to)) {
				ans += edge.weight;
				edgeCount++;
				if (edgeCount == N - 1)
					break;
			}
		}

		System.out.println(ans);
	}

	// 축별 정렬을 이용하여 가장 가까운 노드끼리 간선 추가
	private static void addEdges(List<Edge> edges, int[][] nodes, int coord) {
		// 현재 좌표 축에 따라 노드를 정렬
		Arrays.sort(nodes, Comparator.comparingInt(a -> a[coord]));

		// 인접 노드 간의 최소 간선만 추가
		for (int i = 0; i < N - 1; i++) {
			int from = nodes[i][3]; // 노드의 인덱스
			int to = nodes[i + 1][3]; // 다음 노드의 인덱스
			int weight = Math.abs(nodes[i][coord] - nodes[i + 1][coord]);

			edges.add(new Edge(from, to, weight));
		}
	}

	// Union-Find에서 find 함수
	private static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	// Union-Find에서 union 함수
	private static boolean union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);

		if (rootX != rootY) {
			parent[rootY] = rootX;
			return true;
		}
		return false;
	}
}
