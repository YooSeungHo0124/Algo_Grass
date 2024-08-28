import java.util.*;

public class Main {

	static int K, W, H, result = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][][] visit;

	static int[] hr = { -1, -2, -2, -1, 1, 2, 2, 1 }; // 말 움직임
	static int[] hc = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] mr = { -1, 1, 0, 0 }; // 원숭이 움직임
	static int[] mc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		K = s.nextInt(); // 말 움직임 횟수
		W = s.nextInt(); // 가로
		H = s.nextInt(); // 세로

		visit = new boolean[H][W][K + 1]; // [][][0] : 말 이동 사용 안했을때, [][][K] : K 번 말 이동 사용 했을때
		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = s.nextInt();
			}
		}

		bfs(); // 0,0 에서 시작

		// 도착 못하면 -1 출력, 했다면 최소값 출력
		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}

	}

	static void bfs() {
		PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[2])); // dis 기준으로 오름차순 정렬
		q.offer(new int[] { 0, 0, 0, 0 }); // 초기위치 x:0, y:0, 거리, 말 움직임 0
		visit[0][0][0] = true;

		while (!q.isEmpty()) {
			int[] poll = q.poll();
			int x = poll[0]; // x 좌표
			int y = poll[1]; // y 좌표
			int dis = poll[2]; // 거리
			int horseMove = poll[3]; // 말 움직임 사용 횟수

			if (x == H - 1 && y == W - 1) {
				result = Math.min(result, dis);
				return;
			}
			
			// 원숭이 움직임
			for (int k = 0; k < 4; k++) {
				int mx = x + mr[k];
				int my = y + mc[k];
				
				if (0 <= mx && mx < H && 0 <= my && my < W && !visit[mx][my][horseMove] && map[mx][my] == 0) {
					q.offer(new int[] { mx, my, dis + 1, horseMove }); // dis 추가, horseMove 그대로
					visit[mx][my][horseMove] = true;
				}
			}

			if (horseMove < K) { // 말 움직임으로 이동 할 거라면
				for (int k = 0; k < 8; k++) {
					int hx = x + hr[k];
					int hy = y + hc[k];
					
					if (0 <= hx && hx < H && 0 <= hy && hy < W && !visit[hx][hy][horseMove+1]&& map[hx][hy] == 0) {
						q.offer(new int[] { hx, hy, dis + 1, horseMove + 1 }); // dis 추가, horseMove 추가
						visit[hx][hy][horseMove+1] = true;
					}
				}
			}
			
		}

	}

}
