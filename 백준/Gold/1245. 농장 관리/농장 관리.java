import java.util.*;
public class Main {
	
	static int[][] map;
	static boolean[][] visit;
	
	static int N,M;
	static int[] dr = {-1,-1,-1,0,1,1,1,0};
	static int[] dc = {-1,0,1,1,1,0,-1,-1};
	
	static boolean countable=false;
	static int cnt=0;
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N =s.nextInt();
		M =s.nextInt();
		visit = new boolean[N][M];
		map = new int[N][M];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				map[i][j]=s.nextInt();
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (!visit[i][j]) {
					countable=true;
					dfs(i,j);
					if (countable) {cnt++;}
					
				}
			}
		}
		System.out.println(cnt);
	}
	
	static void dfs(int x, int y) {
		visit[x][y]=true;
		
		int max = map[x][y];
		
		for (int k=0; k<8; k++) {
			int nr = x+dr[k];
			int nc = y+dc[k];
			
			if (0 <= nr && nr < N && 0 <= nc && nc < M) {
	            if (map[nr][nc] > max) {
	                countable = false; // 인접한 칸이 더 높은 경우 봉우리 아님
	            } else if (!visit[nr][nc] && map[nr][nc] == max) {
	                dfs(nr, nc);
	            }
	        }
		}
	}
}
