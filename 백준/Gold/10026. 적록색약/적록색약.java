
import java.util.*;
public class Main {
	
	static char[][] map;
	static int[][] visit;
	static int[][] clone;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int N;
	
	static int nb_cnt=0;
	static boolean blue_cnt;
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		map = new char[N][N];
		visit = new int[N][N];
		clone = new int[N][N];
		
		for (int i=0; i<N; i++) {
			char[] c = s.next().toCharArray();
			for (int j=0; j<N; j++) {
				map[i][j] = c[j]; 
				
				if (c[j]=='R') {      // 레드
					visit[i][j]=1;
					clone[i][j]=1;
				}
				else if (c[j]=='G') { // 녹색
					visit[i][j]=2;
					clone[i][j]=2;
				}
				else {                // 파란색
					visit[i][j]=3;
					clone[i][j]=3;
				}
			}
		}
		
		// dfs blue 찾기
		// dfs red 찾기
		// dfs green 찾기
		int b_cnt=0;
		int g_cnt=0;
		int r_cnt=0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// blue
				if (map[i][j]=='B' && visit[i][j] != 6) {
					blue_cnt = true;
					dfs(i,j,3);
					b_cnt++;
				}
				// Green
				if (map[i][j]=='G' && visit[i][j] != 5) {
					dfs(i,j,2);
					g_cnt++;
				}
				// Red
				if (map[i][j]=='R' && visit[i][j] != 4) {
					dfs(i,j,1);
					r_cnt++;
				}
				
				
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// Not blue
				if (map[i][j]!='B' && (clone[i][j]==1 || clone[i][j]==2) ) {
					blue_cnt = false;
					dfs(i,j,3);
					nb_cnt++;
				}
			}
		}
		
		
		System.out.println((r_cnt + g_cnt + b_cnt)+" "+(nb_cnt + b_cnt));
	}
	
	static void dfs(int x, int y, int color) {
		char COLOR;
		if (color == 3) COLOR='B';
		else if (color == 2) COLOR='G';
		else COLOR = 'R';
		
		visit[x][y]=color+3;
		
		for (int k=0; k<4; k++) {
			int nr = x+dr[k];
			int nc = y+dc[k];
			
			if (0<=nr && nr<N && 0<=nc && nc<N && map[nr][nc]==COLOR &&visit[nr][nc]!=color+3 ) {
				dfs(nr,nc,color);
			}
			
			// not blue 로직
			if (blue_cnt==false &&COLOR=='B' && 0<=nr && nr<N && 0<=nc && nc<N && map[nr][nc]!=COLOR 
					&& clone[nr][nc]==1) {
				clone[nr][nc]=1+3;
				dfs(nr,nc,color);
			}
			// not blue 로직
			else if (blue_cnt==false && COLOR=='B' && 0<=nr && nr<N && 0<=nc && nc<N && map[nr][nc]!=COLOR 
					&& clone[nr][nc]==2) {
				clone[nr][nc]=2+3;
				dfs(nr,nc,color);
			}
			
		}
	}
	
}
