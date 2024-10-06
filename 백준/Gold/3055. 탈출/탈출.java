import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		int N = s.nextInt();
		int M = s.nextInt();
		int dx=0,dy=0;
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		char[][] map = new char[N][M];
		boolean[][] visit = new boolean[N][M];
		PriorityQueue<int[]> pq = new PriorityQueue<>((x,y)->
		{
			if(x[2] == y[2]) {
				return x[3]-y[3];
			}
			return x[2]-y[2];
		});
		
		for (int i=0; i<N; i++) {
			char[] c= s.next().toCharArray();
			for (int j=0; j<M; j++) {
				map[i][j] = c[j];
				
				// 고슴 도치
				if (c[j] == 'S') {  
					pq.offer(new int[] {i,j,0,1});
					visit[i][j] = true;
				}
				// 비버 굴
				if (c[j] == 'D') {  
					dx = i;
					dy = j;
				}
				
				// 물
				if (c[j] == '*') {  
					pq.offer(new int[] {i,j,0,0});   // x,y,turn, * = 0 설정
					visit[i][j] = true;
				}
			}
		}
		
		
		while (!pq.isEmpty()) {
			int[] tmp = pq.poll();
			int x = tmp[0];
			int y = tmp[1];
			int turn = tmp[2];
			int mark = tmp[3];
			
			if (x == dx && y == dy && mark==1) {
				System.out.println(turn);
				return;
			}
			
			for (int k=0; k<4; k++) {
				int nr = x+dr[k];
				int nc = y+dc[k];
				
				// 물 일때 
				if ( mark==0 && 0<= nr && nr < N && 0<=nc && nc<M && map[nr][nc]=='.' && !visit[nr][nc]) {
					visit[nr][nc] = true;
					pq.offer(new int[] {nr,nc,turn+1,0});
				}
				// 비버 일때
				else if (mark==1 && 0<= nr && nr < N && 0<=nc && nc<M && (map[nr][nc]=='.' || map[nr][nc]=='D') && !visit[nr][nc]) {
					visit[nr][nc] = true;
					pq.offer(new int[] {nr,nc,turn+1,1});
				}
			}
			
		}
		System.out.println("KAKTUS");
		
	}
}	
