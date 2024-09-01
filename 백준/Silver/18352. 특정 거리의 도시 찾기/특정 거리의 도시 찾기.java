import java.util.*;

public class Main {

	static class edge {
		int to;
		int w;

		public edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}

	static int N, M, K, X;
	static ArrayList<edge>[] graph;
	static int[] distance;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();
		K = s.nextInt();
		X = s.nextInt();

		graph = new ArrayList[N + 1];
		distance = new int[N + 1];

		// 최단거리 배열 초기화
		for (int i = 0; i <= N; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[X] = 0;

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			graph[s.nextInt()].add(new edge(s.nextInt(), 1)); // 모든 거리 1

		}

		PriorityQueue<edge> pq = new PriorityQueue<>((x, y) -> x.w - y.w);
		pq.offer(new edge(X, 0)); // x부터 시작, x->x 의 weight =0;

		while (!pq.isEmpty()) {
			edge tmp = pq.poll();
			int to = tmp.to;
			int w = tmp.w;

			if (distance[to] < w) {
				continue;
			}

			for (int i = 0; i < graph[to].size(); i++) {
				edge imsi = graph[to].get(i);
				int next = imsi.to;
				int weight = imsi.w;

				if (distance[next] > distance[to] + weight) {
					distance[next] = distance[to] + weight;
					pq.offer(new edge(next, distance[next]));
				}

			}
		}

		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (distance[i] == K) {
				result.add(i);
			}
		}
		if (result.size() > 0) {
			result.sort(Comparator.naturalOrder());
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
		} else {
			System.out.println(-1);
		}

	}
}