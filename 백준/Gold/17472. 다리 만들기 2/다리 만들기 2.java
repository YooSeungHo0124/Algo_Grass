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
		public int compareTo(Edge o) {
			return this.weight - o.weight; 
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}

	static int N, M, num = 1;
	static int[][] map;
	static boolean[][] visit;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[] parents;
	static ArrayList<Edge> edges = new ArrayList<>();

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = s.nextInt();
			}
		}

		// 각 섬마다 노드 번호 매기기
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j] && map[i][j] != 0) {
					bfs(i, j, num);
					num++;
				}
			}
		}

		// 섬 간에 연결된 간선 찾기
		findEdges();

		// 크루스칼 
		parents = new int[num];
		for (int i = 0; i < num; i++) {
			parents[i] = i;  
		}

		Collections.sort(edges);  // 간선들을 가중치 순으로 정렬

		int cnt = 0, totalWeight = 0;
		for (Edge e : edges) {
			if (union(e.from, e.to)) {
				totalWeight += e.weight;
				cnt++;
				if (cnt == num - 2) break;  // 모든 섬을 연결한 경우 종료
			}
		}

		System.out.println(cnt == num - 2 ? totalWeight : -1);  // 연결 불가능 -> -1
	}

	// 섬 간의 간선(거리) 계산
	static void findEdges() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					// 이어진 섬 사방 탐색
					for (int d = 0; d < 4; d++) {
						int x = i, y = j, dist = 0;
						while (true) {
							x += dr[d];
							y += dc[d];
							if (x < 0 || y < 0 || x >= N || y >= M || map[x][y] == map[i][j]) break; // 섬 밖 or 옆이 같은 섬

							if (map[x][y] == 0) {
								dist++;
							} else {
								if (dist > 1) {  // 최소 2칸 이상의 다리만 연결 가능
									edges.add(new Edge(map[i][j], map[x][y], dist));
								}
								break;
							}
						}
					}
				}
			}
		}
	}

	// find
	static int find(int x) {
		if (parents[x] == x) return x;
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

	// 섬 노드 나누기
	static void bfs(int x, int y, int num) {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] { x, y });
		visit[x][y] = true;
		map[x][y] = num;

		while (!dq.isEmpty()) {
			int[] tmp = dq.poll();

			for (int k = 0; k < 4; k++) {
				int nr = tmp[0] + dr[k];
				int nc = tmp[1] + dc[k];

				if (0 <= nr && nr < N && 0 <= nc && nc < M && !visit[nr][nc] && map[nr][nc] != 0) {
					visit[nr][nc] = true;
					map[nr][nc] = num;
					dq.offer(new int[] { nr, nc });
				}
			}
		}
	}
}
