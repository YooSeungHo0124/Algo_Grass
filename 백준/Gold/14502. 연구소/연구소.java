/*
 * 맵이 엄청 크지 않기 때문에 brute force 이용해서 벽 3개를 세우고,
 * 각 케이스 별로 바이러스 확산시킨후 결과를 비교하겠다.
 * */

import java.util.*;
public class Main {

	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visit; 
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static int cnt =0;                 // 안전영역 개수  
	static int max =Integer.MIN_VALUE; // 안전영역 max값
	
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();
		
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				map[i][j]=s.nextInt();
			}
		}
		
		// 브루트 포스로 벽 3개 세우기
		for (int i1 = 0; i1 < N; i1++) {
		    for (int j1 = 0; j1 < M; j1++) {
		        for (int i2 = i1; i2 < N; i2++) {
		            for (int j2 = (i2 == i1) ? j1 + 1 : 0; j2 < M; j2++) {
		                for (int i3 = i2; i3 < N; i3++) {
		                    for (int j3 = (i3 == i2) ? j2 + 1 : 0; j3 < M; j3++) {
		                    	if (map[i1][j1]==0 && map[i2][j2]==0 && map[i3][j3]==0) {
		                    		// 벽 세우기
		                    		map[i1][j1]=1; map[i2][j2]=1; map[i3][j3]=1; 
		                    		
		                    		// 바이러스 퍼트리기
		                    		for (int i=0; i<N; i++) {
		                    			for (int j=0; j<M; j++) {
		                    				if (map[i][j] == 2) {
		                    					dfs(i,j);
		                    				}
		                    			}
		                    		}
		                    		
		                    		// 안전영역 개수 세기
		                    		for (int i=0; i<N; i++) {
		                    			for (int j=0; j<M; j++) {
		                    				if (map[i][j] == 0 && !visit[i][j]) {
		                    					cnt++;
		                    				}
		                    			} 
		                    		}
		                    		
		                    		// 최대 안전영역 개수
		                    		max = Math.max(cnt, max);
		                    		
		                    		// 벽 원래대로, visit 초기화, cnt 초기화
		                    		map[i1][j1]=0; map[i2][j2]=0; map[i3][j3]=0;
		                    		
		                    		for (int i = 0; i < N; i++) {
		                    		    Arrays.fill(visit[i], false);
		                    		}

		                    		cnt=0;
		                    	}
		                    }
		                }
		            }
		        }
		    }
		}
		System.out.println(max);
		
	}
	
	
	static void dfs(int x,int y) {
		visit[x][y]=true;
		
		for (int k=0; k<4; k++) {
			int nr = x+dr[k];
			int nc = y+dc[k];
			
			if (0<= nr && nr<N && 0<=nc && nc<M && map[nr][nc]==0 && !visit[nr][nc]) {
				dfs(nr,nc);
			}
		}
	}
}