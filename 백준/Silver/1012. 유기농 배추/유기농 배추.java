import java.util.*;
public class Main {
	
	static int[][] map;
	static boolean[][] visit;
	
	// 사방
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static int N;
	static int M;
	
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		int test_case=s.nextInt();
		int result_cnt = 1;
		while(test_case>0) {
			
			M=s.nextInt();
			N=s.nextInt();
			int K=s.nextInt();
			
			// 배추 맵 생성
			map = new int[N][M];
			for (int i=0; i<K; i++) {
				int x= s.nextInt();
				int y= s.nextInt();
				map[y][x]=1;
			}
			
			// boolean 생성
			visit = new boolean[N][M];
			
			int cnt=0;
			for (int i=0; i<N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j]==1 && !visit[i][j]) { // 배추 && 방문안한것
						dfs(i,j);
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
			
			
			test_case--;
			result_cnt++;
		}
	}
	
	
	static void dfs(int x, int y) {
		visit[x][y]=true;
		
		for (int k=0; k<4; k++) {
			int nx = x+dr[k];
			int ny = y+dc[k];
			
			if (0<=nx && nx<N && 0<=ny && ny<M 
					&& map[nx][ny]==1 && !visit[nx][ny]) {
				dfs(nx,ny);
			}
		}
	}
	
}
