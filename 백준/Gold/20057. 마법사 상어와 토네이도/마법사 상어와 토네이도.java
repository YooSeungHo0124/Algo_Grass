
import java.util.Scanner;

public class Main {

	static int N;
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 }; // 좌, 하, 우, 상
	static int[] dy = { -1, 0, 1, 0 };
	static int[][][] sandRatio = { { // 좌로 이동할 때의 모래 퍼짐 비율
			{ 0, 0, 2, 0, 0 },{ 0, 10, 7, 1, 0 }, { 5, 0, 0, 0, 0 }, { 0, 10, 7, 1, 0 }, { 0, 0, 2, 0, 0 } },
			{ // 하로 이동할 때의 모래 퍼짐 비율
					{ 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0 }, { 2, 7, 0, 7, 2 }, { 0, 10, 0, 10, 0 }, { 0, 0, 5, 0, 0 } },
			{ // 우로 이동할 때의 모래 퍼짐 비율
					{ 0, 0, 2, 0, 0 }, { 0, 1, 7, 10, 0 }, { 0, 0, 0, 0, 5 }, { 0, 1, 7, 10, 0 }, { 0, 0, 2, 0, 0 } },
			{ // 상으로 이동할 때의 모래 퍼짐 비율
					{ 0, 0, 5, 0, 0 }, { 0, 10, 0, 10, 0 }, { 2, 7, 0, 7, 2 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 0 } } };

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = scanner.nextInt();
			}
		}

		int r = N / 2, c = N / 2; // 토네이도의 시작 위치
		int totalOutsideSand = 0;

		int step = 1; // 한 방향으로 이동하는 칸 수
		while (true) {
			for (int dir = 0; dir < 4; dir++) {
				for (int s = 0; s < step; s++) {
					r += dx[dir];
					c += dy[dir];
					if (r < 0 || r >= N || c < 0 || c >= N) { // 격자 밖으로 나가면 종료
						System.out.println(totalOutsideSand);
						return;
					}

					int sand = map[r][c];
					map[r][c] = 0;

					int sumSpreadSand = 0;
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							int nr = r + i - 2;
							int nc = c + j - 2;
							int spreadSand = (sand * sandRatio[dir][i][j]) / 100;
							sumSpreadSand += spreadSand;

							if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
								totalOutsideSand += spreadSand;
							} else {
								map[nr][nc] += spreadSand;
							}
						}
					}

					// 알파(α) 위치에 남은 모래
					int alphaR = r + dx[dir];
					int alphaC = c + dy[dir];
					int alphaSand = sand - sumSpreadSand;
					if (alphaR < 0 || alphaR >= N || alphaC < 0 || alphaC >= N) {
						totalOutsideSand += alphaSand;
					} else {
						map[alphaR][alphaC] += alphaSand;
					}
				}

				if (dir == 1 || dir == 3) { // 방향이 하, 상일 때 이동하는 칸 수 증가
					step++;
				}
			}
		}
	}
}
