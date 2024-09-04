import java.util.*;
public class Main {
	static class edge{
		int x,y,w;

		public edge(int x, int y, int w) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
		}
	}
	static int[][] map;
	static int[][] distance;
	static int N;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int result_cnt =1;
		
		while (true) {
			N = s.nextInt();
			if (N == 0) {break;}
			map = new int[N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					map[i][j] = s.nextInt();
				}
			}
			distance = new int[N][N];
			for (int i=0; i<N; i++) {
				Arrays.fill(distance[i], Integer.MAX_VALUE);
			}
			distance[0][0] = map[0][0];
			
			PriorityQueue<edge> pq = new PriorityQueue<>((x,y)->(x.w-y.w)); 
			pq.offer(new edge(0,0,map[0][0])); // 0,0 시작
			
			while (!pq.isEmpty()) {
				edge tmp = pq.poll();
				int x= tmp.x;
				int y= tmp.y;
				int w= tmp.w;

				if (distance[x][y] < w) {continue;}
				
				for (int i=0; i<4; i++) {
					int nr = x + dr[i];
					int nc = y + dc[i];
					
					if (0<=nr && nr<N && 0<=nc && nc<N) {
						if (distance[nr][nc] > w + map[nr][nc]) {
							distance[nr][nc] = w + map[nr][nc];
							pq.offer(new edge(nr,nc,distance[nr][nc]));
						}
						
						
					}
					
				}
				
			}
			
			System.out.println("Problem "+result_cnt+": "+distance[N-1][N-1]);
			result_cnt++;
		}
		s.close();
	}
}
