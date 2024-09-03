import java.util.*;

public class Solution {
	static int N, cnt = 0, max = Integer.MIN_VALUE, eat = 1,input_max =Integer.MIN_VALUE;
	static int[][] map;
	static boolean[][] visit;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int test_case = s.nextInt();
		int result_cnt = 1;

		while (test_case > 0) {
			N = s.nextInt();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int input = s.nextInt();
					map[i][j] = input;
					
					input_max = Math.max(input_max, input);
				}
			}

			visit = new boolean[N][N];
			for (int e = 0; e <= input_max; e++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (!visit[i][j] && map[i][j] > e) { // 방문 안했고, 못 먹는거
							bfs(i, j, e);
							cnt++;
						}
					}
				}
				max = Math.max(max, cnt);
				// visit 초기화
				for (int k = 0; k < N; k++) {
					Arrays.fill(visit[k], false);
				}
				// cnt 초기화
				cnt = 0;
			}
			System.out.println("#"+result_cnt+" "+max);
			test_case--;
			result_cnt++;
			
			// max 초기화
			max=0;
		}
	}

	static void bfs(int x, int y, int e) {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { x, y, e }); // x,y,먹을수있는크기
		visit[x][y] = true;
		
		while (!dq.isEmpty()) {
			int[] tmp = dq.poll();
			int m = tmp[0];
			int n = tmp[1];
			int eat = tmp[2];

			for (int k = 0; k < 4; k++) {
				int nr = m + dr[k];
				int nc = n + dc[k];

				if (0<=nr && nr<N && 0<=nc && nc<N && map[nr][nc] > eat && !visit[nr][nc]) {
					visit[nr][nc]=true;
					dq.add(new int[] { nr, nc, eat });
				}
			}
		}
	}
}
