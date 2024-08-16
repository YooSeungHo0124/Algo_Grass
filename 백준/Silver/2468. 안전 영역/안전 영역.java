import java.util.*;

public class Main {

	static int[][] map;
	static boolean[][] visit;

	static int N;
	static int max=0;
	static int water_max = 0;
	static int K=0;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int input = s.nextInt();
				map[i][j] = input;
				water_max = Math.max(water_max, input); // 물 최대 높이에 사용
			}
		}
		
//		System.out.println(water_max);

		for (int k = 0; k <= water_max; k++) { // 높이는 1이상 100이하의 정수
			visit = new boolean[N][N];  // true, false 구분
			K = k;
			int cnt=0;
			for (int i=0; i<N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j]>k && !visit[i][j]) {
						dfs(i,j);
						cnt++;
					}
				}
			}
			
			max= Math.max(max, cnt);
		}
		
		System.out.println(max);
		
	}
	
	static void dfs(int x, int y) {
		visit[x][y]=true;
		
		for (int k=0; k<4; k++) {
			int nr = x+dr[k];
			int nc = y+dc[k];
			if (0<=nr && nr<N && 0<=nc && nc<N 
					&& map[nr][nc]>K && !visit[nr][nc]) {
				dfs(nr,nc);
			}
		}
	}
	

}
