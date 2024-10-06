import java.util.*;
public class Main {
	
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		int N = s.nextInt();
		int M = s.nextInt();
		int[][] map = new int[N+1][M+1];
		boolean[][] visit = new boolean[N+1][M+1];
		for (int i=1; i<=N; i++) {
			char[] c= s.next().toCharArray();
			for (int j=0; j<M; j++) {
				map[i][j+1] = Character.getNumericValue(c[j]);
			}
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((x,y)->(x[2]-y[2]));
		pq.offer(new int[] {1,1,1});
		visit[1][1]= true;
		
		while (!pq.isEmpty()) {
			int[] tmp = pq.poll();
			int x = tmp[0];
			int y = tmp[1];
			int dist = tmp[2];
			
			
			if (x == N && y == M) {
				System.out.println(dist);
				return;
			}
			
			for (int k=0; k<4; k++) {
				int nr = x+dr[k];
				int nc = y+dc[k];
				
				if (1<=nr && nr<=N && 1<=nc && nc<=M && !visit[nr][nc] && map[nr][nc]==1) {
					visit[nr][nc] = true;
					pq.offer(new int[] {nr,nc,dist+1});
				}
			}
			
		}
		
	}
}
