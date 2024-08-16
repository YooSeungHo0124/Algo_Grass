

import java.util.*;
public class Main {
	
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dr = {-1,-1,-1,0,1,1,1,0};
	static int[] dc = {-1,0,1,1,1,0,-1,-1};
	
	static int W;
	static int H;
	
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		while (true) {
			W = s.nextInt();
			H = s.nextInt();
			
			if (W==0 && H==0) {break;}
			
			map = new int[H][W];
			for (int i=0; i<H; i++) {
				for (int j=0; j<W; j++) {
					map[i][j]=s.nextInt();
				}
			}
			
			visit = new boolean[H][W];
			
			int cnt=0;
			for (int i=0; i<H; i++) {
				for (int j=0; j<W; j++) {
					if (map[i][j]==1 && !visit[i][j]) {
						dfs(i,j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
	
	static void dfs (int x, int y) {
		visit[x][y]=true;
		
		for (int k=0; k<8; k++) {
			int nr = x+dr[k];
			int nc = y+dc[k];
			
			if (0<=nr && nr<H && 0 <=nc && nc<W 
					&& map[nr][nc]==1 && !visit[nr][nc]) {
				dfs(nr,nc);
			}
		}
	}
}
