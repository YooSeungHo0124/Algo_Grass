import java.util.*;

public class Main {

	static int N, pos_x, pos_y, size = 2, eat = 0, second = 0;
	static int[][] map;
	static boolean[][] visit;

	static int[] dr = { -1, 0, 0, 1 }; // 상 좌 우 하
	static int[] dc = { 0, -1, 1, 0 };

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int input = s.nextInt();
				map[i][j] = input;
				if (input == 9) {
					pos_x = i;
					pos_y = j; // 상어 위치
					map[pos_x][pos_y] = 0; // 9 -> 0
				}
			}
		}

		// 먹을 거 없을 때 까지 찾는 과정
		while (true) {
			int returned_sec = bfs(pos_x, pos_y); // 상어 위치
			if (returned_sec == 0) { // 먹을것 못찾으면 그동안 초 return
				System.out.println(second);
				break;
			}
			second += returned_sec; // 시간 더하기

//			 visit 초기화
			for (int i = 0; i < N; i++) {
				Arrays.fill(visit[i], false);
			}
		}
		
	}

	// 현재 상어위치에서 먹을 거 찾는 과정
	static int bfs(int x, int y) {
		// 어떤 먹이를 먼저 선정 할지 로직 구현 중요!
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
			if (a[2] == b[2]) { // 거리가 같다면
				if (a[0] == b[0]) { // 행이 같다면
					return a[1] - b[1]; // 더 왼쪽에 있는 순서
				}
				return a[0] - b[0]; // 더 위에 있는 순서
			}
			return a[2] - b[2]; // 거리가 가까운 순서
		});
		
		pq.offer(new int[] { x, y, 0 }); // x좌표, 좌표, 초 세기(<->거리)
		visit[x][y] = true;
		
		while (!pq.isEmpty()) {
			
			int[] poll = pq.poll();
			int r = poll[0];
			int c = poll[1];
			int sec = poll[2];
			
			if (0 < map[r][c] && map[r][c] < size) {   // 물고기 < 상어 : 사냥가능
				map[r][c] = 0; // 먹기
				eat++;
				if (size == eat) {
					size++;
					eat = 0;
				} // 크기만큼 먹었다면 다음 size로 레벨업
				pos_x = r; // 상어 좌표 변경
				pos_y = c;
				return sec;
			}
			
			
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] <= size && !visit[nr][nc]) { // 물고기 <= 상어 : 이동가능
					visit[nr][nc] = true;
					pq.offer(new int[] { nr, nc, sec + 1 });
				}
			}
		}
		// 먹을거 없다면
		return 0;
	}
}
