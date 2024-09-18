import java.util.*;

public class Main {
	static int N, M;
	static int[] inDegree;
	static List<int[]>[] graph;
	static int[][] count;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt(); 
		M = s.nextInt(); 

		int[] basic = new int[N + 1]; // 기본 부품 여부를 저장
		inDegree = new int[N + 1];
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int x = s.nextInt(); 
			int y = s.nextInt(); 
			int k = s.nextInt(); 
			graph[x].add(new int[] { y, k });
			inDegree[y]++;
			basic[x] = 1;
		}

		count = new int[N + 1][N + 1];
		topologicalSort();

		
		for (int i = 1; i < N; i++) {
			if (basic[i] == 0) { // 기본 부품만 출력
				System.out.println(i + " " + count[i][N]);
			}
		}
	}

	
	// 위상 정렬
	static void topologicalSort() {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) { // 진입 차수가 0인 노드들 (기본 부품들)
				queue.add(i);
				count[i][i] = 1; // 기본 부품은 자기 자신을 1개 사용
			}
		}

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			for (int[] next : graph[curr]) {
				int nextPart = next[0]; // 다음 부품
				int needed = next[1];   // 필요한 개수

				// 현재 부품을 만들기 위해 필요한 모든 부품 수를 다음 부품으로 전파
				for (int i = 1; i <= N; i++) {
					count[nextPart][i] += count[curr][i] * needed;
				}

				// 진입 차수 감소
				inDegree[nextPart]--;
				if (inDegree[nextPart] == 0) {
					queue.add(nextPart);
				}
			}
		}
	}
}
